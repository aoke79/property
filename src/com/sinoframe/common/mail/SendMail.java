/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng   //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 子系统:邮件发送
  * 文件名：SendMail.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2009/03/26
  * 功能描述: 邮件发送共通
  * 版   本: V 1.0
  */

package com.sinoframe.common.mail;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import sun.misc.BASE64Encoder;

/**
 * 发送java - Mail
 * @author haodafeng
 * 设置收件人:      发送给某位仁兄 收件人用逗号(,)隔开 setStrSendTo(string)
 * 设置邮件内容:	  setMailBody(string) 
 * 设置附件:		  setStrAffixPath(string[])
 * 设置抄送人:	  setStrSendCC(string)
 * 设置密抄人:	  setStrSendBCC(string)
 */
public class SendMail {

	// 初期化log信息
	static Logger logger = Logger.getLogger(SendMail.class.getName());

	// log4j的路径
	private String strLog4jPath = "log4j.properties";

	//Mail参数文件路径
	private String strConfigPath = "mail.properties";

	//Mail主机地址
	private String strMailHost = "";
	
	//mail端口号
	private int mailport = 25;
	
	//MIME邮件对象
	private MimeMessage mimeMsg;

	//邮件会话对象
	private Session session;

	//邮件传送对象
	private Transport transport;

	//系统属性
	private Properties props;

	//smtp认证用户名
	private String strMailUser = "";

	//smtp认证密码
	private String strMailPassword = "";	
	
	//发送给某位仁兄 收件人用逗号(,)隔开
	private String strSendTo = "";
	
	//抄送给某位仁兄 收件人用逗号(,)隔开	
	private String strSendCC = "";
	
	//密抄送给某位仁兄 收件人用逗号(,)隔开	
	private String strSendBCC = "";
	
	//附件地址list	
	private String[] strAffixPath = {};
	
	//标题
	private String mailSubject = "";
	
	//内容
	private String mailBody = "";
		
	//Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	private Multipart mp;

	//BASE64格式编码对象
	private BASE64Encoder enc;

	/**
	 * 构造函数 
	 */
	public SendMail() {

		//根据配置文件初始化log配置信息
		PropertyConfigurator.configure(this.getClass().getResource(strLog4jPath));

		//读取配置文件信息		
		Properties prop = new Properties();
		try {
			//读取配置文件
			InputStream is = this.getClass().getResourceAsStream(strConfigPath);
			try {
				prop.load(is);
			} finally {
				is.close();
			}

			// 取得mail服务器主机地址
			strMailHost = prop.getProperty("mailhost");
			if (strMailHost == null || strMailHost.equals("")) {
				logger.fatal("mail host is null!");
				System.exit(-1);
			}
			// 取得mail发送用户
			strMailUser = prop.getProperty("mailuser");
			if (strMailUser == null || strMailUser.equals("")) {
				logger.fatal("mail user is null!");
				System.exit(-1);
			}

			// 取得mail发送密码
			strMailPassword = prop.getProperty("mailpassword");
			if (strMailPassword == null || strMailPassword.equals("")) {
				logger.fatal("mail password is null!");
				System.exit(-1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Load db properties file Error!");
//			System.exit(1);
		}
	}

	/**
	 * @param hostName  String
	 */
	public void setSmtpHost(String hostName) {
		logger.info("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象

		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * 创建邮件对象
	 * 
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		logger.info("准备创建MIME邮件对象！");
		try {
			// 创建MIME邮件对象
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			enc = new sun.misc.BASE64Encoder();

			return true;
		} catch (Exception e) {
			logger.fatal("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * 设置是否身份验证
	 * 
	 * @param need boolean
	 */
	public void setNeedAuth(boolean need) {
		logger.info("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * 设置发送者用户名和密码(用于服务器的验证)
	 * 
	 * @param name String   发送邮件者
	 * @param pass String   发送者密码
	 */
	public void setNamePass(String name, String pass) {
		strMailUser = name;
		strMailPassword = pass;
	}

	/**
	 * 设置邮件标题
	 * 
	 * @param mailSubject String 标题
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		logger.info("设置邮件主题！");
		try {
			mimeMsg.setSubject("=?GB2312?B?"
					+ enc.encode(mailSubject.getBytes("GBK")) + "?=");
			return true;
		} catch (Exception e) {
			logger.fatal("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * 设置邮件内容
	 * 
	 * @param mailBody String 标题内容
	 * @return boolean 是否设置成功
	 */
	public boolean setBody(String mailBody) {
		try {
			//
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			logger.fatal("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * 添加附件
	 * 
	 * @param name String 附件全路径
	 * @return boolean 是否添加成功
	 */
	public boolean addFileAffix(String filename) {

		logger.info("增加邮件附件：" + filename);
		try {
			//javamail 附件
			BodyPart bp = new MimeBodyPart();
			
			//把附件构造成datasource
			FileDataSource fileds = new FileDataSource(filename);
			
			//添加附件source
			bp.setDataHandler(new DataHandler(fileds));
			
			//设置附件名称为文件名
			bp.setFileName(fileds.getName());

			//为mp对象添加附件
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from) {
		logger.info("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送到
	 * 
	 * @param name String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 抄送
	 * 
	 * @param copyto String  抄送到 
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 抄送
	 * 
	 * @param copyto String  抄送到 
	 */
	public boolean setSendToBCC(String strBcc) {
		if (strBcc == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.BCC,
					(Address[]) InternetAddress.parse(strBcc));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Just do it as this
	 * 测试接口
	 */
	public static void main(String args[]) throws Exception {
		logger.info("Message sent begin.");

		SendMail themail = new SendMail();
		
		themail.setMailSubject("标题");
		themail.setMailBody("内容");
		themail.setStrSendTo("350129923@163.com");
		themail.makeMail();
		
		//
		logger.info("Message sent end.");
		logger.info("邮件发送成功!.");
	}

	public void makeMail() {

		try {
			props = new Properties();
			logger.info("准备获取邮件会话对象！");
			setSmtpHost(strMailHost);// 如果没有指定邮件服务器,就从getConfig类中获取
			setNeedAuth(true);
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
			transport = session.getTransport("smtp");
			transport.connect(strMailHost,mailport, strMailUser, strMailPassword);
//			transport.connect(strMailHost, strMailUser, strMailPassword);
		} catch (Exception e) {
			logger.fatal("获取邮件会话对象时发生错误！" + e);
//			System.exit(1);
		}
		
		//格式化时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String today = format.format(new Date());
		logger.info("正在发送邮件(" + today + ")....");

		try {
			//创建邮件
			createMimeMessage();
			
			//判断是否添加附件
			if (strAffixPath != null || strAffixPath.length > 0) {
				for (int i = 0;i < strAffixPath.length;i++) {
					//添加附件
					if (addFileAffix(strAffixPath[i].toString()) == false)return;
				}
			}
			
			//设置标题和内容
			if (setSubject(mailSubject) == false)return;			
			if (setBody(mailBody) == false)return;

			//接受邮件端
			if (setTo(strSendTo) == false)return;
			
			//抄送
			if (strSendCC != null || strSendCC.length() >0) {
				if (setCopyTo(strSendCC) == false) return;
			}
			
			//密抄
			if ( strSendBCC != null || strSendBCC.length() > 0) {
				if (setSendToBCC(strSendBCC) == false) return;
			}
			
			//设置发送者
			if (setFrom(strMailUser) == false)return;
			
			//设置发送验证名称
			setNamePass(strMailUser, strMailPassword);
			
			mimeMsg.setSentDate(new Date());
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients
											(Message.RecipientType.TO));
			logger.info("发送邮件成功！ " + "(" + strSendTo + ")");

		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("邮件发送失败！" + "(" + strSendTo + ") " + e);
		}

		try {
			transport.close();
		} catch (Exception e) {
			logger.fatal("邮件会话对象关闭时发生错误！" + e);
//			System.exit(1);
		}
	}

	/**
	 * 设置邮件内容
	 * @param mailBody
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	/**
	 * 设置邮件标题
	 * @param mailSubject
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * 设置附件 支持多个附件
	 * @param strAffixPath
	 */
	public void setStrAffixPath(String[] strAffixPath) {
		this.strAffixPath = strAffixPath;
	}

	/**
	 * 发送给某位仁兄 多个用户用逗号隔开
	 * @param strSendCC
	 */
	public void setStrSendTo(String strSendTo) {
		this.strSendTo = strSendTo;
	}

	/**
	 * 抄送给某位仁兄 多个用户用逗号隔开
	 * @param strSendCC
	 */
	public void setStrSendCC(String strSendCC) {
		this.strSendCC = strSendCC;
	}
	
	/**
	 * 密抄送给某位仁兄 多个用户用逗号隔开
	 * @param strSendBCC
	 */
	public void setStrSendBCC(String strSendBCC) {
		this.strSendBCC = strSendBCC;
	}
}