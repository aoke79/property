
package com.sms.training.qualification.web.action.tfQuaApply;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionContext;
import com.sinoframe.bean.CmUser;
import com.sinoframe.bean.Message;
import com.sinoframe.common.util.DateTool;
import com.sinoframe.common.util.EmailUtil;
import com.sinoframe.common.util.Md5;
import com.sinoframe.common.util.Util;
import com.sinoframe.web.action.BaseAction;
import com.sinoframe.web.action.userInfo.GenRandomNum;
import com.sms.security.basicdata.bean.CmAttachment;
import com.sms.training.qualification.bean.AhMail;
import com.sms.training.qualification.bean.AhUser;
import com.sms.training.qualification.bean.QuaCmUser;
import com.sms.training.qualification.bean.TfQualBaseSignature;
import com.sms.training.qualification.business.IAhUserBS;
import com.sms.training.qualification.business.service.TfQuaSignatureBS;
/**
 * 飞行员签名维护
 * @author 杨书州
 */
@Results(
	{
		@Result(name = "json", type = "json"),
		@Result(name="add",location="/sms/training/qualification/quaApply/quaSignature/quaSignatureAdd.jsp"),
		@Result(name="edit",location="/sms/training/qualification/quaApply/quaSignature/quaSignatureEdit.jsp"),
		@Result(name="list",location="/sms/training/qualification/quaApply/quaSignature/quaSignatureList.jsp"),
		@Result(name="SUCCESS",location="/standard/ajaxDone.jsp"),
		@Result(name = "updateSuccess", location = "/standard/ajaxUpdateDone.jsp"), 
		@Result(name="METHOD",location="tf-qua-apply/tf-qua-signature!list",type="redirectAction"),
		@Result(name="cmUserList",location="/sms/training/qualification/quaApply/quaSignature/quaCmUserList.jsp"),
	    @Result(name = "image",params={"inputName","imgStream"},type="stream")
	}
)
@ParentPackage("json-default")
public class TfQuaSignatureAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	//当前模块名称
	private static String moduleName = "QuaSignature";
	//系统表的service接口
	private TfQuaSignatureBS tfQuaSignatureBS;
	//系统表实体Bean
	private  TfQualBaseSignature tfQualBaseSignature;
	private QuaCmUser cmuser;
	//消息实体
	private Message message;
	//存放查询结果的List集
	private List<TfQualBaseSignature> tfQualBaseSignatureList = new ArrayList<TfQualBaseSignature>();
	//用户信息表
	private List<QuaCmUser> cmUserList = new ArrayList<QuaCmUser>();
	//存放查询对象的HashMap集
	private HashMap<String, String> query  = new HashMap<String, String>();
	//存放ID
	private String ids;
	// 用于存储查询条件的字符串形式
	private String strQuery;		
//	private String fileKey;   没用到
	private List<CmAttachment> fileList = new ArrayList<CmAttachment>();
	//标识页面来源
	private String pageFlag;
	//ajax - json返回是否成功标志 
	private boolean right;
	private String signingid;
	//字节流
	private ByteArrayInputStream imgStream;
	private File myFile;   //上传文件  
	//user的hrid
    private String userid;
    //================================================================
    
  //ahuser对象
  	private AhUser ahuser;
  	private IAhUserBS ahUserBS;
  	private CmUser cmUser;
  	
  	
  	/**
  	 * 返回session值
  	 */
  	private AhUser ahUser;

	public AhUser getAhUser() {
		return ahUser;
	}

	public void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}

	public String sessionName(){
  		ahUser = (AhUser) ActionContext.getContext().getSession().get("user");
  		
  		return "json";
  	}
  	
	private AhMail ahmail;
	/**
	 * 单个 发送邮件的方法
	 * @return
	 */
	public String sendMail(){
		try {
			//根据登录名，判断是否有这个人
			ahuser = this.ahUserBS.findUserByLoginname(cmUser.getLoginName());
			if(ahuser != null){
				//判断这个人是否有邮箱
				if(ahuser.getEmail() != null && !"".equals(ahuser.getEmail())){
					//先判断邮箱是否是正确格式，再判断是否是安华的公司邮箱
					if(ahuser.getEmail().matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
							&& ahuser.getEmail().contains("@ahic.com.cn")){
							
						String pwd=GenRandomNum.getInstance().genRandomNum(6);
						System.out.println(pwd);
							
						ahuser.setPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
						ahUserBS.saveOrUpdate(ahuser);
						
						ahmail = this.ahUserBS.findAhmail(ahuser.getUseruuid());
						if(ahmail != null){
							ahmail.setBegintime(DateTool.getNow());
						}else{
							ahmail = new AhMail();
							ahmail.setBegintime(DateTool.getNow());
							ahmail.setAnUser(ahuser);
						}
						ahUserBS.saveOrUpdate(ahmail);
						String yuju = ahuser.getName()+","+"您好！"+"\r\n"+"您的新密码为："+pwd+"。一个小时之内有效，请尽快登录修改";
						EmailUtil.sendEmail("请登录安华统一办公平台",yuju,ahuser.getEmail());
						this.message = this.getSuccessMessage("您的申请已受理，请查看"+ahuser.getEmail()+"邮箱", "", "", "");
					}else{
						this.message = this.getFailMessage("您的注册邮箱不是公司邮箱,请到HR系统中申请后再进行密码找回！");
					}
				}else{
					this.message = this.getFailMessage("您的注册邮箱不存在，请到HR系统中申请后再进行密码找回！");
				}
				
			}else{
				this.message = this.getFailMessage("该用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("发送失败");
		}
		return "json";
	}
	
	/**
	 * 群发邮件
	 * @return
	 */
	List<AhUser> ahuserList;
	public String sendMails(){
		try {
			String hql = "select u from AhUser u where u.email like '%@ahic.com.cn%'";
			ahuserList = this.ahUserBS.findPageByQuery(hql);
			for (AhUser ahUser : ahuserList) {
				String pwd=GenRandomNum.getInstance().genRandomNum(6);
				System.out.println(pwd);
				ahUser.setPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
				ahUserBS.saveOrUpdate(ahUser);
				String yuju = "<p>"+ahUser.getName()+",您好!</p>" +
				"<p style='text-indent:2em;'>统一办公门户上线试运行,敬请登录使用。为期三个月的试运行结束后,各业务系统将陆续接入，统一办公门户将成为唯一的系统登录入口。</p>" +
				"<p style='text-indent:2em;'>目前通过统一办公门户可以登录的系统有：</p>" +
				"<p style='text-indent:4em;'>办公系统，核保核赔，统计分析，人力信息系统，人力自助服务系统，工作日志、核心业务，预算系统，费控系统，资金管理，财务系统，新农险系统，销售管理，单证系统，再保系统分出子系统，再保系统分入子系统。</p>" +
				"<p style='text-indent:2em;'>统一办公门户访问地址：<a style='color:blue;'>http://10.0.20.32:8080/portal</a></p>" +
				"<p style='text-indent:2em;'>用户名为您的人力新员工号：<span style='color:red;'>"+ahUser.getUsername()+"</span></p>" +
				"<p style='text-indent:9em;'>初始密码为：<span style='color:red;'>"+pwd+"</span></p>" +
				"<p style='text-indent:2em;'>注意：今早已登录并修改密码的同事，请以此邮件密码为准，再次登录修改密码。因此带来的不便，敬请谅解！</p>" +
				"<p style='text-indent:2em;'>本邮件无需回复，任何使用问题请与信息系统部何丰宇联系：hefengyu@ahic.com.cn  联系电话：01068090590</p>" +
				"<p>&nbsp;</p><p>&nbsp;</p><p style='text-indent:2em;'>信息技术部</p>";
				EmailUtil.sendEmail("请登录安华统一办公平台",yuju,ahUser.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("发送失败");
		}
		return "json";
	}
	
	/**
	 * 发送单个短信
	 */
	public String sendMessage(){
		try {
			//根据登录名，判断是否有这个人
			ahuser = this.ahUserBS.findUserByLoginname(cmUser.getLoginName());
			if(ahuser != null){
				//判断是否有 有效 手机号（11位已1开头的手机号）
				//判断这个人有没有手机号
				if(!"".equals(ahuser.getMobile()) && ahuser.getMobile()!=null ){
					//判断手机号是否有误
					if(ahuser.getMobile().length()==11 && ahuser.getMobile().startsWith("1")){
						String pwd=GenRandomNum.getInstance().genRandomNum(6);
						System.out.println(pwd);
						ahmail = this.ahUserBS.findAhmail(ahuser.getUseruuid());
						if(ahmail != null){
							ahmail.setBegintime(DateTool.getNow());
						}else{
							ahmail = new AhMail();
							ahmail.setBegintime(DateTool.getNow());
							ahmail.setAnUser(ahuser);
						}
						ahUserBS.saveOrUpdate(ahmail);
						
						try {
							ahuser.setPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
						} catch (NoSuchAlgorithmException e1) {
							this.message = this.getFailMessage("发送失败！");
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) {
							this.message = this.getFailMessage("发送失败！");
							e1.printStackTrace();
						}
												
						StringBuffer bufxml = new StringBuffer();
						bufxml.append("<?xml version=");
						bufxml.append("\"1.0\"");
						bufxml.append(" encoding=");
						bufxml.append("\"UTF-8\"");
						bufxml.append("?><xmlRequest><header><syscode>");
						bufxml.append("018");
						bufxml.append("</syscode><depcode>");
						bufxml.append("00000000");
						bufxml.append("</depcode><depname>安华农业保险股份有限公司</depname><password>202cb962ac59075b964b07152d234b70</password></header><body><sms><mobile>");
						bufxml.append(ahuser.getMobile());
						bufxml.append("</mobile><content>");
						bufxml.append(ahuser.getName()+"您好, 统一办公门户用户名为新工号:"+ ahuser.getHrid()+" 密码为:"+pwd+" 请登录并修改密码，此密码1小时内有效。");
						bufxml.append("</content></sms></body></xmlRequest>");
						
						PostMethod post = new PostMethod("http://10.0.7.40:8080/smsHttpServlet.servlet");//请求地址
						//这里添加xml字符串
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
							String responseXml = post.getResponseBodyAsString();
							if(!"".equals(responseXml) && responseXml != null  ){
								if(responseXml.contains("000")){
									ahUserBS.saveOrUpdate(ahuser);
									this.message = this.getSuccessMessage("您的申请已受理，请查看"+ahuser.getMobile()+"手机", "", "", "");
								}else if(responseXml.contains("001")){
									this.message = this.getFailMessage("发送失败！");
								}
							}
							post.releaseConnection();//释放连接
						} catch (HttpException e) {
							this.message = this.getFailMessage("发送失败！");
							//连接异常
							e.printStackTrace();
						} catch (IOException e) {
							this.message = this.getFailMessage("发送失败！");
							e.printStackTrace();
						}
					}else{
						this.message = this.getFailMessage("您在HR系统中注册的手机号无效，请到HR系统中申请后再进行密码找回！");
					}
				}else{
					this.message = this.getFailMessage("您未在HR系统中注册手机号，请到HR系统中申请后再进行密码找回！");
				}
			}else{
				this.message = this.getFailMessage("该用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = this.getFailMessage("发送失败");
		}
		return "json";
	}
	
	/**
	 * 群发短信
	 */
	public String sendMessages(){
		try {
			String hql = "select u from AhUser u where u.mobile like '1%' and length(u.mobile)=11 and u.email not like '%@ahic.com.cn%'";
//			String hql = "select u from AhUser u where u.mobile like '1%' and length(u.mobile)=11 and u.email like '%@sinosoft.com.cn%'";
			ahuserList = this.ahUserBS.findPageByQuery(hql);
			for (AhUser ahUser : ahuserList) {
				String pwd=GenRandomNum.getInstance().genRandomNum(6);
				System.out.println(pwd);
				ahUser.setPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
				
				StringBuffer bufxml = new StringBuffer();
				bufxml.append("<?xml version=");
				bufxml.append("\"1.0\"");
				bufxml.append(" encoding=");
				bufxml.append("\"UTF-8\"");
				bufxml.append("?><xmlRequest><header><syscode>");
				bufxml.append("018");
				bufxml.append("</syscode><depcode>");
				bufxml.append("00000000");
				bufxml.append("</depcode><depname>安华农业保险股份有限公司</depname><password>202cb962ac59075b964b07152d234b70</password></header><body><sms><mobile>");
				bufxml.append(ahUser.getMobile());
				bufxml.append("</mobile><content>");
				bufxml.append(ahUser.getName()+"您好, 安华统一办公门户上线试运行,用户名为人力新工号:"+ ahUser.getHrid()+" ,密码为:"+pwd+"。 请通过公司内网登录并修改密码，勿向任何人泄露密码。");
				bufxml.append("</content></sms></body></xmlRequest>");
				
				PostMethod post = new PostMethod("http://10.0.7.40:8080/smsHttpServlet.servlet");//请求地址
				//这里添加xml字符串
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
					String responseXml = post.getResponseBodyAsString();
					System.out.println(responseXml+"=========================");
					if(!"".equals(responseXml) && responseXml != null  ){
						if(responseXml.contains("000")){
							ahUserBS.saveOrUpdate(ahUser);
							this.message = this.getSuccessMessage("您的申请已受理，请查看"+ahUser.getMobile()+"手机", "", "", "");
						}else if(responseXml.contains("001")){
							this.message = this.getFailMessage("发送失败！");
						}
					}
					post.releaseConnection();//释放连接
				} catch (HttpException e) {
					this.message = this.getFailMessage("发送失败");
					e.printStackTrace();
				} catch (IOException e) {
					this.message = this.getFailMessage("发送失败");
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			this.message = this.getFailMessage("发送失败");
			e.printStackTrace();
		}
		return "json";
	}
	
//	/**
//	 * 即发送邮件，也发送手机短信
//	 */
//	public String regetPsdByEmailAndMsg(){
//		ahuser = this.ahUserBS.findUserByLoginname(cmUser.getLoginName());
//		String pwd=GenRandomNum.getInstance().genRandomNum(6);
//		if(ahuser != null){
//			if(!"".equals(ahuser.getMobile()) && ahuser.getMobile()!=null ){
//				//判断手机号是否有误
//				if(ahuser.getMobile().length()==11 && ahuser.getMobile().startsWith("1")){
//					try {
//						ahuser.setPassword(Md5.getInstance().EncoderByMd5(pwd, ""));
//					} catch (NoSuchAlgorithmException e1) {
//						this.message = this.getFailMessage("发送失败！");
//						e1.printStackTrace();
//					} catch (UnsupportedEncodingException e1) {
//						this.message = this.getFailMessage("发送失败！");
//						e1.printStackTrace();
//					}
//											
//					StringBuffer bufxml = new StringBuffer();
//					bufxml.append("<?xml version=");
//					bufxml.append("\"1.0\"");
//					bufxml.append(" encoding=");
//					bufxml.append("\"UTF-8\"");
//					bufxml.append("?><xmlRequest><header><syscode>");
//					bufxml.append("018");
//					bufxml.append("</syscode><depcode>");
//					bufxml.append("00000000");
//					bufxml.append("</depcode><depname>安华农业保险股份有限公司</depname><password>202cb962ac59075b964b07152d234b70</password></header><body><sms><mobile>");
//					bufxml.append(ahuser.getMobile());
//					bufxml.append("</mobile><content>");
//					bufxml.append(ahuser.getName()+"您好, 安华统一办公门户用户名为人力新工号:"+ ahuser.getHrid()+" ,密码为:"+pwd+"。 请通过公司内网登录并修改密码，勿向任何人泄露密码。");
//					bufxml.append("</content></sms></body></xmlRequest>");
//					
//					PostMethod post = new PostMethod("http://10.0.7.40:8080/smsHttpServlet.servlet");//请求地址
//					//这里添加xml字符串
//					post.setRequestBody(bufxml.toString());
//					
//					// 指定请求内容的类型
//					post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
//					HttpClient httpclient = new HttpClient();//创建 HttpClient 的实例
//					String responseXml;
//					try {
//						System.out.println(post.getResponseBodyAsString());//返回的内容
//						responseXml = post.getResponseBodyAsString();
//						if(!"".equals(responseXml) && responseXml != null  ){
//							if(responseXml.contains("000")){
//								ahUserBS.saveOrUpdate(ahuser);
//							}else if(responseXml.contains("001")){
//								this.message = this.getFailMessage("发送失败！");
//								return "json";
//							}
//						}
//						post.releaseConnection();//释放连接
//					} catch (HttpException e) {
//						this.message = this.getFailMessage("发送失败！");
//						//连接异常
//						e.printStackTrace();
//					} catch (IOException e) {
//						this.message = this.getFailMessage("发送失败！");
//						e.printStackTrace();
//					}
//				}else{
//					this.message = this.getFailMessage("您在HR系统中注册的手机号无效，请到HR系统中申请后再进行密码找回！");
//				}
//			}else{
//				this.message = this.getFailMessage("您未在HR系统中注册手机号，请到HR系统中申请后再进行密码找回！");
//			}
//			
//			// 通过邮箱找密码  判断这个人是否有邮箱
//			if(ahuser.getEmail() != null && !"".equals(ahuser.getEmail())){
//				//先判断邮箱是否是正确格式，再判断是否是安华的公司邮箱
//				if(ahuser.getEmail().matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
//						&& ahuser.getEmail().contains("@ahic.com.cn")){
//					ahUserBS.saveOrUpdate(ahuser);
//					String yuju = ahuser.getName()+","+"您好！"+"\r\n"+"您的新密码为："+pwd+"。一个小时之内有效，请尽快登录修改";
//					EmailUtil.sendEmail("请登录安华统一办公平台",yuju,ahuser.getEmail());
//					this.message = this.getSuccessMessage("您的申请已受理，请查看"+ahuser.getEmail()+"邮箱", "", "", "");
//				}else{
//					this.message = this.getFailMessage("您的注册邮箱不是公司邮箱,请到HR系统中申请后再进行密码找回！");
//				}
//			}else{
//				this.message = this.getFailMessage("您的注册邮箱不存在，请到HR系统中申请后再进行密码找回！");
//			}
//		
//		}else{
//			this.message = this.getFailMessage("该用户不存在");
//		}
//		
//		
//		return "json";
//	}
	
	
	/**
	 * 跳转至列表页面
	 * @return
	 */
	public String list() {
		try {
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			if(cmuser!=null)
			query.put("like_cmuser.name", cmuser.getName());
			//查询列表中的条数信息
			String countHql = "select count(*) from TfQualBaseSignature where 1=1 ";
			//设置最大条数
			this.getSysPageInfo().setMaxCount(this.tfQuaSignatureBS.getCountByHQL(countHql, Util.decodeQuery(query)));
			//列表
			String listHql = "from TfQualBaseSignature where 1=1 ";
			
			if(this.getSysOrderByInfo().getOrderAsc().equals("") && this.getSysOrderByInfo().getOrderColumn() == null){
				this.getSysOrderByInfo().setIfDate(false);
				this.getSysOrderByInfo().setOrderAsc("asc");
				this.getSysOrderByInfo().setOrderColumn("name");
			}
			
			tfQualBaseSignatureList = tfQuaSignatureBS.findPageByQuery(this.getSysPageInfo(), listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
			strQuery = Util.toStrQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
			this.getSysPageInfo().setCurrentPage(1);
			//设置日志信息
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}

		return "list";
	}
	
	/**
	 * CmUser页面显示
	 */
	public String cmUserList(){
		try {
			if (strQuery != null && !"".equals(strQuery)) {
				query = Util.toMap(strQuery);
			}
			//查询列表中的条数信息
			String countHql = "select count(*) from QuaCmUser where 1=1 ";
			//设置最大条数
			this.getSysPageInfo().setMaxCount(this.tfQuaSignatureBS.getCountByHQL(countHql, Util.decodeQuery(query)));
			
			//列表
			String listHql = "from QuaCmUser where 1=1 ";
			
			if(this.getSysOrderByInfo().getOrderAsc().equals("")&& this.getSysOrderByInfo().getOrderColumn() == null){
				this.getSysOrderByInfo().setIfDate(false);
				this.getSysOrderByInfo().setOrderAsc("asc");
				this.getSysOrderByInfo().setOrderColumn("name");
			}
			
			cmUserList = tfQuaSignatureBS.findPageByQuery(this.getSysPageInfo(), listHql, Util.decodeQuery(query), this.getSysOrderByInfo());
			strQuery = Util.toStrQuery(query);			
		} catch (Exception e) {
			e.printStackTrace();
			this.getSysPageInfo().setCurrentPage(1);
			//设置日志信息
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
		}

		return "cmUserList";

	}

	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String add() {
		return "add";
	}
	
	/**
	 * 添加签名信息时，判断是否已经存在
	 * @return
	 */
	
	public String exist(){
		List<TfQualBaseSignature> list=tfQuaSignatureBS.findPageByQuery(" from TfQualBaseSignature ps where ps.cmuser.userId ='"+tfQualBaseSignature.getCmuser().getUserId().trim()+"'");
		right=(list.size() == 0);
		return "json";
	}
	
	/**
	 * 保存添加信息   
	 * @return
	 */
	
	public String addSave() {
		try {	
			File file = myFile;
		    byte[] ret =  this.file2byte(file);
		    tfQualBaseSignature.setImageurl(ret);
		    tfQuaSignatureBS.save(tfQualBaseSignature);
			//设定成功消息
			this.message = this.getSuccessMessage(getText("addSuccess"), moduleName,"closeCurrent", "");

		} catch (Exception e) {
			//设置日志信息
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("addFail"));    
			e.printStackTrace();
		}
		return "updateSuccess";
	}
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	
	public String edit() {
		this.setSysOrderByInfo(this.getSysOrderByInfo());
		this.tfQualBaseSignature = tfQuaSignatureBS.findById(TfQualBaseSignature.class, tfQualBaseSignature.getSignatureid());
		fileList = tfQuaSignatureBS.searchFiles(tfQualBaseSignature.getSignatureid(), moduleName);		
		strQuery = Util.toStrQuery(query);
		return "edit";
	}
	
	/**
	 * 保存修改信息     
	 * @return
	 */
	public String editSave() {
		try {
			tfQualBaseSignature = tfQuaSignatureBS.findById(tfQualBaseSignature.getClass(), tfQualBaseSignature.getSignatureid());
			File file = myFile;
		    byte[] ret =  this.file2byte(file);
		    tfQualBaseSignature.setImageurl(ret);
		    tfQuaSignatureBS.saveOrUpdate(tfQualBaseSignature);
		    this.message = this.getSuccessMessage("修改成功", moduleName,"closeCurrent", "");
			
		} catch (Exception e) {
			//设置日志信息
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("updateFail"));
			e.printStackTrace();
		}
		return "updateSuccess";
	}
	
	
	/**
	 * 删除一条记录
	 * @return
	 */
	public String delete() {		
		
		try {
			tfQuaSignatureBS.deleteFileById(tfQualBaseSignature.getSignatureid(), moduleName);
			
			this.tfQuaSignatureBS.deleteById(TfQualBaseSignature.class, tfQualBaseSignature.getSignatureid());
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "tf-qua-apply/tf-qua-signature!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), moduleName,
						"forward", "tf-qua-apply/tf-qua-signature!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			//设置日志信息
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			//设定失败消息
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 删除多条记录
	 * @return
	 */
	public String multipleDelete() {
		try {
			tfQuaSignatureBS.deleteByIds(TfQualBaseSignature.class, this.getIds());
			if(null!=ids){
				String[] signids = ids.split(",");			
				for(String id:signids){
					tfQuaSignatureBS.deleteFileById(id, moduleName);				
				}
			}
			//设定成功消息，判断是否记录了排序需要的条件
			if(this.getSysOrderByInfo().getOrderColumn() != null && !this.getSysOrderByInfo().getOrderColumn().equals("")){
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
									"forward", "tf-qua-apply/tf-qua-signature!list.do?orderBlock=" + this.getSysOrderByInfo().getOrderColumn() + 
									"&orderMethod=" + this.getSysOrderByInfo().getOrderAsc() + "&pageNum=" + this.getPageNum() +
									"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			} else {
				this.message = this.getSuccessMessage(getText("deleteSuccess"), "",
						"forward", "tf-qua-apply/tf-qua-signature!list.do?pageNum=" + this.getPageNum() +
						"&numPerPage=" + this.getNumPerPage() + "&" + Util.toStrQuery(query));
			}
		} catch (Exception e) {
			tfQuaSignatureBS.getErrorLog().info(e.getMessage()+"#"+moduleName);
			this.message = this.getFailMessage(getText("deleteFail"));
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
	/**
	 * 读取base 签名照片方法   
	 * * @param hrid
	 * @return
	 */
	public String showSignature() {
		try {
			TfQualBaseSignature image= tfQuaSignatureBS.findUniqueBySingleQuery(TfQualBaseSignature.class.getSimpleName(), "cmuser.userId", userid);
			if(image != null && image.getImageurl() != null){
				imgStream = new ByteArrayInputStream(image.getImageurl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "image";
	}
	
	public String showSignatureBySignaid() {
		try {
			TfQualBaseSignature image= tfQuaSignatureBS.findUniqueBySingleQuery(TfQualBaseSignature.class.getSimpleName(), "signatureid", signingid);
			if(image != null && image.getImageurl() != null){
				imgStream = new ByteArrayInputStream(image.getImageurl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "image";
	}
	
	@JSON(serialize = false)
	public TfQuaSignatureBS getTfQuaSignatureBS() {
		return tfQuaSignatureBS;
	}
	public void setTfQuaSignatureBS(TfQuaSignatureBS tfQuaSignatureBS) {
		this.tfQuaSignatureBS = tfQuaSignatureBS;
	}
	
	public List<QuaCmUser> getCmUserList() {
		return cmUserList;
	}
	public void setCmUserList(List<QuaCmUser> cmUserList) {
		this.cmUserList = cmUserList;
	}
	public QuaCmUser getCmuser() {
		return cmuser;
	}
	public void setCmuser(QuaCmUser cmuser) {
		this.cmuser = cmuser;
	}
	public TfQualBaseSignature getTfQualBaseSignature() {
		if(tfQualBaseSignature == null){
			tfQualBaseSignature = new TfQualBaseSignature();			
		}
		return tfQualBaseSignature;
	}
	public void setTfQualBaseSignature(TfQualBaseSignature tfQualBaseSignature) {
		this.tfQualBaseSignature = tfQualBaseSignature;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}	
	@JSON(serialize = false)
	public List<TfQualBaseSignature> getTfQualBaseSignatureList() {
		return tfQualBaseSignatureList;
	}
	public void setTfQualBaseSignatureList(List<TfQualBaseSignature> tfQualBaseSignatureList) {
		this.tfQualBaseSignatureList = tfQualBaseSignatureList;
	}
	public HashMap<String, String> getQuery() {
		return query;
	}
	public void setQuery(HashMap<String, String> query) {
		this.query = query;
	}
	@JSON(serialize = false)
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@JSON(serialize = false)
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	@JSON(serialize = false)
	public List<CmAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<CmAttachment> fileList) {
		this.fileList = fileList;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	@JSON(serialize = false)
	public String getSigningid() {
		return signingid;
	}

	public void setSigningid(String signingid) {
		this.signingid = signingid;
	}
	
	public ByteArrayInputStream getImgStream() {
		return imgStream;
	}
	public void setImgStream(ByteArrayInputStream imgStream) {
		this.imgStream = imgStream;
	}
	public void setMyFile(File myFile)  {
	    this .myFile = myFile;
	}  
	@JSON(serialize = false)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@JSON(serialize = false)
	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	 public AhUser getAhuser() {
			return ahuser;
	}

	public void setAhuser(AhUser ahuser) {
		this.ahuser = ahuser;
	}
	@JSON(serialize = false)
	public IAhUserBS getAhUserBS() {
		return ahUserBS;
	}

	public void setAhUserBS(IAhUserBS ahUserBS) {
		this.ahUserBS = ahUserBS;
	}

	public CmUser getCmUser() {
		return cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}
	
}
