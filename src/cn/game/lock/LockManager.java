package cn.game.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author key
 * 
 * 4:38:27 PM
 */
public class LockManager {
	private static ReentrantLock lock = new ReentrantLock();
	private static Map<String, String> map = new HashMap<String, String>();

	private static String put(String key, String value) {
		if (map.size() > 10000) {
			map.clear();
		}
		return map.put(key, value);
	}

	public static String get(String key) {
		try {
			lock.lock();
			if (!map.containsKey(key)) {
				put(key, key);
			}
			return map.get(key);
		} finally {
			lock.unlock();
		}
	}

	public static void remove(String key) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
	}
}
