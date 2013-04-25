package com.ruicai.basis.common.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 常用的工具类
 * 
 * @company 北京金软瑞彩科技有限公司
 * @author 丁俊杰
 * @createDate 2010-11-8
 */
public class CommonUtil {
	public static long oneday = 0x5265c00L;

	/**
	 * 
	 @author 丁俊杰
	 * @createDate 2010-11-8
	 * @param beginDate
	 * @param endDate
	 * @param formatString
	 * @return
	 * @throws Exception
	 *             根据开始 结束 日期 计算天数
	 */
	public static Long getIntervalDays(String beginDate, String endDate,
			String formatString) throws Exception {

		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar
				.setTimeInMillis(stringToDateLong(beginDate, formatString));
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTimeInMillis(stringToDateLong(endDate, formatString));
		Long interval = (Long) ((endCalendar.getTimeInMillis() - beginCalendar
				.getTimeInMillis()) / oneday);
		return interval;
	}

	/**
	 * 
	 @author 丁俊杰
	 * @createDate 2010-11-8
	 * @param strDate
	 * @param formatString
	 * @return
	 * @throws Exception
	 *             将字符型的日期转化为 Long 型的时间
	 */
	public static long stringToDateLong(String strDate, String formatString)
			throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		return formatter.parse(strDate).getTime();
	}

	/**
	 * 
	 @author 丁俊杰
	 * @createDate 2010-11-8
	 * @param checkStr
	 * @return 验证字符串是否为null或空
	 */
	public static boolean checkIsEmpty(String checkStr) {
		boolean isEmpty = false;
		if (checkStr != null && !"".equals(checkStr.trim())) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	}

	/**
	 * 字符串转化成Long
	 * 
	 * @author 丁俊杰
	 * @createDate 2010-11-9
	 * @param str
	 * @return
	 */
	public static Long strToLong(String str) {
		Long strLong = new Long(0);
		if (str != null && !"".equals(str.trim())) {
			strLong = Long.parseLong(str);
		} else {
			strLong = new Long(0);
		}
		return strLong;
	}
	/**
	 * 字符串转化成Double 
	   @author       丁俊杰
	   @createDate   2010-11-9
	   @param str
	   @return
	 */
	public static Double strToDouble(String str){
		Double strDouble = new Double(0.00);
		if(str != null &&!"".equals(str.trim())){
			strDouble = (Double)Double.parseDouble(str);
		}
		return strDouble;
	}
	/**
	 * 格式化Double
	   @author       丁俊杰
	   @createDate   2010-11-10
	   @param formatDoubel
	   @param formatStr
	   @return
	 */
	public static Double formatDouble(Double formatDoubel,String formatStr){
		DecimalFormat format = new DecimalFormat(formatStr); 
		Double formatDouble = Double.valueOf(format.format(formatDoubel));
		return formatDouble;
	}
	
	/**
	 * 计算两个日期之间的月差
	 */
	public static int dispersionMonth2(String strDate1, String strDate2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(DateFormat.getDateInstance().parse(
					strDate1));

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(DateFormat.getDateInstance().parse(
					strDate2));

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
					.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
					.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
						.get(Calendar.YEAR))
						* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH)
						- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
		}
		return iMonth;
	}

}
