package com.sinoframe.common;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FileConfigSystem {
	private String rootPath;

	private Map<String,String> fileMap;

	private static FileConfigSystem fcs;

	public Map<String,String> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String,String> fileMap) {
		this.fileMap = fileMap;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void init() {
		Set<String> set = fileMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String fileValue = fileMap.get(it.next());
			File file = new File(rootPath, fileValue);
			if (!file.exists())
				file.mkdirs();
		}

	}

	public File mappingFile(String key) {
		String fileValue = (String) fileMap.get(key);
		File file = new File(rootPath, fileValue);
		return file;

	}
	
	public String mappingString(String key) {
		String fileValue = (String) fileMap.get(key);
		File file = new File(rootPath, fileValue);
		return file.getAbsolutePath();

	}
	

	public File getTemplate() {
		return mappingFile("templateKey");
	}

	public static FileConfigSystem getInstance() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		   WebApplicationContext webApplicationContext= 
	            WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		if (fcs == null)
			fcs = (FileConfigSystem) webApplicationContext.getBean(
					"fileConfigSystem");
		return fcs;
	}
	
}
