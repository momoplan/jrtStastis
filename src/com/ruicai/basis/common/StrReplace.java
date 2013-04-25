package com.ruicai.basis.common;

import java.util.StringTokenizer;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author  huangbotao
 * @version 1.0
 */

public class StrReplace {

	public StrReplace() {
	}

	public static String replaceChar(String from, String to, String source) {
		StringBuffer bf = new StringBuffer("");
		StringTokenizer st = new StringTokenizer(source, from, true);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			System.out.println("*" + tmp);
			if (tmp.equals(from)) {
				bf.append(to);
			} else {
				bf.append(tmp);
			}
		}
		return bf.toString();
	}

	public static String replaceString(String rStr, String rFix, String rRep) {
		int l = 0;
		String gRtnStr = rStr;
		do {
			l = rStr.indexOf(rFix, l);
			if (l == -1)
				break;
			gRtnStr = rStr.substring(0, l) + rRep
					+ rStr.substring(l + rFix.length());
			l += rRep.length();
			rStr = gRtnStr;
		} while (true);
		return gRtnStr.substring(0, gRtnStr.length());

		//		StrReplace(text,"\n","<br>");   
	}
}

//使用方法
//<jsp:useBean id="replace" scope="page" class="forum.StrReplace" />
//<%= replace.str_replace("<","^","<h1>123456</h1>") %> 
