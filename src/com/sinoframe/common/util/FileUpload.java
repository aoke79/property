package com.sinoframe.common.util;

import java.io.*;

public class FileUpload {
	// 上传的文件数组 用于上传多个文件
	private File[] files;
	// 上传的文件数组 用于上传一个文件
	private File file;

	// 上传文件的类型 用于上传多个文件
	private String[] uploadContentTypes;

	// 上传文件的类型 用于上传一个文件
	private String uploadContentType;

	// 上传文件的名称 用于上传多个文件
	private String[] uploadFileNames;

	// 上传文件的名称 用于上传一个文件
	private String uploadFileName;
	// 文件保存路径
	private String savePath;
	// 文件标题
	private String title;

	public FileUpload() {
		super();
	}
	
    /**
     * 
     * @param files
     * @param uploadContentTypes
     * @param uploadFileNames
     * @param savePath
     * @param title
     */
	public FileUpload(File[] files, String[] uploadContentTypes,
			String[] uploadFileNames, String savePath, String title) {
		super();
		this.files = files;
		this.uploadContentTypes = uploadContentTypes;
		this.uploadFileNames = uploadFileNames;
		this.savePath = savePath;
		this.title = title;
	}
    /**
     * 
     * @param file
     * @param uploadContentType
     * @param uploadFileName
     * @param savePath
     * @param title
     */
	public FileUpload(File file, String uploadContentType,
			String uploadFileName, String savePath, String title) {
		super();
		this.file = file;
		this.uploadContentType = uploadContentType;
		this.uploadFileName = uploadFileName;
		this.savePath = savePath;
		this.title = title;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String[] getUploadContentTypes() {
		return uploadContentTypes;
	}

	public void setUploadContentTypes(String[] uploadContentTypes) {
		this.uploadContentTypes = uploadContentTypes;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileNames() {
		return uploadFileNames;
	}

	public void setUploadFileNames(String[] uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void uploadFiles() throws IOException {
		// 取得需要上传的文件数组
		File[] files = getFiles();
		for (int i = 0; i < files.length; i++) {
			// 以服务的文件保存路径和原文件名建立上传文件输出流
			
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ getUploadFileNames()[i]);
			// 以每个需要上传的文件建立文件文件输入流
			FileInputStream fis = new FileInputStream(files[i]);
			// 将每个需要上传的文件写到服务器对应的文件中
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		}

	}

	public void uploadFile() throws IOException {
		// 取得需要上传的文件数组
		File file = getFile();
		// 以服务的文件保存路径和原文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
				+ getUploadFileName());
		// 以每个需要上传的文件建立文件文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 将每个需要上传的文件写到服务器对应的文件中
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();

	}
	/**
	 *判断文件的后缀是否是指定的后缀
	 * @param strFileName 文件
	 * @param extensionName 后缀
	 * @return
	 */
	public static boolean checkExtension(String strFileName,String extensionName){
		boolean flg=false;
		FileOperate fileOperate=new FileOperate();
		String oldextensionName=fileOperate.getExtension(strFileName);
		if(oldextensionName.equalsIgnoreCase(extensionName)){
			flg=true;
		}
		return flg;
	}
	
	

}
