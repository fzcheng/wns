/**
 * 
 */
package cn.game.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import cn.game.config.GameConfig;


/**
 * @author 侯浩军
 * 
 * 11:39:19 AM
 */
public class DCacheClient {
	Logger logger = Logger.getLogger("log.cachedao");
	
	public static DCacheClient dCacheClient;
	private static Memcached memcached = Memcached.getInstance();
	
	public static String GameKey = "ylxf|";
	static int areaid = GameConfig.getStrKeyInteger("id");
	
	//不同的分区不同的缓存key  用同一个缓存的时候必须区分
	static{
		GameKey += "a" + areaid;
	}
	
	public static DCacheClient getInstance(){
		if(DCacheClient.dCacheClient==null){
			DCacheClient.dCacheClient = new DCacheClient();
		}
		return DCacheClient.dCacheClient;
	}
	
	/**
	 * 将对象转义成为byet[]数组
	 * @param obj  
	 * @return
	 */
	public byte[] setSerialize(Serializable obj){
		try{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			oo.close();
			return bo.toByteArray();
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 在反编译回来
	 * @param byteobj
	 * @return
	 */
	public Object getSerialize(byte[] byteobj){
		try{
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteobj));
			byteobj = null;
			in.close();
			return in.readObject();
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 设置key对应的值
	 * @dcacheKey 关键字
	 * @dcacheValue 关键字对应的值 int 返回值 0 成功
	 */
	public int setCache(String dcacheKey,Serializable dcacheValue) {
		boolean iRet = false;
		try {	
			iRet = memcached.put(GameKey + formatKey(dcacheKey), dcacheValue);
		} catch (Exception e) {
			return -1;
		}
		return iRet?1:0;
	}
	
	/**
	 * 查找key对应的值
	 * 
	 * @Key 关键字
	 * @DcacheValueHolder 关键字对应的值，通过引用返回 int 返回值 0 成功 1 记录不存在 <0 其它错误
	 */
	public Object getCache(String key) {
		try {
			Object obj = memcached.get(GameKey + formatKey(key));
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 查找key对应的值
	 * 
	 * @Key 关键字
	 * @DcacheValueHolder 关键字对应的值，通过引用返回 int 返回值 0 成功 1 记录不存在 <0 其它错误
	 */
	public Object getCacheOnlyMemory(String key) {
		try {
			Object obj = memcached.get(GameKey + formatKey(key));
			
//			if(!(obj instanceof String))
//				return null;
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 设置key对应的值(支持无源工作方式)
	 * 
	 * @dcacheKey 关键字
	 * @dcacheValue 关键字对应的值 int 返回值 0 成功
	 */
	public int setCacheOnlyMemory(String dcacheKey,Serializable dcacheValue) {
		try {
			boolean iRet = memcached.put(GameKey + formatKey(dcacheKey), dcacheValue);
			//dcacheValue = null;
			return iRet?1:0;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 删除key对应的值，包括DB中的数据 
	 * 
	 * @dcacheKey 关键字
	 * @dcacheValue 关键字对应的值 int 返回值 0 成功
	 */
	public int delCache(String dcacheKey) {
		try {
			boolean iRet = memcached.delete(GameKey + formatKey(dcacheKey));
			return iRet?1:0;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 删除key对应的值(支持无源工作方式)
	 * 
	 * @dcacheKey 关键字
	 * @dcacheValue 关键字对应的值 int 返回值 0 成功
	 */
	public int delCacheOnlyMemory(String dcacheKey) {
		try {
			boolean iRet = memcached.delete(GameKey + formatKey(dcacheKey));
			return iRet?1:0;
		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	 * key中不能在使用"|"符号
	 * @param key
	 * @return
	 */
	public String formatKey(String key)
	{
		String result = key.replace("|", "_");
		return result;
	}
}
