package cn.game.vo.record;

import cn.game.vo.BaseVO;

public class SuggestVO extends BaseVO{

	int id;
	String mtId;
	String plat;
	String suggest;
	String time;
	
	public SuggestVO()
	{
		
	}

	public String getMtId() {
		return mtId;
	}

	public void setMtId(String mtId) {
		this.mtId = mtId;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
