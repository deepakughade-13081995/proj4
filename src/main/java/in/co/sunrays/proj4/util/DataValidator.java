package in.co.sunrays.proj4.util;

import java.util.Date;

//import com.sun.org.apache.bcel.internal.generic.ISUB;

/**
   * This class validates input data
   * 
   * @author SunilOS
   * @version 1.0
   * @Copyright (c) SunilOS
   */

public class DataValidator {
	
   /**
    * Checks if value is Null
    * 
    * @param val
    * @return
    */
    
public static boolean isNull(String val){
	if(val==null || val.trim().length()==0){
		
		return true;
		}
	else
	{
		return false;
}
}

    /**
     * Checks if value is NOT Null
     * 
     * @param val
     * @return
     */

public static boolean isNotNull(String val){
	
	return !isNull(val);
}

    /**
     * Checks if value is an Integer
     * 
     * @param val
     * @return
     */

public static boolean isInteger(String val){
	
   if(isNotNull(val)){
	   try{
		   int i=Integer.parseInt(val);
		   return true;}
	   catch(NumberFormatException e){
		   return false;
	   }
   }else{
	   return false;
	   }
   }
/**
 * Checks if value is Long
 * 
 * @param val
 * @return
 */

public static boolean isLong(String val){
	
	if(isNotNull(val)){
		try{
			long i=Long.parseLong(val);
			return true;}
		catch(NumberFormatException e){
		return false;
		}
	}
		else{
			return false;
		}
	}


/**
 * Checks if value is valid Email ID
 * 
 * @param val
 * @return
 */

public static boolean isEmail(String val){
	
	 String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	 if(isNotNull(val)){
		 try{
			 return val.matches(emailreg);
		 }
		 catch(NumberFormatException e){
			 return false;
		 }
	 }
		 else{
			 return false;
			 
		 }
	 }


public static boolean isDate(String val){
	
	Date d=null;
	if(isNotNull(val)){
		d=DataUtility.getDate(val);
	}
	return d!=null;
	}


/**
 * Checks if value is roll number
 * 
 * @param val
 * @return
 */

public static boolean isRollNo(String roll) { // my method created
	String rollreg = "^((?=.*\\d).{1,4}(?=.*[A-Z]).{1,2}(?=.*\\d).{1,6})$";
	String sroll = roll.trim();

	if (isNotNull(sroll)) {

		boolean check = sroll.matches(rollreg);
		//System.out.println(check);
		return check;
	}

	else {

		return false;
	}
}

/**
 * Checks if value is password
 * 
 * @param val
 * @return
 */


public static boolean isPassword(String pass) { // my method created

	String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
	String spass = pass.trim();
	int checkIndex = spass.indexOf(" ");

	if (isNotNull(spass) && spass.matches(passreg) && checkIndex == -1) {
		return true;
	}

	else {
		return false;
	}
}

/**
 * Checks if value is name
 * 
 * @param val
 * @return
 */


public static boolean isName(String name) { // my method created

	String namereg = "^[a-zA-Z_-]+$";
	//String namer ="^[a-zA-Z_-]+$";


	String sname = name.trim();

	if (isNotNull(sname) && sname.matches(namereg)) {

		return true;
	} else {
		return false;
	}
}

public static boolean isName1(String name) { // my method created

	String namereg = "^[^-\\s][\\p{L} .']+$";
	//String namer ="^[a-zA-Z_-]+$";

	String sname = name.trim();

	if (isNotNull(sname) && sname.matches(namereg)) {

		return true;
	} else {
		return false;
	}
}

public static boolean isNotNum(String name) { // my method created

	String namereg = "^([^0-9]*)$";
	//String namer ="^[a-zA-Z_-]+$";


	String sname = name.trim();

	if (isNotNull(sname) && sname.matches(namereg)) {

		return true;
	} else {
		return false;
	}
}

public static boolean isNotSpecialChar(String name) { // my method created

	String namereg = "^[_A-z0-9]*((-|/s)*[_A-z0-9])*$";
	//String namer ="^[a-zA-Z_-]+$";


	String sname = name.trim();

	if (isNotNull(sname) && sname.matches(namereg)) {

		return true;
	} else {
		return false;
	}
}



public static boolean isAddress(String name) { // my method created

	String namereg = "^[0-9a-zA-Z\\s,-]+$";
	//String namer ="^[a-zA-Z_-]+$";


	String sname = name.trim();

	if (isNotNull(sname) && sname.matches(namereg)) {

		return true;
	} else {
		return false;
	}
}



public static boolean isMobileNum(String mob){
	String mobilereg="^[6-9][0-9]{9,12}$";
	if(isNotNull(mob)&& mob.matches(mobilereg)){
		return true;
	}
	else{
		return false;
	}
}


/**
 * Test above methods
 * 
 * @param args
 */

public static void main(String[] args) {
	/*System.out.println(isNull(""));
	System.out.println(isNotNull("sdfgh"));
	System.out.println(isInteger("12"));
	System.out.println(isLong("12.3"));
	System.out.println(isEmail("deepak200@gmail.com"));
    System.out.println(isDate("2/4/20"));*/
	//System.out.println(isRollNo("0156it07101"));
	//System.err.println(isAddress("61 Park Street Camden ME US "));
	//System.out.println(isMobileNum("9827541300"));
	System.out.println(isNotNum("@"));
	//System.out.println(isNotSpecialChar("dwkdmkmkm"));
}
}
