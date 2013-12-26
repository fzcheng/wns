package cn.game.service;

import cn.game.config.GameConfig;

/**
 * 静态字符串
 * 
 * @author fzc
 */
public class StaticStr {
	public final static int LanType_Korea = 1;

	public static String str1 = "出错了";
	public static String loginStr1 = "服务器人满，请稍后在试。";
	public static String loginStr2 = "登陆失败";
	
	public static String onlineHelpStr1 = "请输入问题.";
	public static String onlineHelpStr2 = "输入字符过长.";
	public static String onlineHelpStr3 = "已有一个提问,如需再提问需撤销原提问";
	public static String onlineHelpStr4 = "提问成功";
	
	public static String ROLE_GOLD_NOTENOUGH="元宝不足";
	public static String ROLE_SCORE_NOTENOUGH="积分不足";
	
	public static String ROLE_NO_ROLEINFO="无此id角色";
	public static String REPORT_NO_REPORT="已无可阅奏章";
	public static String REPORT_NOTEQUALSID="奏章ID不匹配";
	public static String REPORT_ILLEGAL_OPT="非法的奏折操作";
	public static String REPORT_NOTCONDITION_OPT="准奏条件未满足";

	public static String noTaelTimes = "冷却中。";
	public static String noFoodTimes = "冷却中。";
	public static String noSoldierTimes = "冷却中。";
	public static String noFood = "粮食不足";
	public static String chooseGainType = "选择征收类型。";
	public static String chooseClearType = "选择加速类型。";
	public static String chooseUpdateType = "选择升级类型。";
	public static String errorName = "请输入正确的名称。";
	public static String noNeedClearCD = "无需加速。";
	public static String taelLimit = "达到银两上限";
	public static String foodLimit = "达到粮食上限";
	
	public static String GIRL_NO_GIRL="没有此美人";
	public static String GIRL_ENERGY_NOTENOUGH="精力不足";
	public static String GIRL_PLACEID_ERROR="非法的后宫编号";
	public static String GIRL_TOP_PLACE="不能再升级";
	
	public static String CHILD_NAME_UNVALIDATE="皇子名字请保持5个字以内";
	public static String CHILD_HAVE_NO="您没有该皇子信息";
	public static String CHILD_NAME_EXISTS="该皇子已经赐过名";
	public static String CHILD_NAME_OUTDATE="该皇子已过成长阶段不能赐名";
	public static String CHILD_JUE_EXISTS="该皇子已有过册封";
	public static String CHILD_JUE_NOTOPLEGEL="等级不到不能册封";
	public static String CHILD_JUE_ERROE="册封失败";
	public static String CHILD_CDTIME_HAVE="CD时间未结束";
	public static String CHILD_LEVEL_ISTOP="皇子等级已满不能在培训";
	public static String CHILD_CANNOT_LEVELUP="成长阶段皇子不能进行升级";
	
	public static String MINISTER_TOP_LEVEL="该大臣已经满级";
	
	public static String TRAVELPLACE_NO_LOTTERY="今日摇奖次数已完";
	
	public static String HAVE_NO_DIE_SOIDIER="没有死亡士兵不需要复活";
	
	public static String HAVE_NO_WAR="没有该据点";
	
	public static String PLEASE_CROSS_PRE_VAR="请战胜前面的据点";
	
	public static String THIS_WAR_IS_PASS="次关卡已经战胜";
	
	public static String SOIDIER_ALL_DEAD="没有士兵可以作战";
	
	public static String CANNOT_GAIN_COUNTRY="该国家不可以进行收获";
	
	public static String DAYCOUNT_OVER="今日征收次数已完";
	
	
	//语言类型
	public static int lantype = GameConfig.getStrKeyInteger("lantype");
	
	public static String mobaied = "今日已膜拜。";
	public static String gold = "元宝";
	public static String chooseMobaiType = "选择膜拜榜单类型。";
	public static String noThew = "体力不足";
	public static String noBook = "没有藏宝图。";
	public static String noLuck = "运试不足，请等待自动回复。";
	public static String emptyItem = "没有获得物品.";
	public static String gain = "获得";
	public static String noThewItem = "没有体力丹";
	public static String noNeed = "没必要使用";
	public static String noHorGain = "没有可领取的成就奖励。";
	public static String tael = "银两";
	public static String food = "粮食";
	public static String item = "物品";
	public static String minister = "大臣";
	public static String unlock = "解锁";
	public static String qmd = "亲密度";
	public static String notSell = "不出售此物品。";
	public static String buyException="购买异常请稍后再试";
	public static String noReward="该奖励不可领取";
	public static String forbidStr = "此号已封:";
	public static String forbidspeakStr = "禁止发言:";
	public static String errorroledata2Str = "找不到此用户。";
	public static String noitemStr01 = "没有小喇叭";
	public static String noitemStr02 = "没有大喇叭";
	public static String itemenough="对不起您没有该道具";
	public static String cannotuse="不可使用该物品";
	public static String noitemStr03="没有五石散";
	
	public static void init()
	{
		System.out.println("init language:" + lantype);
		if(lantype == LanType_Korea)
		{
			System.out.println("init language LanType_Korea");
			init_korea();
		}
	}
	
	public static void init_korea()
	{}
}
