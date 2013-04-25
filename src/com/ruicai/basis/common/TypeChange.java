package com.ruicai.basis.common;

public class TypeChange {

    //	change the Object type to the String type
	public static String objectToString(Object value){
		Object ob = value;
		if(ob==null)
			return "";
		else
			return ob.toString().trim();
	}
	
   //	change the Object type to the int type
	public static int objectToInt(Object value){
		return objectToInt(value,0);
	}
	
	public static int objectToInt(Object value,int othervalue){
		Integer integer = othervalue;		
		try{
		integer = Integer.valueOf(objectToString(value));
		}catch (Exception e) {
		}		
		return integer.intValue();
	}

	//	change the string type to the int type 
	public static int stringToInt(String intstr) {
		Integer integer;
		integer = Integer.valueOf(intstr);
		return integer.intValue();
	}

	//	change int type to the string type 
	public static String intToString(int value) {
		Integer integer = new Integer(value);
		return integer.toString();
	}

	//	change the string type to the float type 
	public static float stringToFloat(String floatstr) {
		Float floatee;
		floatee = Float.valueOf(floatstr);
		return floatee.floatValue();
	}

	//	change the float type to the string type 
	public static String floatToString(float value) {
		Float floatee = new Float(value);
		return floatee.toString();
	}

}
