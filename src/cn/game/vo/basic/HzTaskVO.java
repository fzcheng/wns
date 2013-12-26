package cn.game.vo.basic;


/**
 * 用户信息
 * @author fzc
 *
 */
public class HzTaskVO extends BasicVO{

	int TaskID;         
	int TaskSucType;         
	int TaskSucTypeParam1;         
	int TaskSucTypeParam2;         
	int TaskFailType;         
	int TaskFailTypeParam1;         
	int TaskFailTypeParam2;         
	int RewardType;         
	int RewardParam;         
	int NextTaskID;         

	@Override
	public String getKey() {
		return "" + TaskID;
	}

	public int getTaskID() {
		return TaskID;
	}

	public void setTaskID(int taskID) {
		TaskID = taskID;
	}

	public int getTaskSucType() {
		return TaskSucType;
	}

	public void setTaskSucType(int taskSucType) {
		TaskSucType = taskSucType;
	}

	public int getTaskSucTypeParam1() {
		return TaskSucTypeParam1;
	}

	public void setTaskSucTypeParam1(int taskSucTypeParam1) {
		TaskSucTypeParam1 = taskSucTypeParam1;
	}

	public int getTaskSucTypeParam2() {
		return TaskSucTypeParam2;
	}

	public void setTaskSucTypeParam2(int taskSucTypeParam2) {
		TaskSucTypeParam2 = taskSucTypeParam2;
	}

	public int getTaskFailType() {
		return TaskFailType;
	}

	public void setTaskFailType(int taskFailType) {
		TaskFailType = taskFailType;
	}

	public int getTaskFailTypeParam1() {
		return TaskFailTypeParam1;
	}

	public void setTaskFailTypeParam1(int taskFailTypeParam1) {
		TaskFailTypeParam1 = taskFailTypeParam1;
	}

	public int getTaskFailTypeParam2() {
		return TaskFailTypeParam2;
	}

	public void setTaskFailTypeParam2(int taskFailTypeParam2) {
		TaskFailTypeParam2 = taskFailTypeParam2;
	}

	public int getRewardType() {
		return RewardType;
	}

	public void setRewardType(int rewardType) {
		RewardType = rewardType;
	}

	public int getRewardParam() {
		return RewardParam;
	}

	public void setRewardParam(int rewardParam) {
		RewardParam = rewardParam;
	}

	public int getNextTaskID() {
		return NextTaskID;
	}

	public void setNextTaskID(int nextTaskID) {
		NextTaskID = nextTaskID;
	}
}
