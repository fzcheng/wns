package cn.game.util;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	/**
	 * 
	 * 将java对象转换成json字符串
	 * 
	 * @param javaObj
	 * 
	 * @return
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(javaObj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	 /**
     * 反序列化POJO或简单Collection如List<String>.
     *
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     *
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
     * @see #fromJson(String, JavaType)
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			System.out.println("parse json string error:" + jsonString);
			return null;
		}
	}
	
    /**
	 * 
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * 
	 * @param jsonString
	 * 
	 * @return
	 */
	public static TreeMap<String, Object> getMap4Json(String jsonString) {

//		System.out.println("getMap4Json");
		ObjectMapper mapper = new ObjectMapper();
		//String s = "{ \"name\" : \"萧远山\", \"sex\" : \"男\", \"age\" : \"23\",\"address\" : \"河南郑州\"}";
		try {
			TreeMap<String, Object> map = mapper.readValue(jsonString, new TypeReference<TreeMap<String, Object>>(){});
			//Map<String,User> result = mapper.readValue(src, new TypeReference<Map<String,User>>() { });
			
//			System.out.println(map.size());
//			Iterator<?> iterator = map.keySet().iterator();
//			while (iterator.hasNext()) {
//				Object key = iterator.next();
//				System.out.print(key + ":");
//				System.out.println(map.get(key).toString());
//			}
			
			return map;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static void main(String[] args) {
		
	}
}
