package cn.game.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import cn.game.vo.sms.MessageVO;

public class JsonUtil {

	/**
	 * 
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * 
	 * @param pojoCalss
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {

		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}

	/**
	 * 
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static TreeMap<String, Object> getMap4Json(String jsonString) {
		
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<String> keyIter = jsonObject.keys();
		String key;
		Object value;
		TreeMap<String, Object> valueMap = new TreeMap<String, Object>();
		while (keyIter.hasNext())
		{
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}

	/**
	 * 
	 * 从json数组中得到相应java数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Object[] getObjectArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 
	 * 从json对象集合表达式中得到一个java对象列表
	 * 
	 * @param jsonString
	 * 
	 * @param pojoClass
	 * 
	 * @return
	 */
	public static List<Object> getList4Json(String jsonString, Class pojoClass) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		Object pojoValue;
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);
		}

		return list;
	}

	/**
	 * 
	 * 从json数组中解析出java字符串数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static String[] getStringArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		String[] stringArray = new String[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			stringArray[i] = jsonArray.getString(i);

		}

		return stringArray;
	}

	/**
	 * 
	 * 从json数组中解析出javaLong型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Long[] getLongArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Long[] longArray = new Long[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			longArray[i] = jsonArray.getLong(i);

		}

		return longArray;
	}

	/**
	 * 
	 * 从json数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Integer[] getIntegerArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Integer[] integerArray = new Integer[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			integerArray[i] = jsonArray.getInt(i);

		}

		return integerArray;
	}

//	/** */
//	/**
//	 * 
//	 * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
//	 * 
//	 * @param jsonString
//	 * 
//	 * @return
//	 */
//
//	public static Date[] getDateArray4Json(String jsonString, String DataFormat) {
//
//		JSONArray jsonArray = JSONArray.fromObject(jsonString);
//
//		Date[] dateArray = new Date[jsonArray.size()];
//
//		String dateString;
//
//		Date date;
//
//		for (int i = 0; i < jsonArray.size(); i++) {
//
//			dateString = jsonArray.getString(i);
//
//			date = DateUtil.stringToDate(dateString, DataFormat);
//
//			dateArray[i] = date;
//
//		}
//
//		return dateArray;
//
//	}

	/**
	 * 
	 * 从json数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static Double[] getDoubleArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Double[] doubleArray = new Double[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			doubleArray[i] = jsonArray.getDouble(i);

		}
		return doubleArray;
	}

	/**
	 * 
	 * 将java对象转换成json字符串
	 * 
	 * @param javaObj
	 * 
	 * @return
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {

		JSON json;

		JsonConfig jsonConfig = new JsonConfig();  
	    jsonConfig.setExcludes(new String[] { "jsonStr"});  
	       
		json = JSONSerializer.toJSON(javaObj, jsonConfig);

		return json.toString();
	}

//	/** */
//	/**
//	 * 
//	 * 将java对象转换成json字符串,并设定日期格式
//	 * 
//	 * @param javaObj
//	 * 
//	 * @param dataFormat
//	 * 
//	 * @return
//	 */
//
//	public static String getJsonString4JavaPOJO(Object javaObj,
//			String dataFormat) {
//
//		JSONObject json;
//
//		JsonConfig jsonConfig = configJson(dataFormat);
//
//		json = JSONObject.fromObject(javaObj, jsonConfig);
//
//		return json.toString();
//
//	}

	/** */
	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
//		List<MessageVO> list = new ArrayList<MessageVO>();
//		for(int i = 0; i < 3; i ++)
//		{
//			MessageVO msg = new MessageVO();
//			msg.setMissionId(i);
//			msg.setMobile("139"+i);
//			msg.setContent("content"+i);
//			
//			list.add(msg);
//		}
//		
//		String sss = JsonUtil.getJsonString4JavaPOJO(list);
//		System.out.println(sss);
		
		
		String result = "[{\"content\":\"content0\",\"missionId\":0,\"status\":0,\"mobile\":\"1390\"}," +
				"{\"content\":\"content1\",\"missionId\":1,\"status\":0,\"mobile\":\"1391\"}," +
				"{\"content\":\"content2\",\"missionId\":2,\"status\":0,\"mobile\":\"1392\"}]";
		List<Object> list = JsonUtil.getList4Json(result, MessageVO.class);
		
		List<MessageVO> msglist = new ArrayList<MessageVO>();
		for(Object obj : list)
		{
			MessageVO msg = (MessageVO)obj;
			msglist.add(msg);
		}
	}

	public static String getJsonStringFromMap(Map<String, Object> map) {
		return JSONObject.fromObject(map).toString();
	}

//	/** */
//	/**
//	 * 
//	 * JSON 时间解析器具
//	 * 
//	 * @param datePattern
//	 * 
//	 * @return
//	 */
//
//	public static JsonConfig configJson(String datePattern) {
//
//		JsonConfig jsonConfig = new JsonConfig();
//
//		jsonConfig.setExcludes(new String[] { "" });
//
//		jsonConfig.setIgnoreDefaultExcludes(false);
//
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//
//		jsonConfig.registerJsonValueProcessor(Date.class,
//
//		new DateJsonValueProcessor(datePattern));
//
//		return jsonConfig;
//
//	}
//
//	/** */
//	/**
//	 * 
//	 * 
//	 * 
//	 * @param excludes
//	 * 
//	 * @param datePattern
//	 * 
//	 * @return
//	 */
//
//	public static JsonConfig configJson(String[] excludes,
//
//	String datePattern) {
//
//		JsonConfig jsonConfig = new JsonConfig();
//
//		jsonConfig.setExcludes(excludes);
//
//		jsonConfig.setIgnoreDefaultExcludes(false);
//
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//
//		jsonConfig.registerJsonValueProcessor(Date.class,
//
//		new DateJsonValueProcessor(datePattern));
//
//		return jsonConfig;
//
//	}

}
