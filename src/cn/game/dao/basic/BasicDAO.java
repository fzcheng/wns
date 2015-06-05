package cn.game.dao.basic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.bill.migu.vo.basic.MiguCodeVO;
import cn.game.vo.basic.BasicVO;
import cn.game.vo.basic.GameVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BasicDAO<T extends BasicVO> extends SqlMapClientDaoSupport {

	public LinkedHashMap<String, T> map = new LinkedHashMap<String, T>();
	String xmlname;
	int dataLoadTime;

	public BasicDAO() {
	}

	public BasicDAO(SqlMapClient sqlMapClient, String xmlname) {
		this.setSqlMapClient(sqlMapClient);
		this.xmlname = xmlname;
	}

	/**
	 * 单位时间（秒）加载一次的数据用此构造
	 * 
	 * @param sqlMapClient
	 * @param xmlname
	 * @param dataLoadTime
	 */
	public BasicDAO(SqlMapClient sqlMapClient, String xmlname, int dataLoadTime) {
		this.setSqlMapClient(sqlMapClient);
		this.xmlname = xmlname;
		this.dataLoadTime = dataLoadTime;
		es.scheduleWithFixedDelay(new DataLoadRunnable(), dataLoadTime,
				dataLoadTime, TimeUnit.SECONDS);
	}

	public T getById(String id) {
		if (map == null || map.size() <= 0) {
			loadList();
		}
		return map.get(id);
	}

	public T getByKey(String id) {
		T t = (T)getSqlMapClientTemplate().queryForObject(
				xmlname + ".getByKey", id);
		
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public void loadList() {
		List<T> list = getSqlMapClientTemplate().queryForList(
				xmlname + ".loadList");
		map.clear();
		for (int i = 0; i < list.size(); i++) {
			T vo = (T) list.get(i);
			map.put(vo.getKey(), vo);
		}
	}

	public List<T> getList() {
		if (map == null || map.size() <= 0) {
			this.loadList();
		}
		List<T> list = new ArrayList<T>(map.values());
		return list;
	}

	private ScheduledExecutorService es = Executors.newScheduledThreadPool(1);

	/**
	 * 定时加载数据线程
	 */
	class DataLoadRunnable implements Runnable {

		@Override
		public void run() {
			try {
				loadList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void update(T t) {
		getSqlMapClientTemplate().update(
				xmlname + ".update", t);
	}
}
