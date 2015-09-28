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

	int channel_id;
	String channel_name = "";
	String channelkey = "";
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
		return "" + channel_id;
	}


	public int getChannel_id() {
		return channel_id;
	}


	public void setChannel_id(int channelid) {
		this.channel_id = channelid;
	}


	public String getChannel_name() {
		return channel_name;
	}


	public void setChannel_name(String channelname) {
		this.channel_name = channelname;
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


	public void setChannelkey(String channelkey) {
		this.channelkey = channelkey;
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


	public String getChannelkey() {
		return channelkey;
	}
}
