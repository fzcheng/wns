package cn.master.dao;

import cn.game.dao.record.BasicRoleDAO;
import cn.master.vo.RecordVO;
import cn.master.vo.UploadRecordLogVO;


public class UploadRecordLogDAO extends BasicRoleDAO<UploadRecordLogVO> {

	@Override
	public String getXmlname() {
		
		return "UploadRecordLog";
	}

	public UploadRecordLogVO getByKey(RecordVO vo) {
		return (UploadRecordLogVO)getSqlMapClientTemplate().queryForObject(getXmlname() + ".getByKey", vo);
	}
}
