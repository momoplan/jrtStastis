package com.ruicai.basis.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * @author huangbotao
 *
 */
public class ConvertLang {
	
	public static final long ONE_DAY = 24 * 60 * 60 * 1000;

	/**
	 * 字符串转换成日期数据（用于比较）向后
	 * 
	 * @throws CommonException
	 */
	public static final String[] convertBackwardsDate(String inDate) {
		int in = convertint(inDate);
		String[] str = new String[2];
		Date date = new Date();
		str[0] = convertDate(date);
		str[1] = convertDate(new Date(date.getTime() + in * ConvertLang.ONE_DAY));
		return str;
	}

	/**
	 * 字符串转换成日期数据（用于比较）向前
	 * 
	 * @throws CommonException
	 */
	public static final String[] convertAlongDate(String inDate) {
		int in = convertint(inDate);
		String[] str = new String[2];
		Date date = new Date();
		str[0] = convertDate(new Date(date.getTime() - in * ConvertLang.ONE_DAY));
		str[1] = convertDate(date);
		return str;
	}

	/**
	 * 字符串类型转换成为Integer类型
	 * 
	 * @throws CommonException
	 */
	public static final Integer convertInteger(String str) {
		try {
			return str != null && !str.equals("") ? Integer.valueOf(str) : null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 字符串类型转换成为int类型
	 * 
	 * @throws CommonException
	 */
	public static final int convertint(String str) {
		if (convertInteger(str) == null) {

		}
		return convertInteger(str).intValue();
	}

	/**
	 * 字符串类型转换成为Double类型
	 * 
	 * @throws CommonException
	 */
	public static final Double convertDouble(String str) {
		try {
			return str != null && !str.equals("") ? Double.valueOf(str) : null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 字符串类型转换成为double类型
	 * 
	 * @throws CommonException
	 */
	public static final double convertdouble(String str) {
		if (convertDouble(str) == null) {

		}
		return convertDouble(str).doubleValue();
	}

	/**
	 * 将日期型转换成为字符串类型 yyyyMMdd
	 * 
	 * @throws CommonException
	 */
	public static final String convertDate(Date aDate) {
		return convertDate(aDate, "yyyyMMdd");
	}

	/**
	 * 将时间型转换成为字符串类型 HH:mm:ss
	 * 
	 * @throws CommonException
	 */
	public static final String convertTime(Date aDate) {
		return convertDate(aDate, "HH:mm:ss");
	}

	/**
	 * 将日期型转换成为字符串类型
	 * 
	 * @throws CommonException
	 */
	public static final String convertDate(Date aDate, String dataFormat) {
		try {
			SimpleDateFormat df = null;
			String returnValue = "";

			if (aDate != null) {
				df = new SimpleDateFormat(dataFormat);
				returnValue = df.format(aDate);
			}
			return returnValue;
		} catch (RuntimeException e) {
			return null;
		}

	}
	
	/**
	 * 将日期型转换成为字符串类型 yyyy-MM-dd HH:mm:ss
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateTimeYMDHMS(Date aDate) {
		return convertDate(aDate, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将日期型转换成为字符串类型 yyyy-MM-dd
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateTimeYMD(Date aDate) {
		return convertDate(aDate, "yyyy-MM-dd");
	}
	
	/**
	 * 将日期型转换成为字符串类型 MM月dd日
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateTimeMD(Date aDate) {
		return convertDate(aDate, "MM月dd日");
	}
	
	/**
	 * 将日期型转换成为字符串类型 HH:mm:ss
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateTimeHMS(Date aDate) {
		return convertDate(aDate, "HH:mm:ss");
	}

	/**
	 * 将日期+时间型转换成为字符串类型 yyyyMMddHHmmssS
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateTime(Date aDate) {
		return convertDate(aDate, "yyyyMMddHHmmssS");
	}
	
	/**
	 * 将字符串时间转换成为java.util.Date类型
	 * @param dateString yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static final Date convertDateTimeStr(String dateString) throws ParseException{
		//你需要的date类型是java.util.Date还是java.sql.Date类型？   
        DateFormat dateFormat;   
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        //"yyyy-MM-dd"为待转时间字符串的格式   
        java.util.Date timeDate = dateFormat.parse(dateString);
        //util类型需要java.sql.Date类型的可再转：   
        //java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());
        //sql类型
		return timeDate;
	}
	
	/**
	 * 将字符串时间转换成为java.util.Date类型
	 * @param dateString yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static final Date convertDateTimeStr_ymd(String dateString) throws ParseException{
		//你需要的date类型是java.util.Date还是java.sql.Date类型？   
        DateFormat dateFormat;   
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        //"yyyy-MM-dd"为待转时间字符串的格式   
        java.util.Date timeDate = dateFormat.parse(dateString);
        //util类型需要java.sql.Date类型的可再转：   
        //java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());
        //sql类型
		return timeDate;
	}

	/**
	 * 将字符串型式的日期转换成为指定的格式(转换前：06_11_03 转换后：6年11个月3天)
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateFormat(String date, String separator) {
		try {
			String[] s = date.split(separator);
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				if (!s[i].equals("00")) {
					if (s[i].startsWith("0")) {
						s[i] = s[i].substring(1, 2);
					}
					if (i == 0) {
						strb.append(s[i] + "年");
					}
					if (i == 1) {
						strb.append(s[i] + "个月");
					}
					if (i == 2) {
						strb.append(s[i] + "天");
					}
				}
			}
			return strb.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 将字符串型式的日期转换成为指定的格式(转换前：061103转换后6年11个月3天)
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateFormat(String date) {
		try {
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < date.length(); i = i + 2) {
				String s = date.substring(i, i + 2);
				if (!s.equals("00")) {
					if (s.startsWith("0")) {
						s = s.substring(1, 2);
					}
					if (i == 0 || i == 1) {
						strb.append(s + "年");
					}
					if (i == 2 || i == 3) {
						strb.append(s + "个月");
					}
					if (i == 4 || i == 5) {
						strb.append(s + "天");
					}
				}
			}
			return strb.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 将字符串型式的日期转换成为指定的格式(转换前：2006-11-03 2006/11/03 转换后：20061103)
	 * 
	 * @throws CommonException
	 */
	public static final String convertDate(String date, String separator) {
		try {
			String[] s = date.split(separator);
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				strb.append(s[i]);
			}
			return strb.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 将字符串型式的日期转换成为指定的格式(转换前：20061103 转换后：2006-11-03)
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateSeparator(String date,
			String separator) {

		try {
			StringBuffer strb = new StringBuffer(date.subSequence(0, 4)
					+ separator + date.substring(4, 6) + separator
					+ date.substring(6, 8));

			return strb.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 取得系统时间的上一个月
	 * 
	 * @throws CommonException
	 */
	public static final String convertDateLastMonnth() {

		try {
			Calendar cal = Calendar.getInstance();

			StringBuffer strb = new StringBuffer();

			strb.append(String.valueOf(cal.get(Calendar.YEAR)));

			strb.append(String.valueOf(cal.get(Calendar.MONTH)));

			return strb.toString();
		} catch (RuntimeException e) {
			return null;
		}

	}

	/**
	 * 
	 * @param doubleAdd
	 * @return
	 * @throws CommonException
	 */
	public static final Double convertDoubleAdd(Double[] doubleAdd) {

		try {
			double d = 0;

			for (int i = 0; i < doubleAdd.length; i++) {

				d = doubleAdd[i].doubleValue() + d;

			}

			return new Double(d);
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param doubleAdd
	 * @return
	 * @throws CommonException
	 */

	public static final Double convertDoubleProduct(Double[] doubleAdd) {

		try {
			double d = 1;

			for (int i = 0; i < doubleAdd.length; i++) {

				d = doubleAdd[i].doubleValue() * d;

			}

			return new Double(d);
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param args
	 * @throws CommonException
	 */
	public static final Double convertDoubleMinus(Double[] doubleAdd) {
		try {
			double d = 0;

			for (int i = 0; i < doubleAdd.length; i++) {

				d = doubleAdd[i].doubleValue() - d;

			}

			return new Double(d);
		} catch (RuntimeException e) {
			return null;
		}
	}
	
	/**
	 * 方法描述 将字符串转化为日期
	 * 
	 * @param dateStr
	 *            字符串格式的日期
	 * @return Long
	 * @exception ParseException
	 */
	public static long getDateLongTimeStr(String dateStr) {
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
			
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}
	/**
	 * 方法描述 将字符串转化为日期
	 * 
	 * @param dateStr
	 *            字符串格式的日期 yyyy-MM-dd HH:mm:ss
	 * @return Date
	 */
	public static Date getDateTimeStr(String dateStr) {
		DateFormat df = DateFormat.getDateTimeInstance();
		//DateFormat df=new DateFormat("yyyy-MM-dd HH:mm:ss");
		Date datim = null;
		try {
			datim = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return datim;
	}

	/**
	 * @interpret 传入Long型的时间转化为字符串型时间
	 * @param     Long date
	 * @return    String yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeStr(long date){

		java.util.Date Ldate = new java.util.Date(date)   ;  
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sLDate=sdf.format(Ldate);
		
		return sLDate;
	}
	
	/**
	 * @interpret 传入Long型时间,格式化的时间模式返回字符串时间
	 * @param     date
	 * @param     type  yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss.SSS 或 yyyy-MM-dd
	 * @return    String
	 */
	public static String getDateTime(long date,String type){
		
		java.util.Date Ldate = new java.util.Date(date);  
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(type);
		
		String sLDate=sdf.format(Ldate);
		
		return sLDate;
	}
	
	/**
	 * @interpret 传入Long型的时间转化为字符串型时间
	 * @param     Long date
	 * @return    String
	 */
	public static String getDateTimeStr_ymd(long date){
		
		java.util.Date Ldate = new java.util.Date(date)   ;  
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		
		String sLDate=sdf.format(Ldate);
		
		return sLDate;
	}
	
	/**
	 * @interpret 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp） 
	 * @param dateString 需要转换为timestamp的字符串:yyyy-MM-dd kk:mm:ss.SSS
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp getTimestampTimeStr(String dateString) 
	  throws java.text.ParseException {
	  DateFormat dateFormat;
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式
	  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util类型
	  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型
	  return dateTime;
	}

	/*
	 * 取得明天的日期（yyyy-MM-dd）
	 */
	public final static String getTomorrow() {
		// 得到明前的那天日期
		Date today = new Date();
		long time = 24 * 60 * 60 * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date weekDay = new Date(today.getTime() + time);
		return sdf.format(weekDay);
	}
	
	/*
	 * 取得今天的日期（yyyy-MM-dd）
	 */
	public final static String getToday() {
		// 得到明前的那天日期
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date weekDay = new Date(today.getTime());
		return sdf.format(weekDay);
	}
	
	/*
	 * 当前时间是否是今天的22:50:01到23:59:59之间
	 */
	public final static boolean isNullIssueTime() {
		String nowString = ConvertLang.convertDateTimeHMS(new Date(System.currentTimeMillis()));
		String startTime = "22:50:01";
		String overTime = "23:59:59";
		
		SimpleDateFormat sdf  =  new  SimpleDateFormat("HH:mm:ss", Locale.CHINA);   
		Date   dtStart = null;
		Date   dtOver = null;
		Date   now = null;
		try {
			dtStart = sdf.parse(startTime);
			dtOver = sdf.parse(overTime);
			now = sdf.parse(nowString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		boolean isNullIssueTime = (now.getTime() >= dtStart.getTime()) && (now.getTime() <= dtOver.getTime());
		return isNullIssueTime;
	}

	/**
	 * @interpert 取得7天后的日期（yyyy-MM-dd kk:mm:ss）
	 * @param     dateStr
	 * @return    String
	 */
	public final static String getWeekDay(String dateStr) {
		// 得到传入的日期
		Date today = ConvertLang.getDateTimeStr(dateStr);
		//7天的毫秒数
		long time = 7 * 24 * 60 * 60 * 1000;
		//初始化时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		
		Date weekDay = new Date(today.getTime() + time);
		//返回日期
		return sdf.format(weekDay);
	}
	
	public static String nextTime(String strTime) { 
        Calendar cal = Calendar.getInstance(); 
        Date date = new Date(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String nextTime = null;
        try { 
            date = sdf.parse(strTime); 
            cal.setTime(date); 
            cal.add(cal.DATE, 1);
            nextTime = sdf.format(cal.getTime());
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return nextTime; 
    } 
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(ConvertLang.getWeekDay("2009-07-31 13:00:00"));
	}
}
