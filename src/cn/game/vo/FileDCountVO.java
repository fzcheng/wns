package cn.game.vo;

import cn.game.vo.BaseVO;

public class FileDCountVO extends BaseVO{

	int id;
	String filename;
	int count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
