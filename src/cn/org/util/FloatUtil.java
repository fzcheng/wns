package cn.org.util;

import java.text.DecimalFormat;

public class FloatUtil {

	public static String format2string0(float scale) {
		DecimalFormat fnum = new DecimalFormat("##0");
		String dd = fnum.format(scale);
		return dd;
	}
	
	public static String format2string2(float scale) {
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String dd = fnum.format(scale);
		return dd;
	}
	
	
	public static void main(String[] args) {
		System.out.println(format2string0(32.321321f));
	}
}
