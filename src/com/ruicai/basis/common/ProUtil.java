/*
 * shining
 * */
package com.ruicai.basis.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shining
 * @version Jun 5, 2007 10:48:44 AM
 * 
 */

public class ProUtil {
	private static Log log = LogFactory.getLog(ProUtil.class);

	public String getMessage(String key) {
		String message = null;
		Properties properties = new Properties();
		InputStream is = getClass().getResourceAsStream(
				"/language_cn.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			log.error("getMessage() fail", e);
			// e.printStackTrace();
		}
		message = properties.getProperty(key);
		try {
			message = new String(message.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			// e.printStackTrace();
		}

		return message;
	}
	
	public String getToMessage(String key,String uri) {
		String message = null;
		Properties properties = new Properties();
		InputStream is = getClass().getResourceAsStream(
				uri);
		try {
			properties.load(is);
		} catch (IOException e) {
			log.error("getMessage() fail", e);
			// e.printStackTrace();
		}
		message = properties.getProperty(key);
		try {
			message = new String(message.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			// e.printStackTrace();
		}

		return message;
	}
}
