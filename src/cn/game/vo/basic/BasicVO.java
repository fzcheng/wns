package cn.game.vo.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;


public abstract class BasicVO 
{
	@JsonIgnore
	public abstract String getKey();
	
	@JsonIgnore
	public String getGroupKey()
	{
		return getKey();
	}
	//public abstract String getXmlData() ;
}
