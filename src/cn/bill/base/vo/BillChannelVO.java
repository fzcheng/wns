package cn.bill.base.vo;

import java.util.ArrayList;
import java.util.List;

import cn.bill.migu.vo.basic.MiguCodeVO;
import cn.game.vo.basic.BasicVO;


/**
 * 渠道
 * @author fzc
 *
 */
public class BillChannelVO extends BasicVO{

	int channelid;
	String channelname = "";
	String key = "";
	int status;//1-启用  2-关闭  0-配置中
	String codeids = "";
	String remark = "";
	String transfer_url = "";
	int p;
	
	List<MiguCodeVO> migucodelist;
	
	public BillChannelVO() {
		migucodelist = new ArrayList<MiguCodeVO>();
	}


	@Override
	public String getKey() {
		return "" + channelid;
	}


	public int getChannelid() {
		return channelid;
	}


	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}


	public String getChannelname() {
		return channelname;
	}


	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getCodeids() {
		return codeids;
	}


	public void setCodeids(String codeids) {
		this.codeids = codeids;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getTransfer_url() {
		return transfer_url;
	}


	public void setTransfer_url(String transfer_url) {
		this.transfer_url = transfer_url;
	}


	public void addMiguCode(MiguCodeVO migucode) {
		migucodelist.add(migucode);
	}
	
	public List<MiguCodeVO> getMiguCodeList() {
		return migucodelist;
	}


	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}
}
