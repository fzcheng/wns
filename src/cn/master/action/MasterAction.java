package cn.master.action;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import awo.common.model.obj.AnalyDataFormat.FirstTest;
import awo.common.model.obj.AnalyDataFormat.FirstTestBack;
import cn.master.MasterErrorCode;
import cn.master.dao.RecordDAO;
import cn.master.exception.VersionException;
import cn.master.proto.MasterData.NullData_;
import cn.master.proto.MasterData.SaveData_;
import cn.master.response.ErrorResponse;
import cn.master.response.MasterResponse;
import cn.master.service.MasterService;
import cn.master.vo.RecordVO;
import cn.org.util.SpringUtils;

import com.google.protobuf.InvalidProtocolBufferException;

public class MasterAction extends BaseAction {

	private final static Logger logger = Logger.getLogger(MasterAction.class);
	
	MasterService masterservice;
	
	public void setMasterservice(MasterService masterservice) {
		this.masterservice = masterservice;
	}
	
	/**
	 * FirstTest
	 */
	public ActionForward FirstTest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ByteArrayOutputStream bytearray = (ByteArrayOutputStream)request.getAttribute("ByteArray");
		
		MasterResponse firstResp = new MasterResponse(request, response);
		
		FirstTest.Builder first = FirstTest.newBuilder();
		try {
			first.mergeFrom(bytearray.toByteArray());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_DecodeData);
		}

		FirstTestBack.Builder firstBack = FirstTestBack.newBuilder().
				setTestInt1(321).
				setTestStr2("this is 魔力小鸟。 1028").
				setTestStr3(first.getTestStr2()).
				setTestStr4("eggeggfwesfs").addAllGroup(first.getGroupList());
		
		firstResp.setByteArray(firstBack.build().toByteArray());
		firstResp.write();
		
		return null;
	}
	
	/**
	 * getrecord
	 */
	public ActionForward getrecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		MasterResponse firstResp = new MasterResponse(request, response);
		
		/*
		ByteArrayOutputStream bytearray = (ByteArrayOutputStream)request.getAttribute("ByteArray");
		MasterData.NullData_.Builder first = MasterData.NullData_.newBuilder();
		try {
			first.mergeFrom(bytearray.toByteArray());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_DecodeData);
		}
		*/
		
		RecordVO record = masterservice.getRecord(request);
		
		SaveData_.Builder savedata = SaveData_.newBuilder();
		if(record != null)
		{
			//正常从byte[]中初始化
			try {
				savedata.mergeFrom(record.getRecord_data());
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
				
				return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_DecodeData);
			}
		}
		else
		{
			//第一次 无存档的情况下返回SaveData_ 中设置version为－1
			savedata.setDataVersions(-1);
		}
		
		firstResp.setByteArray(savedata.build().toByteArray());
		firstResp.write();
		
		return null;
	}
	
	/**
	 * upload
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ByteArrayOutputStream bytearray = (ByteArrayOutputStream)request.getAttribute("ByteArray");
		
		MasterResponse firstResp = new MasterResponse(request, response);
		
		try {
			masterservice.updateRecord(bytearray.toByteArray(), false);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_DecodeData);
		} catch(VersionException e)
		{
			//客户端数据版本低
			e.printStackTrace();
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_LowVersion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_Exception);
		}
		
		NullData_.Builder firstBack = NullData_.newBuilder();
		firstResp.setByteArray(firstBack.build().toByteArray());
		firstResp.write();
		
		return null;
	}
	
	/**
	 * uploadforce
	 */
	public ActionForward uploadforce(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ByteArrayOutputStream bytearray = (ByteArrayOutputStream)request.getAttribute("ByteArray");
		
		MasterResponse firstResp = new MasterResponse(request, response);
		
		try {
			masterservice.updateRecord(bytearray.toByteArray(), true);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
			
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_DecodeData);
		} catch(VersionException e)
		{
			//无需强制
			e.printStackTrace();
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_NoNeedForce);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return dispatchMethodError(mapping, form, request, response, MasterErrorCode.Error_Exception);
		}
		
		NullData_.Builder firstBack = NullData_.newBuilder();
		firstResp.setByteArray(firstBack.build().toByteArray());
		firstResp.write();
		
		return null;
	}
	
	/**
	 * error
	 */
	public ActionForward error(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String errorCode = (String)request.getAttribute("errorCode");
		String errorMsg = (String)request.getAttribute("errorMsg");
		
		ErrorResponse errorResp = new ErrorResponse(request, response, errorCode, errorMsg);
		
		errorResp.setByteArray("error".getBytes());
		errorResp.write();
		
		//TODO 测试用
		RecordDAO recorddao = (RecordDAO)SpringUtils.getBean("master_recorddao");
		ByteArrayOutputStream bytearray = (ByteArrayOutputStream)request.getAttribute("ByteArray");
		
		String data = "";
		try {
			data = new String(bytearray.toByteArray(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		RecordVO vo = new RecordVO();
//		vo.setRecord_data(bytearray.toByteArray());
//		//vo.setRecord_data(data);
//		vo.setUID("32132132132131312");
//		vo.setCreate_time(DateUtil.getCurrentTime());
//		vo.setLast_time(DateUtil.getCurrentTime());
//		recorddao.save(vo);
		
		RecordVO vo = new RecordVO();
		vo.setUID("32132132132131312");
		vo = recorddao.getByKey(vo);
		byte readbyte[] = vo.getRecord_data();
		byte oldbyte[] = bytearray.toByteArray();
		
		String data2 = "";
		try {
			data2 = new String(readbyte, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
