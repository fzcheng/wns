package cn.org.util;

import java.util.Map;
import java.util.TreeMap;

public class StringUtil {

	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getFromMap(TreeMap<String, Object> map) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if("empty".equals(entry.getKey()))
				continue;
			sbuffer.append(entry.getKey());
			//sbuffer.append("-");
			sbuffer.append(entry.getValue());
			//sbuffer.append("-");
		}
		
		String signTemp = sbuffer.toString();

		return signTemp;
	}
	
	public static String getFromMap(TreeMap<String, Object> map, String excludeKey) {
		StringBuffer sbuffer = new StringBuffer();
		//System.out.println("getFromMap");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(excludeKey.equals(entry.getKey()))
				continue;
			//System.out.println("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"");
			sbuffer.append(entry.getKey());
			//sbuffer.append("-");
			sbuffer.append(entry.getValue());
			//sbuffer.append("-");
		}
		
		String signTemp = sbuffer.toString();

		return signTemp;
	}
}
