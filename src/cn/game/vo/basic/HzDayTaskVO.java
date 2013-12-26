package cn.game.vo.basic;


/**
 * 用户信息
 * @author fzc
 *
 */
public class HzDayTaskVO extends BasicVO{
   
	String dayTime;
	String TaskSucType;
	

	@Override
	public String getKey() {
		return "" + dayTime;
	}

	public String getTaskSucType() {
		return TaskSucType;
	}

	public void setTaskSucType(String taskSucType) {
		TaskSucType = taskSucType;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

}
