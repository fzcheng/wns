package cn.game.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import cn.game.dao.FileDCountDAO;
import cn.org.util.SpringUtils;

public class FileDownloadAction extends DownloadAction {
	private Connection conn;
	private String path;
	private String filename;

	FileDCountDAO filedcountdao;

	// 获得某个文件的下载次数，其中id是文件名的hashcode
	private int getDownloadCount(int id) throws Exception {
		filedcountdao = (FileDCountDAO)SpringUtils.getBean("filedcountdao");
		return filedcountdao.getDownloadCount(id);
	}

	// 在文件完成下载后，将该文件的下载次数加1
	private void incDownloadCount() throws Exception {
		filedcountdao = (FileDCountDAO)SpringUtils.getBean("filedcountdao");
		int id = filename.hashCode();
		filedcountdao.incDownloadCount(id, filename);
	}

	// 下载文件时调用getStreamInfo方法
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//final String contentType = "application/file";
		final String contentType = "application/vnd.android.package-archive";
		// 建议设置content-disposition响应信息头，否则Web浏览器在下载文件时
		// 无法在保存文件对话框中显示正确的文件名
		response.setHeader("content-disposition", "attachment; filename="
				+ filename);
		
		response.setHeader("content-Type", "application/vnd.android.package-archive");
//		response.setHeader("content-Length", "12400096");
		response.setHeader("X-Cache-Lookup", "Hit From DiskCache");
		
//		response.setStatus(302);
//		response.setHeader("Location", "http://122.72.112.193/down.myapp.com/myapp/qqteam/AndroidQQ/qq_4.7.0.2155_android.apk?mkey=535e220c7eb90a2b&f=d688&p=.apk");
//		//User-Agent
//		//Mozilla/5.0 (Linux; U; Android 4.1.1; zh-cn; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/5.2.380
//		String ua = request.getHeader("User-Agent");
//		System.out.println("getStreamInfo:" + ua);

//		ServletOutputStream out = response.getOutputStream();
//		out.write(utf2Bytes("The actual URL is '/myapp/qqteam/AndroidQQ/qq_4.7.0.2155_android.apk'."));
//		out.flush();

		final FileInputStream fis = new FileInputStream(path + filename);
		
		incDownloadCount();
		return new DownloadAction.StreamInfo() // 使用隐式的方法实现了StreamInfo接口
		{
			public String getContentType() {

				return contentType;
			}

			public InputStream getInputStream() throws IOException {
				return fis;
			}
		};
	}

	// 如果Struts动作不加file请求参数，则通过execute方法将指定目录中文件列表输出到客户端
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		path = this.getServlet().getInitParameter("downloadPath");
		filename = request.getParameter("file");
		if (filename == null) {
			File file = new File(path);
			File[] files = file.listFiles();
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();

			out.println("<ul>");
			for (File f : files) // 开始向客户端浏览器输出文件列表
			{

				if (f.isFile() && !f.isHidden()) {
					out.println("<li><a href='"
							+ request.getContextPath()
							+ mapping.getPath()
							+ ".do?file="
							+ f.getName()
							+ "'>"
							+ f.getName()
							+ "</a>&nbsp;&nbsp;<font color='blue'>下载次数："
							+ String.valueOf(getDownloadCount(f.getName()
									.hashCode())) + "</color></li>");
				}
			}
			out.println("</ul>");
			return null;
		} else {
			if(!filename.endsWith(".apk"))
			{
				filename += ".apk";
			}
			// 当file参数存在时，则调用DownloadAction中的execute方法
			// 实际上，在DownloadAction类中的execute方法调用了getStreamInfo方法
			// 这条语句就相当于调用了getStreamInfo方法
			return super.execute(mapping, form, request, response);
		}
	}
	
	public static byte[] utf2Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}
}
