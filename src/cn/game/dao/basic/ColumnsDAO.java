package cn.game.dao.basic;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.game.vo.basic.ColumnsBean;
import cn.game.vo.basic.TableBean;

public class ColumnsDAO extends SqlMapClientDaoSupport {

	public boolean doesColumnExist(String tableName, String fieldName)
	{
		TableBean table = new TableBean();
		table.setTableName(tableName);
		table.setFieldName(fieldName);
		ColumnsBean column = (ColumnsBean)getSqlMapClientTemplate().queryForObject("ColumnsBean.getColumnsByName", table);
		
		if(column == null)
		{
			System.out.println(tableName + "&" + fieldName + " : Not Exist");
			return false;
		}
		else
		{
			System.out.println(tableName + "&" + fieldName + " : Exist");
			return true;
		}
	}
	
	public void addColumn(String tableName, String fieldName, String type, String defaultv, String comment)
	{
		System.out.println("addColumn : " + tableName + "&" + fieldName + "&" + type + "&" + defaultv + "&" + comment);
		
		TableBean table = new TableBean();
		table.setTableName(tableName);
		table.setFieldName(fieldName);
		table.setType(type);
		table.setDefaultv(defaultv);
		table.setComment(comment);
		
		getSqlMapClientTemplate().update("ColumnsBean.addColumn", table);
	}

	public void createTable(String createstring) {
		
		System.out.println("createTable : " + createstring);
		
		getSqlMapClientTemplate().update("ColumnsBean.createTable", createstring);
	}
}
