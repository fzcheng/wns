package cn.org.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author LuZhiYong
 * @Date 2012-10-25
 */
public class ExportExcelUtil {

	/**
	 * 将数据转化成流
	 * @param list 数据
	 * @param title 标题
	 * @return
	 * @author LuZhiYong
	 * @Date 2012-10-25
	 */
	public static InputStream getExcelInputStream(List<String[]> list, String[] title) { 
		ByteArrayOutputStream out = new ByteArrayOutputStream();// 将OutputStream转化为InputStream 
		writeExcel(out, list, title); 
		return new ByteArrayInputStream(out.toByteArray()); 
	} 

	public static void writeExcel(OutputStream out, List<String[]> list, String[] title) { 
		try { 
			WritableWorkbook workbook = Workbook.createWorkbook(out); 
			WritableSheet ws = workbook.createSheet("sheet 1", 0); 
			
			int rowNum = 0; // 要写的行,第一行是从0开始，以此类推 
			
			if (title != null) { 
				putRow(ws, 0, title); 
				rowNum = 1; 
			} 
			
			for (int i = 0; i < list.size(); i++, rowNum++) { //数据写入Excel 
				Object[] cells = (Object[]) list.get(i); 
				putRow(ws, rowNum, cells); 
			} 
			
			workbook.write(); 
			workbook.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	private static void putRow(WritableSheet ws, int rowNum, Object[] cells) throws Exception { 
		for (int j = 0; j < cells.length; j++) { 
			Label cell = new Label(j, rowNum, String.valueOf(cells[j])); 
			ws.addCell(cell); 
		} 
	} 
//
//	} 
//
//	  SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
//	excelName = "Products_Export_"+ df.format(new java.util.Date()) + ".xls";//导出Excel文件名 
//	    List<InventoryTotalVO>  rowList=pageBean.getRows();//导出的记录 
//	    List<String[]>  extelDataList=new ArrayList<String[]>(); 
//	    for(int i=0;i<rowList.size();i++){ 
//	    extelDataList.add(data);//要写入Excel的数据 
//	    } 

}
