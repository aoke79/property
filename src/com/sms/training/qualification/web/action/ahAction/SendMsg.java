package com.sms.training.qualification.web.action.ahAction;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.sinoframe.web.action.BaseAction;

@ParentPackage("json-default")
@Results({
		@Result(name = "json", type = "json"),
})

public class SendMsg extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造方法
	 */
	public SendMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public String sendMessage(){
//		String xml = "<?xml version="
//			+ "\"1.0\""
//			+ " encoding="
//			+ "\"UTF-8\""
//			+ "?><SDRequest><TransactionName>CreateDataFileComplete</TransactionName><IdentityInfo><Code>"
//			+ 1 + "</Code><Description></Description><Timestamp>"
//			+ "20100315140542" + "</Timestamp></IdentityInfo></SDRequest>";
		StringBuffer bufxml = new StringBuffer();
		bufxml.append("<?xml version=");
		bufxml.append("\"1.0\"");
		bufxml.append(" encoding=");
		bufxml.append("\"UTF-8\"");
		bufxml.append("?><xmlRequest><header><syscode>");
		bufxml.append("018");
		bufxml.append("</syscode><depcode>");
		bufxml.append("00000000");
		bufxml.append("</depcode><depname>安华农业保险股份有限公司</depname><password>123</password></header><body><sms><mobile>");
		bufxml.append("18637107804");
		bufxml.append("</mobile><content>没有内容的短信内容  安华</content></sms></body></xmlRequest>");
		
		PostMethod post = new PostMethod("http://10.0.7.40:8080/smsHttpServlet.servlet");//请求地址
//		post.setRequestBody(xml);//这里添加xml字符串
		post.setRequestBody(bufxml.toString());

		// 指定请求内容的类型
		post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
		HttpClient httpclient = new HttpClient();//创建 HttpClient 的实例
		int result;
		try {
		result = httpclient.executeMethod(post);
		System.out.println("Response status code: " + result);//返回200为成功
		System.out.println("Response body: ");
		System.out.println(post.getResponseBodyAsString());//返回的内容
		post.releaseConnection();//释放连接
		} catch (HttpException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return "json";
	}
}
