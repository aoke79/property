package com.sinoframe.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	
	private static Md5 md5 = null;

	private Md5() {
	}

	public static synchronized Md5 getInstance() {
		if (md5 == null) {
			md5 = new Md5();
		}
		return md5;
	}

	/**
	 * 利用MD5进行加密
	 * 
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 *             没有这种产生消息摘要的算法
	 * @throws UnsupportedEncodingException
	 */
	public String EncoderByMd5(String str,String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String md5Value = "";
		if( salt != null ){
			md5Value =  firstEncoderByMd5(firstEncoderByMd5(str)+salt);
		}
		return md5Value;
	}

	private static String firstEncoderByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		// 确定计算方法
		// MessageDigest md5 = MessageDigest.getInstance("MD5");
		// BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		// String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		// return newstr;
		StringBuffer buf = new StringBuffer(""); 
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(str.getBytes()); 
		byte b[] = md.digest(); 
		int i; 
		for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0){
				i+= 256; 
			}
			if(i<16){
				buf.append("0"); 
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();		
	}
	/**
	 * 判断用户密码是否正确
	 * 
	 * @param newpasswd
	 *            用户输入的密码
	 * @param oldpasswd
	 *            数据库中存储的密码－－用户密码的摘要
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean checkpassword(String newpasswd, String salt, String oldpasswd)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (firstEncoderByMd5(firstEncoderByMd5(newpasswd)+salt).equals(oldpasswd))
			return true;
		else
			return false;
	}
	
	public static void main(String args[]){
		
		String a = "xxxxxx";
		try {
			System.out.println(Md5.getInstance().EncoderByMd5(a,""));
		} catch (NoSuchAlgorithmException e) {
	// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
