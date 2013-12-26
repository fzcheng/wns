package cn.game.config;

import java.io.UnsupportedEncodingException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 各区服务器配置文件
 * @author fzcheng
 * 11:47:50 AM
 */
public class GameConfig {
	
	private static PropertiesConfiguration config = null;
	static {
			try {
//				config = new PropertiesConfiguration();
				config = new PropertiesConfiguration("game.properties");
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
//				config.addProperty("id", 1);
//				config.addProperty("ip", "112.65.128.98");
//				config.addProperty("port", 8888);
//				config.addProperty("name", 1);
//				config.addProperty("maxonline", 1);
//				config.addProperty("sectionip", "112.65.128.98");
//				config.addProperty("sectionport", 18567);
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
		
	public static int getStrKeyInteger(String key){
		try{
			return config.getInt(key);
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public static int getStrKeyInteger(String key, int defaultv){
		try{
			return config.getInt(key);
		}
		catch(Exception e)
		{
			return defaultv;
		}
	}
	
	public static String getStrKey(String values){
		String value = config.getString(values);
		if(value == null){
			return value;
		}
		try {
			value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static int getEnergyTime() {
		return GameConfig.getStrKeyInteger("energytime", 3600);
	}
	
	public static int getThewTime() {
		return GameConfig.getStrKeyInteger("thewtime", 3600);
	}
	
	public static int getGainTime() {
		return GameConfig.getStrKeyInteger("gaintime", 1800);
	}
	public static int getZouzhangTime() {
		return GameConfig.getStrKeyInteger("zouzhangtime", 3600);
	}

	public static int getLuckTime() {
		return GameConfig.getStrKeyInteger("lucktime", 3600);
	}
}
