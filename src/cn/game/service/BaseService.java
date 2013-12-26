package cn.game.service;

public class BaseService {
	public static String getHexStr(String[] strings) {
		String result = "";
		for(String str : strings)
		{
			result += str + "&";
		}
		return result;
	}
}
