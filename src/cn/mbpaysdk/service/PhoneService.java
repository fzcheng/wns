package cn.mbpaysdk.service;

import cn.mbpaysdk.dao.PhoneDAO;
import cn.mbpaysdk.vo.PhoneVO;

public class PhoneService {

	PhoneDAO phonedao;
	
	DataService dataservice;
	
	public void setPhonedao(PhoneDAO phonedao) {
		this.phonedao = phonedao;
	}
	
	public void setDataservice(DataService dataservice) {
		this.dataservice = dataservice;
	}

	public void createOrUpdate(PhoneVO phone) {
		PhoneVO cur = phonedao.getByKey(phone);
		if(cur != null)
		{
			phonedao.update(phone);
		}
		else
		{
			phonedao.save(phone);
		}
	}
}
