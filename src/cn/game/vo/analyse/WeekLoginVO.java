package cn.game.vo.analyse;

public class WeekLoginVO {
	
	int id;
	String date;	//日期
	int regcount;	//注册人数
	int logincount;	//登陆人数
	int lcount2;	//次2周连续登陆人数
	int lcount3;	//次3周连续登陆人数
	int lcount4;	//次4周连续登陆人数
	
	public WeekLoginVO()
	{
		regcount = 0;	//注册人数
		logincount = 0;	//登陆人数
		lcount2 = 0;	//次2日连续登陆人数
		lcount3 = 0;	//次3日连续登陆人数
		lcount4 = 0;	//次4日连续登陆人数
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRegcount() {
		return regcount;
	}
	public void setRegcount(int regcount) {
		this.regcount = regcount;
	}
	public int getLogincount() {
		return logincount;
	}
	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}
	public int getLcount2() {
		return lcount2;
	}
	public void setLcount2(int lcount2) {
		this.lcount2 = lcount2;
	}
	public int getLcount3() {
		return lcount3;
	}
	public void setLcount3(int lcount3) {
		this.lcount3 = lcount3;
	}
	public int getLcount4() {
		return lcount4;
	}
	public void setLcount4(int lcount4) {
		this.lcount4 = lcount4;
	}
}
