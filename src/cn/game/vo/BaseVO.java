package cn.game.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseVO 
{
	private String tableName;
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
	
	@JsonIgnore
	public String getTableName()
	{
		return tableName;
	}
}
