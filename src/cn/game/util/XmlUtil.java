package cn.game.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @author hongliang.dinghl Dom4j 生成XML文档与解析XML文档
 */
public class XmlUtil
{

	public static void createXml(String fileName) {
		Document document = DocumentHelper.createDocument();
		Element employees = document.addElement("employees");
		Element employee = employees.addElement("employee");
		employee.addAttribute("test", "true");
		
		Element flag = employees.addElement("flag");
		flag.setText("1");
		
		Element name = employee.addElement("name");
		name.setText("ddvip");
		name.addAttribute("test", "true");
		
		Element sex = employee.addElement("sex");
		sex.setText("m");
		Element age = employee.addElement("age");
		age.setText("29");
		try {
			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	public static Document parserXmlFile(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				if(!employee.isTextOnly())
				{
					List attList=employee.attributes();//获得所有属性  
			        for(Object e:attList){  
			            System.out.println(employee.getQName().getName()+"元素的"+((Attribute)e).getQName().getName()+"属性值为："+((Attribute)e).getValue());  
			        }
			        
					for (Iterator j = employee.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						System.out.println(node.getName() + ":" + node.getText());
					}
				}
				else
				{
					List attList=employee.attributes();//获得所有属性  
			        for(Object e:attList){  
			            System.out.println(employee.getQName().getName()+"元素的"+((Attribute)e).getQName().getName()+"属性值为："+((Attribute)e).getValue());  
			        }  
			        //输出子节点的文本数据  
			        //System.out.println(employee.getText());  
			             
					System.out.println(employee.getName() + ":" + employee.getText());
				}
				return document;
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("dom4j parserXml File");
		return null;
	}
	
	public static Document parserXml(InputStream in) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(in);
			Element employees = document.getRootElement();
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				if(!employee.isTextOnly())
				{
					for (Iterator j = employee.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						System.out.println(node.getName() + ":" + node.getText());
					}
				}
				else
				{
					System.out.println(employee.getName() + ":" + employee.getText());
				}

			}
			
			return document;
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("dom4j parserXml inputstream");
		
		return null;
	}
	
//	public void parserXmlString(String str) {
//		SAXReader saxReader = new SAXReader();
//		try {
//			Document document = saxReader.read(str.);
//			Element employees = document.getRootElement();
//			for (Iterator i = employees.elementIterator(); i.hasNext();) {
//				Element employee = (Element) i.next();
//				for (Iterator j = employee.elementIterator(); j.hasNext();) {
//					Element node = (Element) j.next();
//					System.out.println(node.getName() + ":" + node.getText());
//				}
//
//			}
//		} catch (DocumentException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("dom4j parserXml inputstream");
//	}
	
	public static void main(String[] args) {
		XmlUtil demo = new XmlUtil();
		demo.createXml("xmltest.xml");
		Document document = demo.parserXmlFile("xmltest.xml");
		System.out.println(document.asXML());
	}
}
