package cn.org.util;
/**
 * 分页
 * @author LuZhiYong
 * @Date 2012-7-16
 */
public class Paging {

	private int pageSize = 10;	//每页的记录条数
	private int pageIndex = 1;	//当前页
	private int count = 0;		//记录总数
	
	public Paging(int pageIndex, int count){
		this.pageIndex = pageIndex;
		this.count = count;
	}
	
	public Paging(int count){
		this.count = count;
	}
	
	//当前页的开始记录行数
	public int getStart() {
		return pageIndex*pageSize-pageSize;
	}
	
	//总页数
	public int getTotalPage(){
		if(count%pageSize == 0){
			return count/pageSize;
		}else{
			return (count/pageSize)+1;
		}
	}
	
	public static void main(String[] args) {
		Paging p = new Paging(30);
		System.out.println(p.getTotalPage());
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		if(pageIndex < 0){
			pageIndex = 0;
		}else if(pageIndex > getTotalPage()){
			pageIndex = getTotalPage();
		}

		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
