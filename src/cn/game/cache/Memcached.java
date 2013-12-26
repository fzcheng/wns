package cn.game.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cn.org.util.SpringUtils;

/**
 * Memcached 客户端管理类 
 * 
 * 非存储模块缓存使用ttMemcachedClient
 * @author fzcheng
 * 5:00:42 PM
 */
public class Memcached{

	private MemcachedClient memcachedClient; 	//无源 
	
	public static Memcached memcached;
	
	/**
	 * 获得一个单例对象
	 * @return Memcached
	 */
	public static Memcached getInstance(){
		if(Memcached.memcached==null){
			Memcached.memcached = (Memcached)SpringUtils.getBean("memcached");
		}
		return Memcached.memcached;
	}	
	
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * 往缓存中放入一个对象(无源)
	 * @param key
	 * @param obj
	 * @return 成功或者失败
	 */
	public boolean put_memory(String key,Serializable obj)
	{
		try 
		{
			return memcachedClient.set(key, 0, obj);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 往缓存中放入一个对象(有源)
	 * @param key
	 * @param obj
	 * @return 成功或者失败
	 */
	public boolean put(String key,Serializable obj)
	{
		try 
		{
			String buff = this.setSerialize(obj);
			return memcachedClient.set(key, 0, buff);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据key从缓存中取出一个对象(无源)
	 * @param key
	 * @return Object
	 */
	public Object get_memory(String key)
	{
		try {
			return memcachedClient.get(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 根据key从缓存中取出一个对象(有源)
	 * @param key
	 * @return Object
	 */
	public Object get(String key)
	{
		try {
			String obj =memcachedClient.get(key);
			if(obj == null)
			{
				return null;
			}
			return this.getSerialize(obj);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 从缓存中根据key删除一个对象(无源)
	 * @param key
	 * @return 成功失败
	 */
	public boolean delete_memory(String key)
	{
		try {
			return memcachedClient.delete(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 从缓存中根据key删除一个对象（有源）
	 * @param key 
	 * @return 成功失败
	 */
	public boolean delete(String key)
	{
		try {
			return memcachedClient.delete(key);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (MemcachedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 有源反序列化
	 * @param byteobj
	 * @return
	 */
	public Object getSerialize(String obj){
		try{
			byte[] buff=new BASE64Decoder().decodeBuffer(obj);
			ByteArrayInputStream bais=new ByteArrayInputStream(buff);
			ObjectInputStream in = new ObjectInputStream(bais);
			in.close();
			return in.readObject();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将对象转义成为byet[]数组
	 * @param obj  
	 * @return
	 */
	public String setSerialize(Serializable obj){
		try{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			oo.close();
			String object= new BASE64Encoder().encode(bo.toByteArray());
			return object;
		}catch(Exception e){
			return null;
		}
	}
}

