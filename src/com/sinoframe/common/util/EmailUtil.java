package com.sinoframe.common.util;

import com.sinoframe.common.mail.SendMail;

public class EmailUtil {

	/**
	 * 发送邮件的公用方法
	 * @param subject 邮件的主题
	 * @param body 邮件的内容
	 * @param toAddress 收件人的地址
	 */
	public static void sendEmail(String subject, String body, String toAddress){
		
		SendMail themail = new SendMail();
		
		themail.setMailSubject(subject);
		themail.setMailBody(body);
		themail.setStrSendTo(toAddress);
		themail.makeMail();
	}
	
	public static void main(String[] args){
		EmailUtil.sendEmail("找回密码", "您的密码为：12345", "lili860204@126.com");
	}
}
