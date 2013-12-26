package cn.game.vo.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;


public abstract class BasicVO 
{
	@JsonIgnore
	public abstract String getKey();
	
	//public abstract String getXmlData() ;
}
