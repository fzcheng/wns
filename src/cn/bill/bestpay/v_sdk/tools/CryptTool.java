package cn.bill.bestpay.v_sdk.tools;

import java.security.MessageDigest;

public class CryptTool
{
  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
    "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

  public static String byteArrayToHexString(byte[] b)
  {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToHexString(b[i]));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public static byte[] md5Digest(byte[] src)
    throws Exception
  {
    MessageDigest alg = 
      MessageDigest.getInstance("MD5");

    return alg.digest(src);
  }

  public static String md5Digest(String src)
    throws Exception
  {
    return byteArrayToHexString(md5Digest(src.getBytes()));
  }
}
