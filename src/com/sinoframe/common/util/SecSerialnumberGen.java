/**
 * 获取序列号
 */

package com.sinoframe.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinoframe.bean.SecSerialnumber;
import com.sinoframe.business.ISecSerialnumberBS;
@Component("secSerialnumberGen")
public class SecSerialnumberGen {
	
	private List<SecSerialnumber> secSerialnumberList = new ArrayList<SecSerialnumber>();
	@Transactional
	public synchronized String getSecSerialnumber(String serSource){
		StringBuffer bufferSdeptid = new StringBuffer();
		String hql = "from SecSerialnumber ss where ss.sersource = '" + serSource + "'";
		secSerialnumberList = this.getSecSerialnumberBS().findPageByQuery(hql);
		SecSerialnumber secSerialnumberTemp = secSerialnumberList.get(0);
		String strSespecidlid = secSerialnumberTemp.getSespecidlid();
		if(secSerialnumberList.size() != -1){
			String strTemp;
			String strSerIndex = strSespecidlid.substring(0, strSespecidlid.indexOf('{'));
			System.out.println("strSerIndex:" + strSerIndex);//FD
			strTemp = strSespecidlid.substring(strSespecidlid.indexOf('{'));
			System.out.println("strTemp:" + strTemp);//{单位}{时间}{序号}
			if(strSerIndex != null || !("".equals(strSerIndex))){
				bufferSdeptid.append(strSerIndex);
			}
			while(strTemp.indexOf('{') != -1){
				String strNotNull = strTemp.substring(strTemp.indexOf('{') + 1, strTemp.indexOf('}'));
				strTemp = strTemp.substring(strTemp.indexOf('}') + 1);
				System.out.println("strTemp:" + strTemp);//{时间}{序号}
				if(strNotNull != null || !("".equals(strNotNull))){
					if(strNotNull.equals("单位")){
						//bufferSdeptid.append("{");
						bufferSdeptid.append(secSerialnumberTemp.getSdeptid());
						//bufferSdeptid.append("}");
					} else if(strNotNull.equals("时间")){
						//bufferSdeptid.append("{");
						bufferSdeptid.append(DateTool.getDateTime(secSerialnumberTemp.getSdateformat()));
						//bufferSdeptid.append("}");
					} else if(strNotNull.equals("序号")){
						//bufferSdeptid.append("{");
						for(int i = 0; i < secSerialnumberTemp.getSerialnum() - secSerialnumberTemp.getSerial(); i++){
							bufferSdeptid.append("0");
						}
						bufferSdeptid.append(String.valueOf(secSerialnumberTemp.getSerial()));
						//bufferSdeptid.append("}");
						secSerialnumberTemp.setSerial(secSerialnumberTemp.getSerial() + 1);
						getSecSerialnumberBS().saveOrUpdate(secSerialnumberTemp);
					}
				}
			}
		}
		return bufferSdeptid.toString();
	}
	
	public ISecSerialnumberBS getSecSerialnumberBS() {
		return (ISecSerialnumberBS)this.getBean("secSerialnumberBS");
	}
	
	private Object getBean(String beanName) {
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return webApplicationContext.getBean(beanName);
	}

	public void setServletRequest(HttpServletRequest arg0) {
	}
}
