package cn.game.lock;


public class TimeLockUtils {
	private static final LRUMap<String, Long> cache = new LRUMap<String, Long>(10000);

	/**
	 * 锁定
	 * @param key
	 * @param lockedMillise
	 * @return
	 */
	private static final boolean lock(String key, long lockedMillise) {
		return (cache.put(key, Long.valueOf(System.currentTimeMillis()
				+ lockedMillise)) != null);
	}

	/**
	 * 检查是否锁定
	 * @param key
	 * @return
	 */
	private static final boolean locked(String key) {
		if (!(cache.containsKey(key)))
			return false;

		Long timeMillis = cache.get(key);
		if (timeMillis == null)
			return false;

		if (System.currentTimeMillis() - timeMillis.longValue() > 0L) {
			cache.remove(key);
			return false;
		}
		return true;
	}

	/**
	 * 解锁
	 * @param key
	 * @return
	 */
	private static final boolean unlock(String key) {
		if (cache.containsKey(key))
			cache.remove(key);

		return true;
	}
	
	/**
	 * 返回锁定状态,未锁定则加锁
	 * @param key
	 * @param lockedMillise
	 * @return
	 */
	public static final boolean timeLock(String key,long lockedMillise){
		boolean flag = locked(key);
		if(!flag){
			lock(key, lockedMillise);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			if(timeLock("abc", 1000)){
				System.out.println("保护");
			}else{
				System.out.println("放行");
			}
			try {
				Thread.sleep(1010);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}