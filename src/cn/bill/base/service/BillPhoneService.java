package cn.bill.base.service;

import cn.bill.base.dao.BillPhoneDAO;
import cn.bill.base.vo.BillPhoneVO;
import cn.game.util.DateUtil;


/**
 * bill phone 数据服务
 * 
 * @author fzcheng
 */
public class BillPhoneService {
	BillPhoneDAO billphonedao;

	public void setBillphonedao(BillPhoneDAO billphonedao) {
		this.billphonedao = billphonedao;
	}
	
	public BillPhoneVO getByPhone(String phone)
	{
		BillPhoneVO miguphone = billphonedao.getByPhone(phone);
		if(miguphone == null)
		{
			miguphone = new BillPhoneVO();
			miguphone.setPhonenum(phone);
			miguphone.setImsi("");
			miguphone.setCreate_time(DateUtil.getCurrentTime());
			miguphone.setIsblock(0);
			miguphone.setLast_time(DateUtil.getCurrentTime());
			miguphone.setMonthcount(0);
			miguphone.setTotalcount(0);
			miguphone.setTotalprice(0);
			
			billphonedao.save(miguphone);
		}
		
		return miguphone;
	}
	
	public BillPhoneVO getByImsi(String imsi)
	{
		BillPhoneVO miguphone = billphonedao.getByImsi(imsi);
		if(miguphone == null)
		{
			miguphone = new BillPhoneVO();
			miguphone.setPhonenum("");
			miguphone.setImsi(imsi);
			miguphone.setCreate_time(DateUtil.getCurrentTime());
			miguphone.setIsblock(0);
			miguphone.setLast_time(DateUtil.getCurrentTime());
			miguphone.setMonthcount(0);
			miguphone.setTotalcount(0);
			miguphone.setTotalprice(0);
			
			billphonedao.save(miguphone);
		}
		
		return miguphone;
	}
}
