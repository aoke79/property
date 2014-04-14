package com.sinoframe.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.id.Hex;
import org.apache.commons.lang.xwork.ArrayUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sinoframe.common.jbpm4.ProcessBase;

public class Util {
	public Util() {
	}

	public static boolean validateUserName(String username) {
		Pattern p = Pattern.compile("^\\w+$");
		Matcher m = p.matcher(username);
		return m.find();
	}

	public static String Array2String(String values[]) {
		String result = "";
		if (values == null)
			return result;
		int len = values.length;
		for (int i = 0; i < len; i++)
			result = result + values[i] + ",";

		if (result.endsWith(","))
			result = result.substring(0, result.length() - 1);
		return result;
	}

	public static String Array2String(Object values[]) {
		String result = "";
		if (values == null)
			return result;
		int len = values.length;
		for (int i = 0; i < len; i++)
			result = result + values[i].toString() + ",";

		if (result.endsWith(","))
			result = result.substring(0, result.length() - 1);
		return result;
	}

	public static String Array2String(List values) {
		String result = "";
		if (values == null)
			return result;
		int len = values.size();
		for (int i = 0; i < len; i++)
			result = result + values.get(i).toString() + ",";

		if (result.endsWith(","))
			result = result.substring(0, result.length() - 1);
		return result;
	}

	public static String base64Encode(String txt) {
		if (txt != null && txt.length() > 0)
			txt = (new BASE64Encoder()).encode(txt.getBytes());
		return txt;
	}

	public static String base64Encode(byte txt[]) {
		String encodeTxt = "";
		if (txt != null && txt.length > 0)
			encodeTxt = (new BASE64Encoder()).encode(txt);
		return encodeTxt;
	}

	public static String base64decode(String txt) {
		if (txt != null && txt.length() > 0)
			try {
				byte buf[] = (new BASE64Decoder()).decodeBuffer(txt);
				txt = new String(buf);
			} catch (IOException ioexception) {
			}
		return txt;
	}

	public static byte[] base64decodebyte(String txt) {
		byte buf[] = (byte[]) null;
		if (txt != null && txt.length() > 0)
			try {
				buf = (new BASE64Decoder()).decodeBuffer(txt);
			} catch (IOException ioexception) {
			}
		return buf;
	}

	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 0;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	public static final String replace(String line, String oldString,
			String newString, int count[]) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		} else {
			return line;
		}
	}

	public static final String escapeHTMLTags(String in) {
		if (in == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}

		if (last == 0)
			return in;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final synchronized String hash(String data) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. We will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte bytes[]) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}

		return buf.toString().toUpperCase();
	}

	public static final byte[] decodeHex(String hex) {
		char chars[] = hex.toCharArray();
		byte bytes[] = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}

		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case 48: // '0'
			return 0;

		case 49: // '1'
			return 1;

		case 50: // '2'
			return 2;

		case 51: // '3'
			return 3;

		case 52: // '4'
			return 4;

		case 53: // '5'
			return 5;

		case 54: // '6'
			return 6;

		case 55: // '7'
			return 7;

		case 56: // '8'
			return 8;

		case 57: // '9'
			return 9;

		case 97: // 'a'
			return 10;

		case 98: // 'b'
			return 11;

		case 99: // 'c'
			return 12;

		case 100: // 'd'
			return 13;

		case 101: // 'e'
			return 14;

		case 102: // 'f'
			return 15;
		}
		return 0;
	}

	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0)
			return new String[0];
		ArrayList wordList = new ArrayList();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);
		int start = 0;
		for (int end = boundary.next(); end != -1; end = boundary.next()) {
			String tmp = text.substring(start, end).trim();
			tmp = replace(tmp, "+", "");
			tmp = replace(tmp, "/", "");
			tmp = replace(tmp, "\\", "");
			tmp = replace(tmp, "#", "");
			tmp = replace(tmp, "*", "");
			tmp = replace(tmp, ")", "");
			tmp = replace(tmp, "(", "");
			tmp = replace(tmp, "&", "");
			if (tmp.length() > 0)
				wordList.add(tmp);
			start = end;
		}

		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	public static final String randomString(int length) {
		if (length < 1)
			return null;
		char randBuffer[] = new char[length];
		for (int i = 0; i < randBuffer.length; i++)
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];

		return new String(randBuffer);
	}

	public static final String chopAtWord(String string, int length) {
		if (string == null)
			return string;
		char charArray[] = string.toCharArray();
		int sLength = string.length();
		if (length < sLength)
			sLength = length;
		for (int i = 0; i < sLength - 1; i++) {
			if (charArray[i] == '\r' && charArray[i + 1] == '\n')
				return string.substring(0, i + 1);
			if (charArray[i] == '\n')
				return string.substring(0, i);
		}

		if (charArray[sLength - 1] == '\n')
			return string.substring(0, sLength - 1);
		if (string.length() < length)
			return string;
		for (int i = length - 1; i > 0; i--)
			if (charArray[i] == ' ')
				return string.substring(0, i).trim();

		return string.substring(0, length);
	}

	public static final String escapeForXML(String string) {
		if (string == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
		}

		if (last == 0)
			return string;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final String escapeForSpecial(String string) {
		if (string == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				} else if (ch == '>') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}

		if (last == 0)
			return string;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		} else {
			StringBuffer buf = new StringBuffer(length);
			buf.append(zeroArray, 0, length - string.length()).append(string);
			return buf.toString();
		}
	}

	public static final String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}

	public static final String collectionToString(Collection c, String spilt) {
		String ret;
		ArrayList a;
		if (c == null)
			return null;
		if (spilt == null)
			return null;
		ret = "";
		a = new ArrayList(c);
		for (int i = 0; i < a.size(); i++) {
			String t = (String) a.get(i);
			if (i == a.size() - 1)
				ret = ret + t;
			else
				ret = ret + t + spilt;
		}

		return ret;
	}

	public static String genPassword(int length) {
		if (length < 1)
			return null;
		String strChars[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a" };
		StringBuffer strPassword = new StringBuffer();
		int nRand = (int) Math.round(Math.random() * 100D);
		for (int i = 0; i < length; i++) {
			nRand = (int) Math.round(Math.random() * 100D);
			strPassword.append(strChars[nRand % (strChars.length - 1)]);
		}

		return strPassword.toString();
	}

	public static String genNumPassword(int length) {
		if (length < 1)
			return null;
		String strChars[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuffer strPassword = new StringBuffer();
		int nRand = (int) Math.round(Math.random() * 100D);
		for (int i = 0; i < length; i++) {
			nRand = (int) Math.round(Math.random() * 100D);
			strPassword.append(strChars[nRand % (strChars.length - 1)]);
		}

		return strPassword.toString();
	}

	public static String genEmptyString(int length) {
		if (length < 1)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
			sb.append(" ");

		return sb.toString();
	}

	public static String getHTML(String str) {
		String strret = null;
		URL rTmp = null;
		InputStream ins = null;
		BufferedReader breader = null;
		InputStreamReader isreader = null;
		try {
			rTmp = new URL(str);
			ins = rTmp.openStream();
			isreader = new InputStreamReader(ins);
			breader = new BufferedReader(isreader);
			String info = breader.readLine();
			strret = info;
			for (info = breader.readLine(); info != null; info = breader
					.readLine())
				strret = strret + "\n" + info;

		} catch (Exception exception) {
		} finally {
			try {
				if (breader != null)
					breader.close();
			} catch (IOException ioexception) {
			}
			try {
				if (isreader != null)
					isreader.close();
			} catch (IOException ioexception1) {
			}
			try {
				if (ins != null)
					ins.close();
			} catch (IOException ioexception2) {
			}
			return strret;
		}
	}

	public static String getAsciiString(int digit) {
		byte ret[] = new byte[1];
		ret[0] = (byte) digit;
		return new String(ret);
	}

	public static int getAsciiNum(String s) {
		if (s.length() < 1) {
			return 0;
		} else {
			byte b = s.getBytes()[0];
			return b;
		}
	}

	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	public static String formatDate(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
		return outFormat.format(date);
	}

	public static String formatDateTime(Date date) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return outFormat.format(date);
	}

	public static String formatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String formatDate5(Date myDate) {
		String strDate = getYear(myDate) + "-" + getMonth(myDate) + "-"
				+ getDay(myDate);
		return strDate;
	}

	public static String formatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static long Date2Long(int year, int month, int date) {
		Calendar cld = Calendar.getInstance();
		month--;
		cld.set(year, month, date);
		return cld.getTime().getTime();
	}

	public static long Time2Long(int year, int month, int date, int hour,
			int minute, int second) {
		Calendar cld = Calendar.getInstance();
		month--;
		cld.set(year, month, date, hour, minute, second);
		return cld.getTime().getTime();
	}

	public static int getYear(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(1);
	}

	public static int getMonth(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(2) + 1;
	}

	public static int getDay(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(5);
	}

	public static int getHour(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(11);
	}

	public static int getMinute(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(12);
	}

	public static int getSecond(long t) {
		Calendar cld = Calendar.getInstance();
		if (t > 0L)
			cld.setTime(new Date(t));
		return cld.get(13);
	}

	public static int getYear(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(1);
	}

	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(2) + 1;
	}

	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(5);
	}

	public static int getHour(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(11);
	}

	public static int getMinute(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(12);
	}

	public static int getSecond(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(13);
	}

	public static int getYear() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(1);
	}

	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(2) + 1;
	}

	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		cld.setTime(new Date());
		return cld.get(5);
	}

	public static String replaceComma(String text) {
		if (text != null)
			text = text.replaceAll("\uFFFD\uFFFD", ",");
		return text;
	}

	public static String replaceBr(String text) {
		if (text != null)
			text = text.replaceAll("\n", "<BR>");
		return text;
	}

	public static long getLongTime() {
		return System.currentTimeMillis();
	}

	public static boolean nullOrBlank(String param) {
		return param == null || param.length() == 0 || param.trim().equals("");
	}

	public static String notNull(String param) {
		return param != null ? param.trim() : "";
	}

	public static boolean parseBoolean(String param) {
		if (nullOrBlank(param))
			return false;
		switch (param.charAt(0)) {
		case 49: // '1'
		case 84: // 'T'
		case 89: // 'Y'
		case 116: // 't'
		case 121: // 'y'
			return true;
		}
		return false;
	}

	public static String[] getPagesign(Locale locale) {
		String pagessign[] = { Messages.getMessage(locale, "pages.first"),
				Messages.getMessage(locale, "pages.previous"),
				Messages.getMessage(locale, "pages.next"),
				Messages.getMessage(locale, "pages.end") };
		return pagessign;
	}

	// add by jilili 2011/5/4 start
	/**
	 * 将以"ad,cd,ed"此类以","分开的字符串，转换成"'ad','cd','ed'"
	 * 
	 * @param ids
	 *            逗号分开的字符串
	 */
	public static String toStringIds(String ids) {
		StringBuffer sb = new StringBuffer();
		String[] aryIds = ids.split(",");
		for (String strId : aryIds) {
			sb.append("'").append(strId.trim()).append("'").append(",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * 将以"ad,cd,ed"此类以","分开的字符串，转换成"'ad','cd','ed'"
	 * 
	 * @param ids
	 *            逗号分开的字符串
	 */
	public static String toStringIds(String ids, String pattern) {
		StringBuffer sb = new StringBuffer();
		String[] aryIds = ids.split(pattern);
		for (String strId : aryIds) {
			sb.append("'").append(strId.trim()).append("'").append(",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * 字符串处理方式
	 * 
	 * @param str
	 *            要分隔的字符串
	 * @param pattern
	 *            以什么样的字符分隔
	 * @return 返回list集合
	 * @author jilili
	 */
	public static final List<String> toList(String str, String pattern) {
		List<String> stringList = new ArrayList<String>();
		if (str != null && !"".equals(str)) {
			String[] strChild = str.split(pattern);
			for (String s : strChild) {
				stringList.add(s.trim());
			}
		}

		return stringList;
	}

	// add By jilili 2011/5/4 end

	// add by niujingwei 2011/8/5 start
	/**
	 * 将以List<String>转换成"'ad','cd','ed'"
	 * 
	 * @param list
	 *            String数组 flg 生成的String是否加上 （） true 是 false 不是
	 * 
	 */
	public static String toStringList(List<String> list, boolean flg) {
		String deString = "";
		if (list.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (String strId : list) {
				sb.append("'").append(strId.trim()).append("'").append(",");
			}
			deString = sb.toString().substring(0, sb.toString().length() - 1);
			if (flg) {
				return "( " + deString + " )";
			}
		}

		// if("".equals(deString)){
		// if (flg) {
		// return "('" + "')";
		// }
		// }

		return deString;
	}

	// add By jilili 2011/5/4 end

	// add by jilili 2011/5/16 start
	/**
	 * 将用于存储查询条件的query对象转换为字符串形式
	 */
	public static final String toStrQuery(Map<String, String> query) {
		if (query != null && !query.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			Set<String> keys = query.keySet();
			for (String key : keys) {
				// 是否是中文的标志
				boolean flag = false;
				char[] c = query.get(key).toCharArray();
				// 如果字符数组中有一个是中文，则认为该字符串是中文
				for (int i = 0; i < c.length; i++) {
					if (StringUtil.isChinese(c[i])) {
						flag = true;
						break;
					}
				}
				// 如果是中文，则将其字符数组的字符串表达形式拼接到url中
				if (flag) {
					sb.append("query.").append(key).append("=").append(
							Arrays.toString(query.get(key).getBytes())).append(
							"&");
				} else {
					sb.append("query.").append(key).append("=").append(
							query.get(key)).append("&");
				}
			}
			return sb.toString().substring(0, sb.toString().length() - 1);
		}
		return "";
	}

	public static final HashMap<String, String> toMap(String strQuery) {
		HashMap<String, String> query = new HashMap<String, String>();
		String[] entrys = strQuery.split("&");
		for (String entry : entrys) {
			String[] content = entry.split("=");
			if (!entry.endsWith("=")) {
				if (content[1].trim().endsWith("]")) {
					String byteStr = encodeByteString(content[1]);
					query.put(
							content[0].substring(content[0].indexOf(".") + 1),
							byteStr);

				} else {

					query.put(
							content[0].substring(content[0].indexOf(".") + 1),
							content[1]);
				}
			} else {
				query
						.put(content[0].substring(content[0].indexOf(".") + 1),
								"");
			}
		}
		return query;
	}

	public static String encodeByteString(String content) {
		String str = content.trim();
		str = str.substring(1, str.length() - 1);
		String[] strByte = str.split(",");
		byte[] b = new byte[0];
		for (String s : strByte) {
			b = ArrayUtils.add(b, new Byte(s.trim()));
		}
		String byteStr = "";
		try {
			byteStr = new String(b, "GBK");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return byteStr;
	}

	public static final HashMap<String, String> decodeQuery(
			HashMap<String, String> query) {
		Set<String> keysSet = query.keySet();
		for (String strKey : keysSet) {
			String str = query.get(strKey).trim();
			if (str.endsWith("]")) {
				String byteStr = encodeByteString(str);
				query.put(strKey, byteStr);
			}
			query.put(strKey, query.get(strKey));
		}
		return query;
	}

	public static final List<String> toList(String str) {
		List<String> stringList = new ArrayList<String>();
		if (str != null && !"".equals(str)) {
			String[] strChild = str.split(",");
			for (String s : strChild) {
				stringList.add(s.trim());
			}
		}

		return stringList;
	}

	public static String getName(String taskId) {
		ProcessBase processBase = new ProcessBase();
		Map<String, String> object1 = (Map<String, String>) processBase
				.getVariable(taskId, "mainpre");
		if (object1 == null) {
			return "";
		}
		// 获得任务的唯一标示
		Object object = processBase.getVariable(taskId, "owner");
		String str = object == null ? "" : object.toString();
		return object1.get("name" + str);
	}

	private static Object getObject(Object obj, String attr) {
		Object object = null;
		Class c = obj.getClass();
		// 获得第一个字母
		String first = attr.substring(0, 1);
		// 获得第一个字母以后的所有字母
		String end = attr.substring(1);
		// 拼接方法名称
		String newStr = "get" + first.toUpperCase() + end;
		Method method = null;
		try {
			method = c.getMethod(newStr, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			newStr = "is" + first.toUpperCase() + end;
			try {
				method = c.getMethod(newStr, null);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			}
		}
		if (method != null) {
			try {
				// 执行该方法
				object = (Object) method.invoke(obj, null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return object;

	}

	public static String getValue(Object obj, String attr) {
		if (attr == null) {
			return null;
		}
		String value = null;
		Class c = obj.getClass();
		// 如果是取对象中对象的属性的话在走if分支，但是只能取得两级
		if (attr.indexOf(".") != -1) {
			String[] split = attr.split("\\.");
			if (split.length > 2) {
				return null;
			}
			String string = split[0];
			Object object = getObject(obj, string);
			if (object == null) {
				return null;
			}
			value = (String) getObject(object, split[1]);
		} else {

			value = (String) getObject(obj, attr);

		}
		return value;
	}

	/**
	 * 
	 * TODO 遍历对象集合取对象的属性拼成有规律的字符串
	 * 
	 * @param list
	 * @param attr
	 *            可以为 id name ...... 或者xxx.id xxx.name...... 代表对象的属性
	 *            如果attr的形式为xxx.id表示的是去当前对象中的getXXX()对象的getId()属性
	 * @param flag
	 *            0代表s,s,s,s样式 1代表 's','s','s'样式 2代表（'s','s','s'）
	 * @param isHode
	 *            表示如果属性为null获得属性为"",那么是否同样拼在串里面。true表示是 false表示不拼
	 * @return
	 * @throws Exception
	 *             Sep 23, 20117:22:56 PM
	 * @author niujingwei
	 */
	public static String forEachObjectGetAttr(List list, String attr, int flag,
			boolean isHold) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String newStr = "";
		if (flag == 2) {
			newStr += "(";
		}
		for (Object object : list) {
			String value = getValue(object, attr);
			if (flag == 0) {
				if (isHold) {
					sBuffer.append(value == null ? "" : value + ",");
				} else {
					if (value != null && !"".equals(value)) {
						sBuffer.append(value + ",");
					}
				}
			} else {

				if (isHold) {
					sBuffer.append("'" + (value == null ? "" : value) + "',");
				} else {
					if (value != null && !"".equals(value)) {
						sBuffer.append("'" + value + "',");
					}
				}

			}
		}
		if (sBuffer.indexOf(",") != -1) {
			newStr += sBuffer.substring(0, sBuffer.length() - 1);
		} else {
			return "";
		}
		if (flag == 2) {
			newStr += ")";
		}

		return newStr;
	}

	/**
	 * 
	 * TODO 遍历对象集合取对象的属性拼成有规律的字符串
	 * 
	 * @param list
	 * @param attr
	 *            可以为 id name ...... 或者xxx.id xxx.name...... 代表对象的属性
	 *            如果attr的形式为xxx.id表示的是去当前对象中的getXXX()对象的getId()属性
	 * @param flag
	 *            0代表s,s,s,s样式 1代表 's','s','s'样式 2代表（'s','s','s'）
	 * @param isHode
	 *            表示如果属性为null获得属性为"",那么是否同样拼在串里面。true表示是 false表示不拼
	 * @return
	 * @throws Exception
	 *             Sep 23, 20117:22:56 PM
	 * @author niujingwei
	 */
	public static String forEachObjectGetAttr(Object[] list, String attr,
			int flag, boolean isHold) throws Exception {
		StringBuffer sBuffer = new StringBuffer();
		String newStr = "";
		if (flag == 2) {
			newStr += "(";
		}
		for (Object object : list) {
			String value = getValue(object, attr);
			if (flag == 0) {
				if (isHold) {
					sBuffer.append(value == null ? "" : value + ",");
				} else {
					if (value != null && !"".equals(value)) {
						sBuffer.append(value + ",");
					}
				}
			} else {

				if (isHold) {
					sBuffer.append("'" + (value == null ? "" : value) + "',");
				} else {
					if (value != null && !"".equals(value)) {
						sBuffer.append("'" + value + "',");
					}
				}

			}
		}
		if (sBuffer.indexOf(",") != -1) {
			newStr += sBuffer.substring(0, sBuffer.length() - 1);
		} else {
			return "";
		}
		if (flag == 2) {
			newStr += ")";
		}

		return newStr;
	}

	/**
	 * 生成32位UUID
	 * 
	 * @return
	 */
	public static String createUUID32Code() {
		String uuid = UUID.randomUUID().toString();
		// 得到32位的UUID
		uuid = uuid.replaceAll("-", "");
		return uuid;

	}

	/**
	 * 生成36位UUID
	 * 
	 * @return
	 */
	public static String createUUID36Code() {
		// 得到36位的UUID
		String uuid = UUID.randomUUID().toString();
		return uuid;

	}
	
	// add by jilili 2011/5/16 end

	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
	private static final char AMP_ENCODE[] = "&amp;".toCharArray();
	private static final char LT_ENCODE[] = "&lt;".toCharArray();
	private static final char GT_ENCODE[] = "&gt;".toCharArray();
	private static MessageDigest digest = null;
	private static Random randGen = new Random();
	private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();
	private static final char zeroArray[] = "0000000000000000".toCharArray();

	/**
	 * Map按照Key和Value排序
	 * 
	 * @param map
	 *            所需要排序的Map集合
	 * @param order
	 *            如果为true时则是需要按字母正序排序，否则倒序
	 * @return
	 */
	public static final Map mapSort(Map map, boolean order) {
		Map<Object, Object> mapVK = new TreeMap<Object, Object>(
				new Comparator<Object>() {
					public int compare(Object obj1, Object obj2) {
						String v1 = (String) obj1;
						String v2 = (String) obj2;
						int s = v2.compareTo(v1);
						return s;
					}
				});

		Set col = map.keySet();
		Iterator iter = col.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = (String) map.get(key);
			mapVK.put(key, value);
		}
		if (order) {
			Map<Object, Object> mapOrder = new TreeMap<Object, Object>();
			Iterator<Entry<Object, Object>> iterator = mapVK.entrySet()
					.iterator();
			List<String> list = new ArrayList<String>();
			while (iterator.hasNext()) {
				Entry<Object, Object> entry = iterator.next();
				list.add((String) entry.getKey());
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				mapOrder.put(list.get(i), mapVK.get(list.get(i)));
			}
			return mapOrder;

		}
		return mapVK;
	}
	
	/**
	 * 删除数组中的重复元素（保留一个）
	 * @author HaoQi
	 * @return 删除了重复项的字符串数组
	 */
	public static String[] removeDuplicate(String[] strArray){
		List<String> list = new LinkedList<String>();
        for(int i = 0; i < strArray.length; i++) {
            if(!list.contains(strArray[i])) {
                list.add(strArray[i]);
            }
        }
        
        return (String[])list.toArray(new String[list.size()]);
	}

	/**
	 * 获取指定个数的“两位字母”的不重复数组，num最大为26*26
	 * @param num 数组个数
	 * @return
	 */
	public static String[] get2LetterArray(int num){
		
		//装载字符串数组
		String[] letter1 = new String[26];
		String[] letter2 = new String[26];
		String tempLetter = new String();
		for (int i = 97; i <= 122; i++) {
			tempLetter = Character.valueOf((char)i).toString();
			letter1[i-97] = tempLetter;
			letter2[i-97] = tempLetter;
		}
		
		//最终数组
		String[] finalStr = new String[num];
		
		int count = 0;
		l1 : for (String str1 : letter1) {
			l2 : for (String str2 : letter2) {
				finalStr[count] = str1+str2;
				count++;
				if(count == num){
					break l1;
				}
			}
		}
		
		return finalStr;
	}
	
	public static void main(String[] args) {
		String[] temp = get2LetterArray(20);
		for (String string : temp) {
			System.out.println(string);
		}
	}

}
