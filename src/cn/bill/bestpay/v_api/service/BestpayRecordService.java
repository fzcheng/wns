package cn.bill.bestpay.v_api.service;

import cn.bill.bestpay.v_api.dao.BestpayOriRecordDAO;
import cn.bill.bestpay.v_api.dao.BestpayRecordDAO;
import cn.bill.bestpay.v_api.vo.BestpayOriRecordVO;
import cn.bill.bestpay.v_api.vo.BestpayRecordVO;
import cn.org.util.DateUtil;

public class BestpayRecordService {

	BestpayRecordDAO bestpayrecorddao;
	BestpayOriRecordDAO bestpayorirecorddao;
	
	public void setBestpayrecorddao(BestpayRecordDAO bestpayrecorddao)
	{
		this.bestpayrecorddao = bestpayrecorddao;
	}
	
	public void setBestpayorirecorddao(BestpayOriRecordDAO bestpayorirecorddao)
	{
		this.bestpayorirecorddao = bestpayorirecorddao;
	}
	
	/**
	 * 
	 * @param orderid
	 * @param phone
	 * @param tid
	 * @param app_id
	 * @param app_name
	 * @param fee_name
	 * @param dev_name
	 * @return
	 */
	public BestpayRecordVO createRecord(String orderid, String phone, int money, String tid, int cp_channel_id, String app_id, String fee_code, String dev_name, String url, String extra)
	{
		BestpayRecordVO v = bestpayrecorddao.getByOrderid(orderid);
		if(v != null)
			return null;
			
		BestpayRecordVO record = new BestpayRecordVO();
		record.setOrderid(orderid);
		record.setPhone(phone);
		record.setPrice(money);
		record.setTid(tid);
		record.setCp_channel_id(cp_channel_id);
		record.setApp_id(app_id);
		record.setFee_code(fee_code);
		record.setDev_name(dev_name);
		record.setTransfer_url(url);
		record.setExtra(extra);
		record.setCreate_time(DateUtil.getCurrentTimeStrs());
		record.setModify_time(DateUtil.getCurrentTimeStrs());
		
		System.out.println(record.toString());
		record.setId(bestpayrecorddao.save(record));
		
		return record;
	}
	
	public BestpayRecordVO getRecordByOrderid(String orderid)
	{
		BestpayRecordVO v = bestpayrecorddao.getByOrderid(orderid);
		if(v != null)
			return v;
		
		return null;
	}
	
	public BestpayRecordVO getRecordByTid(String tid)
	{
		BestpayRecordVO v = bestpayrecorddao.getByTid(tid);
		if(v != null)
			return v;
		
		return null;
	}
	
	public BestpayRecordVO getRecordByChannelOrderid(String channelorderid)
	{
		BestpayRecordVO v = bestpayrecorddao.getByChannelOrderid(channelorderid);
		if(v != null)
			return v;
		
		return null;
	}

	public void update(BestpayRecordVO record) {
		record.setModify_time(DateUtil.getCurrentTimeStrs());
		
		bestpayrecorddao.update(record);
	}

	public BestpayRecordVO getRecordById(int id) {
		BestpayRecordVO vo = new BestpayRecordVO();
		vo.setId(id);
		vo = bestpayrecorddao.getById(vo);
		return vo;
	}
	
	public BestpayRecordVO getRecordBySMSID(String smsid) {
		BestpayRecordVO vo = bestpayrecorddao.getBySMSID(smsid);
		return vo;
	}
	
	
	//////ori record/////////////////////////////////////////////////////////
	public void updateOri(BestpayOriRecordVO record) {
		record.setModify_time(DateUtil.getCurrentTimeStrs());
		
		bestpayorirecorddao.update(record);
	}
	
	public BestpayOriRecordVO getRecordOriByOrderNo(String orderNo)
	{
		BestpayOriRecordVO v = bestpayorirecorddao.getByOrderNo(orderNo);
		if(v != null)
			return v;
		
		return null;
	}
	
	public BestpayOriRecordVO createRecordOri(String orderNo, String goodsCode, String phoneNum, int orderAmount, String attach)
	{
		BestpayOriRecordVO v = getRecordOriByOrderNo(orderNo);
		if(v != null)
			return null;
			
		BestpayOriRecordVO record = new BestpayOriRecordVO();
		record.setOrderNo(orderNo);
		record.setGoodsCode(goodsCode);
		record.setPhoneNum(phoneNum);
		record.setOrderAmount(orderAmount);
		record.setAttach(attach);
		record.setCreate_time(DateUtil.getCurrentTimeStrs());
		record.setModify_time(DateUtil.getCurrentTimeStrs());
		
		System.out.println(record.toString());
		record.setId(bestpayorirecorddao.save(record));
		
		return record;
	}
}
