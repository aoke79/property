///////////////////////////////////////////////////////////////////////////////
// COPYRIGHT(C) 2011 SINOSOFT  CO., LTD.                                     //
//                                                                           //
// ALL RIGHTS RESERVED BY SINOSOFT  CO., LTD.                                //
// THIS PROGRAM MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH                //
// IT WAS FURNISHED BY SINOSOFT  CO., LTD.                                   //
// NO PART OF THIS PROGRAM MAY BE REPRODUCED OR DISCLOSED TO OTHERS,         //
// IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF SINOSOFT              //
//(CHINA) CO., LTD.                                                          //
//                                                                           //
// SINOSOFT CO., LTD. CONFIDENTIAL AND PROPRIETARY                           //
///////////////////////////////////////////////////////////////////////////////

/**
 * <p><b>Title</b>:  DateTool</p>
 * <p><b>Description</b>:  校验类</p>
 * <p><b>DATE</b>: 2011/04/11</p>
 * AUTHOR        : SinoSoft ruanruiwen
 * VERSION       : 1.0
 * <p><b>HISTORY</b>: </p>
 * 
**/
package com.sinoframe.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.FastHashMap;

public class ValidateUtil {
	
	private static ValidateUtil validateUtil = null;
	  public static char[][] specialCharactersRepresentation = new char[63][];
	private ValidateUtil() {
	}

	public static synchronized ValidateUtil getInstance() {
		if (validateUtil == null) {
			validateUtil = new ValidateUtil();
		}
		return validateUtil;
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj 判断对象
	 * @return 为空返回true,非空返回false
	 * @author wanglei
	 */
	public boolean checkNull(Object obj)
	{
		boolean flag = false;
		if(obj == null)
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str 要判断的字符串
	 * @return 为空返回true,非空返回false
	 * @author wanglei
	 */
	public boolean checkStringNull(String str)
	{
		boolean flag = false;
		if(checkNull(str) || str.trim().length() == 0)
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断日期是否合法
	 * @param dateStr 要判断的日期
	 * @return 合法返回true,非法返回false
	 * @author wanglei
	 */
	public boolean checkDate(String dateStr)
	{
		Pattern p = Pattern
		    .compile("(?:[0-9]{1,4}(?<!^0?0?0?0))-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8]| (?:(?<=(?:0[13578]|1[02])-)(?:29|3[01]))|(?:(?<=(?:0[469]|11)-)(?:29|30))| (?:(?<=(?:(?:[0-9]{0,2}(?!0?0)(?:[02468]?(?<![13579])[048]|[13579][26]))| (?:(?:[02468]?[048]|[13579][26])00))-02-)(?:29)))");
		Matcher   m   =   p.matcher(dateStr); 
		return m.find();
	}
	
	/**
	 * 判断email是否合法
	 * @param mail 要判断的email
	 * @return 合法返回true,非法返回false
	 * @author wanglei
	 */
	public boolean checkEmail(String mail){   
	    String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";   
	    Pattern   p   =   Pattern.compile(regex);   
	    Matcher   m   =   p.matcher(mail);   
	    return m.find();   
	}   
	
	/**
	 * 判断身份证是否合法
	 * @param cerCode 要判断身份证号码
	 * @return 合法返回true,非法返回false
	 * @author wanglei
	 */
	public boolean checkCerCode(String cerCode){   
		FastHashMap areaMap = null;
		areaMap = this.getAreaMap();
		String ereg ="";
		int s = 0;
		int y = 0;
		String m ="";
		//校验码
		String JYM = "10X98765432";
		boolean flag =false;
		String[] cerCodeArray = getStringArrayFromString(cerCode);
		//地区检验
		if(areaMap.get(cerCode.substring(0,2))==null)
		{
			return false;
		}
		try{
		switch(cerCode.length())
		{
			case 15:
				if ( (Integer.parseInt(cerCode.substring(6,8))+1900) % 4 == 0 || ((Integer.parseInt(cerCode.substring(6,8))+1900) % 100 == 0 && (Integer.parseInt(cerCode.substring(6,8))+1900) % 4 == 0 ))
				{
					ereg="[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}";//测试出生日期的合法性
				} else {
					ereg="[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}";//测试出生日期的合法性
				}
				Pattern   p   =   Pattern.compile(ereg);
				flag = p.matcher(cerCode).find();
				break;
			case 18:
				//18位身份号码检测
				//出生日期的合法性检查 
				//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
				//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
				if ( Integer.parseInt(cerCode.substring(6,10)) % 4 == 0 || (Integer.parseInt(cerCode.substring(6,10)) % 100 == 0 && Integer.parseInt(cerCode.substring(6,10))%4 == 0 )){
					ereg="[1-9][0-9]{5}[1-9]{1}[0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]";//闰年出生日期的合法性正则表达式
				} else {
					ereg="[1-9][0-9]{5}[1-9]{1}[0-9]{3}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]";//平年出生日期的合法性正则表达式
				}
				Pattern   pp   =   Pattern.compile(ereg);
				if(pp.matcher(cerCode).find()){//测试出生日期的合法性
						//计算校验位
						s = (Integer.parseInt(cerCodeArray[0]) + Integer.parseInt(cerCodeArray[10])) * 7
						+ (Integer.parseInt(cerCodeArray[1]) + Integer.parseInt(cerCodeArray[11])) * 9
						+ (Integer.parseInt(cerCodeArray[2]) + Integer.parseInt(cerCodeArray[12])) * 10
						+ (Integer.parseInt(cerCodeArray[3]) + Integer.parseInt(cerCodeArray[13])) * 5
						+ (Integer.parseInt(cerCodeArray[4]) + Integer.parseInt(cerCodeArray[14])) * 8
						+ (Integer.parseInt(cerCodeArray[5]) + Integer.parseInt(cerCodeArray[15])) * 4
						+ (Integer.parseInt(cerCodeArray[6]) + Integer.parseInt(cerCodeArray[16])) * 2
						+ Integer.parseInt(cerCodeArray[7]) * 1 
						+ Integer.parseInt(cerCodeArray[8]) * 6
						+ Integer.parseInt(cerCodeArray[9]) * 3 ;
						y = s % 11;
						m = "F";
						m = JYM.substring(y,y+1);//判断校验位
						if(m.equals(cerCodeArray[17]))
						{
							flag = true; //检测ID的校验位
						}
						else
						{
							flag = false;
						}
				}else
				{
					flag = false;;
				}
			  	break;
			default:
			  	flag = false;
                break;
		}
		}
		catch(Exception e)
		{
			flag = false;
		}
       return flag;   
	}
	
	/**
	 * 将字符串中每个字符存放在字符串数组中返回
	 * @param str
	 * @return
	 */
	private String[] getStringArrayFromString(String str)
	{
		String [] strArray = new String[1000];
		for(int i = 0;i<str.length();i++)
		{
			strArray[i]=str.substring(i,i+1);
		}
		return strArray;
	}
	
	/**
	 * 获取身份证地域代码
	 * @return
	 */
	private FastHashMap getAreaMap()
	{
		FastHashMap areaMap = new FastHashMap();
		areaMap.put("11","北京");
		areaMap.put("12","天津");
		areaMap.put("13","河北");
		areaMap.put("14","山西");
		areaMap.put("15","内蒙古");
		areaMap.put("21","辽宁");
		areaMap.put("22","吉林");
		areaMap.put("23","黑龙江");
		areaMap.put("31","上海");
		areaMap.put("32","江苏");
		areaMap.put("33","浙江");
		areaMap.put("34","安徽");
		areaMap.put("35","福建");
		areaMap.put("36","江西");
		areaMap.put("37","山东");
		areaMap.put("41","河南");
		areaMap.put("42","湖北");
		areaMap.put("43","湖南");
		areaMap.put("44","广东");
		areaMap.put("45","广西");
		areaMap.put("46","海南");
		areaMap.put("50","重庆");
		areaMap.put("51","四川");
		areaMap.put("52","贵州");
		areaMap.put("53","云南");
		areaMap.put("54","西藏");
		areaMap.put("61","陕西");
		areaMap.put("62","甘肃");
		areaMap.put("63","青海");
		areaMap.put("64","宁夏");
		areaMap.put("65","新疆");
		areaMap.put("71","台湾");
		areaMap.put("81","香港");
		areaMap.put("82","澳门");
		areaMap.put("91","国外");
		return areaMap;
		
	}
	
	/**
	 * 校验手机号码是否合法
	 * @param mobilePhone 待校验的手机号码
	 * @return 合法返回true,非法返回false
	 * @author wanglei
	 */
	public boolean checkMobilephone(String mobilePhone)
	{
		String regex = "^1(3|5|8)\\d{9}$";   
	    Pattern   p   =   Pattern.compile(regex);   
	    Matcher   m   =   p.matcher(mobilePhone);   
	    return m.find(); 
	}
	
	/**
	 * 校验电话号码号码是否合法
	 * @param linkPhone 待校验的电话号码
	 * @return 合法返回true,非法返回false
	 * @author wanglei
	 */
	public boolean checkLinkPhone(String linkPhone)
	{
		String regex = "^\\d{3}-\\d{8}$|^\\d{4}-\\d{7,8}$|^\\d{3}-\\d{8}-\\d{1,4}$|^\\d{4}-\\d{7,8}-\\d{1,4}$|^\\d{3}-\\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}-\\d{4}$|00|^\\+";   
	    Pattern   p   =   Pattern.compile(regex);   
	    Matcher   m   =   p.matcher(linkPhone);   
	    return m.find(); 
	}
	
	/**
	 * 校验字符串是否是数字
	 * @param str 待校验的字符串
	 * @return 是数字返回true,不是数字返回false
	 * @author wanglei
	 */
	public boolean checkNumber(String str)
	{
		String regex = "^\\d+(\\.\\d+)?$";   
	    Pattern   p   =   Pattern.compile(regex);   
	    Matcher   m   =   p.matcher(str);   
	    return m.find(); 
	}
	
	  public static String escapeXml(String buffer)
	  {
	    int start = 0;
	    int length = buffer.length();
	    char[] arrayBuffer = buffer.toCharArray();
	    StringBuffer escapedBuffer = null;

	    for (int i = 0; i < length; ++i) {
	      char c = arrayBuffer[i];
	      if (c <= '>') {
	        char[] escaped = specialCharactersRepresentation[c];
	        if (escaped == null)
	          continue;
	        if (start == 0) {
	          escapedBuffer = new StringBuffer(length + 5);
	        }

	        if (start < i) {
	          escapedBuffer.append(arrayBuffer, start, i - start);
	        }
	        start = i + 1;

	        escapedBuffer.append(escaped);
	      }

	    }

	    if (start == 0) {
	      return buffer;
	    }

	    if (start < length) {
	      escapedBuffer.append(arrayBuffer, start, length - start);
	    }
	    return escapedBuffer.toString();
	  }
	  static
	  {
	    specialCharactersRepresentation[38] = "&amp;".toCharArray();
	    specialCharactersRepresentation[60] = "&lt;".toCharArray();
	    specialCharactersRepresentation[62] = "&gt;".toCharArray();
	    specialCharactersRepresentation[34] = "&#034;".toCharArray();
	    specialCharactersRepresentation[39] = "&#039;".toCharArray();
	  }
}
