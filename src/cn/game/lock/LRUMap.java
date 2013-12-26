package cn.game.lock;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUMap<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8939241893756402092L;

	private final ReentrantLock lock = new ReentrantLock();
	private final int maxSize;

	public LRUMap(int initCapacity, int maxSize) {
		super(initCapacity, 0.75F, true);
		this.maxSize = maxSize;
	}

	public LRUMap(int maxSize) {
		super(maxSize >> 2, 0.75F, true);
		this.maxSize = maxSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return (size() >= this.maxSize);
	}

	@Override
	public V get(Object key) {
		try {
			this.lock.lock();
			return super.get(key);
		} finally {
			this.lock.unlock();
		}
	}

	@Override
	public V put(K key, V val) {
		try {
			this.lock.lock();
			return super.put(key, val);
		} finally {
			this.lock.unlock();
		}
	}
}