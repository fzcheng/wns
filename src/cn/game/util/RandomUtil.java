package cn.game.util;

import java.util.Random;

public class RandomUtil {

	public static Random rand = null;
	
	public static String getRandom(int length) {
		if(rand == null)
		{
			rand = new Random();
			rand.setSeed(System.currentTimeMillis());
		}
		
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			boolean isChar = (rand.nextInt(2) % 2 == 0);// 输出字母还是数字
			if (isChar) { // 字符串
				int choice = rand.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				ret.append((char) (choice + rand.nextInt(26)));
			} else { // 数字
				ret.append(Integer.toString(rand.nextInt(10)));
			}
		}
		return ret.toString();
	}
	
	public static Random getRand()
	{
		if(rand == null)
		{
			rand = new Random();
			rand.setSeed(System.currentTimeMillis());
		}
		return rand;
	}
	
	/**
	 * 两个int之间随机数
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getIntRandom(int x,int y)
	{
		y = y + 1;
		return (int) (x + Math.random() * (y - x));
	}
	
	

	/**
	 * 获取一个随机数
	 * 
	 * @param num
	 * @return
	 */
	public static int getIntRandom(int num) 
	{
		int numa = getRand().nextInt(num);
		return numa;
	}
}
