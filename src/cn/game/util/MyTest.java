package cn.game.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;

/**
 *  
 * 只是写的一个示例，filePath,和FileName根据需要进行调整。
 */
public class MyTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //String str="http://192.168.11.198:8089/Default.aspx?id=1&user=2&type=3";
    	//String filePath="D:\\Wildlife.wmv";
        //String fileName="Wildlife.wmv";
        
    	//String urlstr="http://192.168.11.198:8089/mlh/uploadpic.do";
    	String urlstr="http://192.168.11.198:8089/mlh/role.do?comd=uploadphoto&albumId=1";
    	String filePath="D:\\aa.jpg";
        String fileName="aa.jpg";
        
//        try {
//            URL url=new URL(urlstr);
//            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.addRequestProperty("FileName", fileName);
//            connection.setRequestProperty("content-type", "text/html");
//            BufferedOutputStream  out=new BufferedOutputStream(connection.getOutputStream());
//            
//            //读取文件上传到服务器
//            File file=new File(filePath);
//            FileInputStream fileInputStream=new FileInputStream(file);
//            byte[]bytes=new byte[1024];
//            int b;
//            int len = 0;
//            while((len = fileInputStream.read(bytes,0,1024))>0)
//            //while((b = fileInputStream.read()) != -1)
//            {
//                out.write(bytes, 0, len);
//            	//out.write(b);
//                System.out.println();
//                System.out.println(len);
//                System.out.print("v:");
//                for(int ii = 0; ii < len; ii ++)
//                {
//                	System.out.print(bytes[ii] + ",");
//                }
//            }
//            out.flush();
//            fileInputStream.close();
//            //读取URLConnection的响应
//            DataInputStream in=new DataInputStream(connection.getInputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("comd", "uploadphoto");
        FormFile[] files = new FormFile[1];
        File file = new File("d://aa.jpg");
        files[0] = new FormFile("aaa.jpg", file, "bbb", "UTF-8");
        postFile(urlstr, params, files);
        
//        try {
//			//HttpURLConnection conn = (HttpURLConnection) HttpRequestUtil.sendGetRequest(urlstr, params, null);
//			
//			File file = new File("d://aa.jpg");
//		    FormFile formFile = new FormFile(file.getName(), file, "document", "text/plain");  
//		    boolean isSuccess = HttpRequestUtil.uploadFile(urlstr, params, formFile);  
//		    
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    private static final String CHARSET = "UTF-8";
    private static final String PREFIX = "--", LINEND = "\r\n";
    private static final String MULTIPART_FROM_DATA = "multipart/form-data";
    private static final String PROXY_NAME = "http.route.default-proxy";
    /**
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     * @param actionUrl
     * @param params
     * @param files
     * @return
     * @throws BizException
     */
    public static String postFile(String actionUrl, Map<String, String> params,
            FormFile[] files){

        String BOUNDARY = java.util.UUID.randomUUID().toString();

        URL uri = null;

        HttpURLConnection conn = null;
        DataOutputStream outStream = null;
        String result = "";
        try {
            uri = new URL(actionUrl);
            
            conn = (HttpURLConnection) uri.openConnection();
            
            conn.setInstanceFollowRedirects(true);
            // HttpURLConnection.setFollowRedirects(true);
            conn.setReadTimeout(30 * 1000); // 缓存的最长时�?
            conn.setDoInput(true);// 允许输入
            conn.setDoOutput(true);// 允许输出
            conn.setUseCaches(false); // 不允许使用缓�?

            
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charsert", CHARSET);
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
                    + ";boundary=" + BOUNDARY);
            
            conn.setRequestProperty("accept", "text/xml;text/html");
            //conn.setRequestProperty("Content-Type","text/xml;charset=utf-8");
            //conn.addRequestProperty("comd", "uploadphoto");
            
            conn.connect();
            // 首先组拼文本类型的参�?
            StringBuilder sb = new StringBuilder();
            if (params != null)
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINEND);
                    sb.append("Content-Disposition: form-data; name=\""
                            + entry.getKey() + "\"" + LINEND);
                    sb.append("Content-Type: text/plain; charset=" + CHARSET
                            + LINEND);
                    sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                    sb.append(LINEND);
                    sb.append(entry.getValue());
                    sb.append(LINEND);
                }

            outStream = new DataOutputStream(conn.getOutputStream());
            //outStream.write("?comd=uploadphoto".toString().getBytes());
            outStream.write(sb.toString().getBytes());
            // 发�?文件数据
            for (FormFile file : files) {
                StringBuilder split = new StringBuilder();
                split.append("--");
                split.append(BOUNDARY);
                split.append(LINEND);
//                split.append("Content-Disposition: form-data;name=\""
//                        + file.getFormname() + "\";filename=\""
//                        + file.getFileName() + "\"" + LINEND);
                split.append("Content-Disposition: form-data; filename=\""
                        + file.getFilname() + "\"" + LINEND);
                split.append("Content-Type: " + file.getContentType() + LINEND
                        + LINEND);
                outStream.write(split.toString().getBytes());
                
                if(file.getInStream()!=null){
	 	        	byte[] buffer = new byte[1024];
	 	        	int len = 0;
	 	        	while((len = file.getInStream().read(buffer, 0, 1024))!=-1){
	 	        		outStream.write(buffer, 0, len);
	 	        	}
	 	        	file.getInStream().close();
	 	        }else{
	 	        	outStream.write(file.getData(), 0, file.getData().length);
	 	        }
                
                //outStream.write(file.getData(), 0, file.getData().length);
                outStream.write(LINEND.getBytes());
            }

            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();
            // 得到响应�?
            int res = conn.getResponseCode();
//            if (res != HttpStatus.SC_OK
//                    && res != HttpStatus.SC_MOVED_PERMANENTLY
//                    && res != HttpStatus.SC_MOVED_TEMPORARILY)
//                throw new BizException(ExType.NET_ERROR);
            if (res == HttpStatus.SC_MOVED_PERMANENTLY
                    || res == HttpStatus.SC_MOVED_TEMPORARILY) {

                result = getString((conn.getURL().getProtocol() + "://"
                        + conn.getURL().getHost() + "/" + conn
                        .getHeaderField("Location")), null);
                return result;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[1024 * 4];
            int read_len = 0;
            while ((read_len = in.read(buffer)) != -1) {
                out.write(buffer, 0, read_len);
            }
            result = new String(out.toByteArray(), CHARSET);
            in.close();
            out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (Exception e) {
            }
            try {
                conn.disconnect();
            } catch (Exception e) {
            }
        }
        return result;
    }
    
    /**
     * Get方式提交，字符编码UTF-8
     * 
     * @param url
     * @param params
     * @return
     * @throws BizException
     */
    public static String getString(String url, Map<String, String> params){
//        return get(url, params, CHARSET);
    	return "error";
    }
    
//    /**
//     * Get方式提交
//     * 
//     * @param url
//     *            提交地址
//     * @param params
//     *            查询参数�? �?值对
//     * @param charset
//     *            参数提交编码�?
//     * @return 响应消息
//     * @throws BizException
//     */
//    public static String get(String url, Map<String, String> params,
//            String charset) {
//        if (url == null || "".equals(url)) {
//            return null;
//        }
//        String responseStr = "";
//        HttpClient httpClient = null;
//        HttpGet hg = null;
//        try {
//            List<NameValuePair> qparams = getParamsList(params);
//            if (qparams != null && qparams.size() > 0) {
//                charset = (charset == null ? CHARSET : charset);
//                String formatParams = URLEncodedUtils.format(qparams, charset);
//                url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams)
//                        : (url.substring(0, url.indexOf("?") + 1) + formatParams);
//            }
//            System.out.println("url == " + url);
//            httpClient = getDefaultHttpClient(charset);
//            //System.out.println("httpClient toString(): " + httpClient.toString());
//            
//            hg = new HttpGet(url);
//            
//            // 发�?请求，得到响�?
//            HttpResponse response = httpClient.execute(hg);
//            
////            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
////                throw new Exception();
////            }
//            responseStr = EntityUtils.toString(response.getEntity(), CHARSET);
//        } catch (Exception e) {
//        	System.out.println("请求出错： " + e.getMessage());
//        	//e.printStackTrace();
////            throw new ParseException();
//        } finally {
//            abortConnection(hg, httpClient);
//        }
//        return responseStr;
//    }
}
