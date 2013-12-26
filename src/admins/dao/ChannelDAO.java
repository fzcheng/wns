package admins.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import admins.ben.ChannelVO;

public class ChannelDAO extends SqlMapClientDaoSupport{


	/**
	 * 获取channel
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelVO> getList() {
		return (List<ChannelVO>)getSqlMapClientTemplate().queryForList("Channel.getList");
	}
	
	public ChannelVO getByChannelId(String gameId, int channelId){
		ChannelVO channelvo = new ChannelVO();
		channelvo.setChannelId(channelId);
		return (ChannelVO) getSqlMapClientTemplate().queryForObject("Channel.getByChannelId", channelvo);
	}
	
	public int saveChannel(ChannelVO vo){
		return (Integer) getSqlMapClientTemplate().insert("Channel.save", vo);
	}
	
	public int updateChannel(ChannelVO vo){
		return (Integer) getSqlMapClientTemplate().update("Channel.update", vo);
	}
	
	public int delChannel(String gameId, int channelId){
		ChannelVO channelvo = new ChannelVO();
		channelvo.setChannelId(channelId);
		return (Integer) getSqlMapClientTemplate().delete("Channel.del", channelvo);
	}
	public ChannelVO getChannelById(String id){
		return (ChannelVO) getSqlMapClientTemplate().queryForObject("Channel.getChannelById", id);
	}
	
	public int delChannel(int channelId){
		return (Integer) getSqlMapClientTemplate().delete("Channel.del", channelId);
	}
}
