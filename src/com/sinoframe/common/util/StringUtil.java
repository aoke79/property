/**
 * Title  StringUtil
 * Description  Utilities use for String tools
 */

package com.sinoframe.common.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	
	/**
	 * 私有构造方法，禁止实例化对象
	 */
	private StringUtil() {
		
	}
	
	/**
	 * 字符串中含有大写字符的转换成"-"加小写字符
	 * @param string
	 * @return
	 */
	public static String caseCharacter(String string) {
		String result = "";
		for (int i = 0; i < string.length(); i++) {
			char shortString = string.charAt(i);
			if(Character.isUpperCase(shortString)){
				result += "-"+Character.toLowerCase(shortString);
			}else{
				result += shortString;
			}
		}
		return result;
	}

	/**
	 * 根据.do格式的URL获取模块信息
	 * @param url
	 * @return
	 */
	public static String caseModule(String url) {
		String result = "";
		//若.do是按"xx-xx/xx!xx.do"形式
		if(url.contains("/")){
			url = url.replace("/", "&");
			String fieldName = url.substring(0, url.indexOf("&"));
			String[] blockField = fieldName.split("-");
			for (int i = 0; i < blockField.length; i++) {
				if(i < blockField.length && i > 0){
					for (int j = 0; j < blockField[i].length(); j++) {
						char shortString = blockField[i].charAt(j);
						if(j == 0){
							result += Character.toUpperCase(shortString);
						} else {
							result += shortString;
						}
					}
				} else {
					result = blockField[i] + result;
				}
			}
			result = result.replace("-", "");
		//若.do是按"xx-xx!xx.do"形式
		} else if(url.contains("!")) {
			url = url.substring(0, url.indexOf("!"));
			String[] blockField = url.split("-");
			for (int i = 0; i < blockField.length; i++) {
				if(i < blockField.length && i > 0){
					for (int j = 0; j < blockField[i].length(); j++) {
						char shortString = blockField[i].charAt(j);
						if(j == 0){
							result += Character.toUpperCase(shortString);
						} else {
							result += shortString;
						}
					}
				} else {
					result = blockField[i] + result;
				}
			}
			result = result.replace("-", "");
		//若.do是按"xx.do"形式
		} else {
			result = url.substring(0, url.indexOf(".do"));
		}
		
		return result;
	}
	
	/**
	 * 判断一个字符是否属于中文字符
	 * @param c
	 * @return
	 */
	public static boolean isChinese(Character c){
		if(String.valueOf(c).matches("[\u4e00-\u9fa5]")){
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串中重复的部分进行删除
	 * @param charString
	 * @return
	 */
	public static String removeSame(String charString) {
		String result = "";
		String[] stringArray = charString.split(",");
		Map<Integer,String> stringMap = new HashMap<Integer,String>();
		int sn = 0;
		for (int i = 0; i < stringArray.length; i++) {
			sn++;
			if(!stringMap.containsValue(stringArray[i])){
				stringMap.put(sn, stringArray[i]);
			}
		}
		for (Map.Entry<Integer, String> map : stringMap.entrySet()) {
			result += map.getValue() + ",";
		}
		result = result.substring(0, result.length()-1);
		return result;
	}
	
	/**
	 * 根据字符串数组拼接成连续的字符串
	 * @param smartType  Q: quote -- ()  M: mention  -- ''   A: all smart -- ('')
	 * @param stringArray
	 * @return
	 */
	public static String smartFormatString(String smartType, String[] stringArray) {
		String result = "";
		//根据样式处理数组
		for (int i = 0; i < stringArray.length; i++) {
			if(!smartType.equals("Q")){
				result += "'" + stringArray[i] + "',";
			} else {
				result += stringArray[i] +",";
			}
		}
		if(result.endsWith(",")){
			result = result.substring(0, result.lastIndexOf(","));
		}
		if(!smartType.equals("M")){
			result = "(" + result + ")";
		}
		return result;
	}
	
}
