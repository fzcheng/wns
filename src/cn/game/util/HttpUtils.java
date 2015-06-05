package cn.game.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * <p>Title: </p>
 * <p>Description: http utils </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author LiLu
 * @version 1.
 */
public class HttpUtils
{

	private static final String URL_PARAM_CONNECT_FLAG = "&";
	private static Log log = LogFactory.getLog(HttpUtils.class);

	private HttpUtils()
	{
	}

	/**
	 * GET METHOD
	 * @param strUrl String
	 * @param map Map
	 * @throws IOException
	 * @return List
	 */
	public static List URLGet(String strUrl, Map map) throws IOException
	{
		String strtTotalURL = "";
		List result = new ArrayList();
		if (strtTotalURL.indexOf("?") == -1)
		{
			strtTotalURL = strUrl + "?" + getUrl(map);
		}
		else
		{
			strtTotalURL = strUrl + "&" + getUrl(map);
		}
		log.debug("strtTotalURL:" + strtTotalURL);
		URL url = new URL(strtTotalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
		HttpURLConnection.setFollowRedirects(true);
		BufferedReader in = new BufferedReader(new InputStreamReader(con
				.getInputStream()));
		while (true)
		{
			String line = in.readLine();
			if (line == null)
			{
				break;
			}
			else
			{
				result.add(line);
			}
		}
		in.close();
		return (result);
	}

	/**
	 * POST METHOD
	 * @param strUrl String
	 * @param content Map
	 * @throws IOException
	 * @return List
	 */
	public static String URLPost(String strUrl, Map map) throws IOException
	{
		String content = "";
		content = getUrl(map);
		String totalURL = null;
		if (strUrl.indexOf("?") == -1)
		{
			totalURL = strUrl + "?" + content;
		}
		else
		{
			totalURL = strUrl + "&" + content;
		}
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setAllowUserInteraction(false);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=GBK");
		BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con
				.getOutputStream()));
		bout.write(content);
		bout.flush();
		bout.close();
		BufferedReader bin = new BufferedReader(new InputStreamReader(con
				.getInputStream()));
		String line = bin.readLine();
		bin.close();
		return line;
	}
	
	public static String URLPost_JSON(String strUrl, String jsonData) throws IOException
	{
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestProperty("content-type", "application/octet-stream");
		System.out.println("start open connection.");

		OutputStream out = con.getOutputStream();
		out.write(jsonData.getBytes("UTF-8"));
		out.flush();//发送
		System.out.println("send json Data.");

		InputStream in = con.getInputStream();
		byte[] b = new byte[512];
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		in.available();
		System.out.println("start read...");
		int k = 0;//接收
		while ((k = in.read(b)) != -1)
			bao.write(b, 0, k);

		byte[] result = bao.toByteArray();

		in.close();
		out.close();
		con.disconnect();
		return new String(result);
	}

	private static String getUrl(Map map)
	{
		if (null == map || map.keySet().size() == 0)
		{
			return ("");
		}
		StringBuffer url = new StringBuffer();
		Set keys = map.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
			String key = String.valueOf(i.next());
			if (map.containsKey(key))
			{
				Object val = map.get(key);
				String str = val != null ? val.toString() : "";
				try
				{
					str = URLEncoder.encode(str, "GBK");
				}
				catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(
						URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = "";
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(""
				+ strURL.charAt(strURL.length() - 1)))
		{
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		return (strURL);
	}

	public static String URLGetSZF(String strUrl, Map map) throws IOException
	{
		String strtTotalURL = "";
		List result = new ArrayList();
		if (strtTotalURL.indexOf("?") == -1)
		{
			strtTotalURL = strUrl + "?" + getUrl(map);
		}
		else
		{
			strtTotalURL = strUrl + "&" + getUrl(map);
		}
		log.debug("strtTotalURL:" + strtTotalURL);
		URL url = new URL(strtTotalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
		HttpURLConnection.setFollowRedirects(true);
		String line = con.getHeaderField("szfResponseCode");
		return line;
	}
}
