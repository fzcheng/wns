package cn.game.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseVO 
{
	private String tableName;
	private String para;
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
	
	@JsonIgnore
	public String getTableName()
	{
		return tableName;
	}

	@JsonIgnore
	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}
}
