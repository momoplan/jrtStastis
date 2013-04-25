package com.ruicai.basis.common;

import java.io.*;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;

import org.dom4j.io.XMLWriter;
import org.dom4j.io.SAXReader;

public class ModelReder_ {
    
    public static void main(String[] args) {
        
        try {
            
        	ModelReder_ modelReder = new ModelReder_();
        	
            modelReder.WriteCountdown(modelReder.ReadCountdown());
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
    }
    
    /**
     * @interpret 参与人数XML文件内容读取
     * @return    String
     * @throws    Exception
     */
    public String testRead() throws Exception{
        
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("output.xml"));
        
        Element root = document.getRootElement();
        
        for (Iterator iter = root.elementIterator(); iter.hasNext();) {
            Element element = (Element) iter.next();
            //System.out.println(element.getText());
            //System.out.println(element.attributeValue("name"));
            //System.out.println(element.attributeValue("blog"));
        }
        return "";
        
    }
    
    /**
     * @interpret 参与人数XML文件写入[人数自增1]
     * @throws    Exception
     */
    public void testWrite() throws Exception{
        
        Document document = DocumentHelper.createDocument();
        
        Element root = document.addElement("root");
        
        Element element1 = root.addElement("number")
        //.addAttribute("name","Alexander")
        //.addAttribute("blog", "http://netnova.blogjava.net")
        .addText("1");

        
        XMLWriter writer = new XMLWriter(new FileOutputStream("output.xml"));
        
        writer.write(document);
        writer.close();    
        
    }
    
    /**
     * @interpret 从XML文件中取当期结束时间
     * @return    String
     * @throws    Exception
     */
    public String ReadCountdown() throws Exception{
        
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream("countdown.xml"));
        
        Element root = document.getRootElement();
        
        String time = null;
        
        for (Iterator iter = root.elementIterator(); iter.hasNext();) {
        	
            Element element = (Element) iter.next();
            
            time = element.getText();
        }
        return time;
    }
    
    /**
     * @interpret 传入上期时间,推算出当期截止时间.修改开奖倒计时文件本期截止时间,例如: (2009-07-31 00:00:00)
     * @throws    Exception
     */
    public void WriteCountdown(String time) throws Exception{
    	
    	//根据传入的上期截止时间,推算当期截止时间
    	String weekday = ConvertLang.getWeekDay(time);
        
        Document document = DocumentHelper.createDocument();
        
        Element root = document.addElement("root");
        
        Element element1 = root.addElement("weekday").addText(weekday);

        XMLWriter writer = new XMLWriter(new FileOutputStream("countdown.xml"));
        
        writer.write(document);
        writer.close();
    }
}
