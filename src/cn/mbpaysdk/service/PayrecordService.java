package cn.mbpaysdk.service;

import cn.mbpaysdk.dao.PayrecordDAO;
import cn.mbpaysdk.vo.PayrecordVO;

public class PayrecordService {

	PayrecordDAO payrecorddao;
	
	DataService dataservice;
	
	public void setPayrecorddao(PayrecordDAO payrecorddao) {
		this.payrecorddao = payrecorddao;
	}
	
	public void setDataservice(DataService dataservice) {
		this.dataservice = dataservice;
	}

	public void createOrUpdate(PayrecordVO payrecord) {
		PayrecordVO cur = payrecorddao.getByOrderId(payrecord.getOrder_id());
		if(cur != null)
		{
			payrecorddao.update(payrecord);
		}
		else
		{
			payrecorddao.save(payrecord);
		}
	}

	public PayrecordVO getByOrderId(String order_id) {
		return payrecorddao.getByOrderId(order_id);
	}
}
