package com.ruicai.basis.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruicai.basis.common.ProUtil;
import com.ruicai.basis.common.StrReplace;

public class FileUrl {
	
	private static Log log = LogFactory.getLog(FileUrl.class);
	
	private StrReplace strreplace = new StrReplace();
	
	private ProUtil proutil = new ProUtil();
	/**
	 * @interpret 获取静态页的URI
	 * @return    String
	 */
	public String findUir(String uri,String systemname){
		
		String buff_ = null;
		
		String buff = new String(FileUrl.class.getResource("/").toString());
		
		buff_ = strreplace.replaceString(buff,"file:/", "");
		
		buff_ = strreplace.replaceString(buff_, "/WEB-INF/classes/", "");
		
		if(systemname.equals("Lunix")){
			buff_ = "/"+buff_ + proutil.getMessage(uri);
		}else if(systemname.equals("Windows")){
			buff_ = buff_ + proutil.getMessage(uri);
		}
		
		//log.info(buff_);

		return buff_;
	}
}
