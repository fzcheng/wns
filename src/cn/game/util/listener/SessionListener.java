package cn.game.util.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {
	Logger logger = Logger.getLogger("log.cachedao");
	public void sessionCreated(HttpSessionEvent hse) {

	}
	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
//		UserLoginVO userloginvo = (UserLoginVO)session.getAttribute("userloginvo");
//		String teamId = (String)session.getAttribute("teamId");
//		if(userloginvo != null && teamId != null){
//			DCacheClient dCacheClient = new DCacheClient();
//			dCacheClient.SessionListener(session.getId(),userloginvo.getInfoId()+"");//更新统计之类的数据
//			RadAffairDAO.removeRadaffairMap(teamId);//清除比赛随即事件
//			MemCacheUtil.remove(userloginvo.getInfoId());//清楚登录时间
//			session.removeAttribute(Constants.REQUEST_PARAMS);
//		}
//		LockManager.remove(teamId);
	}
}
