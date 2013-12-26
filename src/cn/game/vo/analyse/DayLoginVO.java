package cn.game.vo.analyse;

/*
 * 每日登陆统计
 */
public class DayLoginVO {

	int id;
	String date;	//日期
	int regcount;	//注册人数
	int logincount;	//登陆人数
	int clogincount;//连续3天登陆人数
	int lcount2;	//次2日连续登陆人数
	int lcount3;	//次3日连续登陆人数
	int lcount4;	//次4日连续登陆人数
	int lcount5;	//次5日连续登陆人数
	int lcount6;	//次6日连续登陆人数
	int lcount7;	//次7日连续登陆人数
	int effectcount;//有效用户数（7天之内登录的用户数）//需要定时统计 
	
	public DayLoginVO()
	{
		regcount = 0;	//注册人数
		logincount = 0;	//登陆人数
		clogincount = 0;//连续3天登陆人数
		lcount2 = 0;	//次2日连续登陆人数
		lcount3 = 0;	//次3日连续登陆人数
		lcount4 = 0;	//次4日连续登陆人数
		lcount5 = 0;	//次5日连续登陆人数
		lcount6 = 0;	//次6日连续登陆人数
		lcount7 = 0;	//次7日连续登陆人数
		effectcount = 0;//有效用户数（7天之内登录的用户数）  
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
	public int getClogincount() {
		return clogincount;
	}
	public void setClogincount(int clogincount) {
		this.clogincount = clogincount;
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
	public int getLcount5() {
		return lcount5;
	}
	public void setLcount5(int lcount5) {
		this.lcount5 = lcount5;
	}
	public int getLcount6() {
		return lcount6;
	}
	public void setLcount6(int lcount6) {
		this.lcount6 = lcount6;
	}
	public int getLcount7() {
		return lcount7;
	}
	public void setLcount7(int lcount7) {
		this.lcount7 = lcount7;
	}
	public int getEffectcount() {
		return effectcount;
	}
	public void setEffectcount(int effectcount) {
		this.effectcount = effectcount;
	}
}
