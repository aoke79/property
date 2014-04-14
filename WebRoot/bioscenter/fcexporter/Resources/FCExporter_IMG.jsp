<%
/**
* Copyright (c) 2009 Infosoft Global Private Limited
*
*/
/**
*  ChangeLog / Version History:
*  ----------------------------
*
*   1.0 [ 19 February 2009 ] 
*       - Added encoders
*       - can save as JPG/PNG/GIF
*  ----- General Comments ----
*  This jsp uses the exportBean present in the request scope to get the values required for image generation.
*  Encoder is chosen based on the requested image format (JPG, PNG, GIF).
*  The StringBuffer err_warn_Codes contains the error codes if any, during the execution.
*  In case of "save" action, if the file already exists in the specified, if overwrite is off, 
*  and Intelligent File Naming is on,
*  A new unique filename is created and data is saved to that file.
*  Finally, if there are any errors, the request is forwarded to the error page.
*/
%><%@ page language="java" contentType="text/html; charset=UTF-8"%><%@ page import="java.io.ByteArrayInputStream"%><%@ page import="java.io.ByteArrayOutputStream"%><%@ page import="com.meritit.fusionchart.OracleBlob"%><%@ page import="java.io.InputStream"%><%@page import="java.util.HashMap" %><%@page import="java.io.OutputStream" %><%@ page import="javax.imageio.ImageIO"%><%@ page import="java.awt.image.BufferedImage"%><%@ page import="javax.imageio.stream.FileImageOutputStream"%><%@page import="java.util.Iterator" %><%@page import="java.io.File" %><%@ page import="com.fusioncharts.exporter.generators.ImageGenerator"%><%@ page import="com.fusioncharts.exporter.encoders.JPEGEncoder"%><%@ page import="com.fusioncharts.exporter.encoders.BasicEncoder"%><%@ page import="com.fusioncharts.exporter.beans.ChartMetadata"%><%@page import="com.fusioncharts.exporter.FusionChartsExportHelper"%>
<%@ page import="java.io.File"%>
<%@ page import="javax.xml.parsers.DocumentBuilder"%>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.NodeList"%>
<%@ page import="com.fusioncharts.exporter.ErrorHandler"%>
<jsp:useBean id="exportBean" scope="request" class="com.fusioncharts.exporter.beans.ExportBean"/>
<%
String action= (String)exportBean.getExportParameterValue("exportaction");
String exportFormat = (String)exportBean.getExportParameterValue("exportformat");
String exportTargetWindow = (String)exportBean.getExportParameterValue("exporttargetwindow");

String fileNameWithoutExt = (String)exportBean.getExportParameterValue("exportfilename");
String extension = FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());;
String fileName = fileNameWithoutExt+"."+ extension;
String imageID=null;
String stream = (String)exportBean.getStream();
ChartMetadata metadata= exportBean.getMetadata();

boolean isHTML = false;
if(action.equals("download"))
	isHTML=true;

StringBuffer err_warn_Codes = new StringBuffer();

BufferedImage chartImage = ImageGenerator.getChartImage(stream,metadata);
String noticeMessage = "";
String meta_values= exportBean.getMetadataAsQueryString(null,false,isHTML);


if(!action.equals("download")){
	noticeMessage = "&notice=";
     // For servlet api before 2.1 use the following
	//String requestURL = HttpUtils.getRequestURL(request).toString();
	// in servlet api 2.1 use the following
	//String requestURL = request.getRequestURL().toString();
	String pathToWebAppRoot = request.getRealPath("/");//getServletContext().getRealPath("/");
	String pathToSaveFolder = pathToWebAppRoot+File.separator+FusionChartsExportHelper.SAVEPATH;
	File saveFolder = new File(pathToSaveFolder);
	String completeFilePath = pathToSaveFolder + File.separator + fileName;
	String completeFilePathWithoutExt = pathToSaveFolder + File.separator + fileNameWithoutExt;
	File saveFile = new File(completeFilePath);
	
	if(saveFile.exists()){
			noticeMessage+=ErrorHandler.getErrorForCode("W509");
			if(!FusionChartsExportHelper.OVERWRITEFILE){
				if(FusionChartsExportHelper.INTELLIGENTFILENAMING) {
					noticeMessage+=ErrorHandler.getErrorForCode("W514");
					completeFilePath= FusionChartsExportHelper.getUniqueFileName(completeFilePathWithoutExt,extension);
					File tempFile= new File(completeFilePath);
					fileName = tempFile.getName();
					noticeMessage+=ErrorHandler.getErrorForCode("W515")+ fileName;
					err_warn_Codes.append("W515,");
				}
			}
	}
	// In Save mode, send back Successful response back to chart
	// In save mode, isHTML is false
	String pathToDisplay=FusionChartsExportHelper.HTTP_URI+"/"+fileName;
	if (FusionChartsExportHelper.HTTP_URI.endsWith("/")) {
		pathToDisplay=FusionChartsExportHelper.HTTP_URI+fileName;
	}
				
	 meta_values =exportBean.getMetadataAsQueryString(pathToDisplay,false,isHTML);
	
	// Now encode and save to file
	FileImageOutputStream fios = new FileImageOutputStream(new File(completeFilePath));
	if( exportFormat.toLowerCase().equalsIgnoreCase("jpg") || exportFormat.toLowerCase().equalsIgnoreCase("jpeg")){
		JPEGEncoder jpegEncoder = new JPEGEncoder();
		try {
			jpegEncoder.encode(chartImage,fios);
		}catch(Throwable e){
			//TODO Unable to encode the buffered image
			err_warn_Codes.append("E516,");
		}
		chartImage=null;
	}
	else {
		
		BasicEncoder basicEncoder = new BasicEncoder();
		try {
			basicEncoder.encode(chartImage,fios,1F,exportFormat.toLowerCase());
		}catch(Throwable e){
			System.out.println(" Unable to encode the buffered image");
			err_warn_Codes.append("E516,");
		}
		chartImage=null;
	}
		if(err_warn_Codes.indexOf("E")<0){
			// if there are no errors
			out.print(meta_values+noticeMessage+"&statusCode=1&statusMessage=successful");
		}

	}
	else{
		     String contextPath = request.getRealPath("/");
    		    
		    // 读取配置文件
		    File f = new File(contextPath+"WEB-INF/resources/config.xml");
		   
		    // 读取配置文件中的数据源
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
		    DocumentBuilder builder = factory.newDocumentBuilder();    
		    Document doc = builder.parse(f);    
		    NodeList nl = doc.getElementsByTagName("fcExporterDataSource"); 
		    String strUrl = doc.getElementsByTagName("url").item(0).getFirstChild().getNodeValue();    
            String strUserName = doc.getElementsByTagName("username").item(0).getFirstChild().getNodeValue();
     		String strPassword = doc.getElementsByTagName("password").item(0).getFirstChild().getNodeValue(); 
	
	        OracleBlob ob=new OracleBlob(strUrl,strUserName,strPassword);
	        //response.flushBuffer();
			response.setContentType(FusionChartsExportHelper.getMimeTypeFor(exportFormat.toLowerCase()));
			if( exportFormat.toLowerCase().equalsIgnoreCase("jpg") || exportFormat.toLowerCase().equalsIgnoreCase("jpeg")){
				JPEGEncoder jpegEncoder = new JPEGEncoder();
				try {
					ByteArrayOutputStream osB =new ByteArrayOutputStream();
					jpegEncoder.encode(chartImage,osB);
					InputStream is = new ByteArrayInputStream(osB.toByteArray());
					//写入图片到数据库
					 imageID=ob.getID();
					 
					ob.writeBlob(null,is,"FUSIONCHART_IMAGE_TEMP",imageID,imageID+"."+exportFormat);
				}catch(Throwable e){
					err_warn_Codes.append("E516,");
					meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
					response.reset();
					response.setContentType("text/html");
					out.print(meta_values+noticeMessage+ErrorHandler.buildResponse(err_warn_Codes.toString(),isHTML));
					return;
				}
				chartImage=null;
			}
			else {
				BasicEncoder basicEncoder = new BasicEncoder();
				try {
					ByteArrayOutputStream osB =new ByteArrayOutputStream();
					basicEncoder.encode(chartImage,osB,1F,exportFormat.toLowerCase());
					InputStream is = new ByteArrayInputStream(osB.toByteArray());
                    imageID=ob.getID();
                    ob.writeBlob(null,is,"FUSIONCHART_IMAGE_TEMP",imageID,imageID+"."+exportFormat);
				}catch(Throwable e){
					err_warn_Codes.append("E516,");
					meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
					response.reset();
					response.setContentType("text/html");
					out.print(meta_values+noticeMessage+ErrorHandler.buildResponse(err_warn_Codes.toString(),isHTML));
					return;
				}
				chartImage=null;
			}
	}
if(err_warn_Codes.indexOf("E") != -1) {
	meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
	%>
	<jsp:forward page="../FCExporterError.jsp">
	<jsp:param name="errorMessage" value="<%=err_warn_Codes.toString()%>" />
	<jsp:param name="otherMessages" value="<%=meta_values%>" />
	<jsp:param name="exportTargetWindow" value="<%=exportTargetWindow%>" />
	<jsp:param name="isHTML" value="<%=isHTML%>" />
	</jsp:forward>
	<%}else{
	   String wordjsp=request.getParameter("wordjsp");
	   String alReadyExportPicID=request.getParameter("alReadyExportPicID");
	   String chartId=request.getParameter("chartId");
	   String picCount=request.getParameter("picCount");
	   String redirUrl=wordjsp+"?imageID="+imageID+"&exportFormat="+exportFormat;
	   String forwordPage=request.getParameter("forwordPage");
	   String type=request.getParameter("type");
		
	   if(forwordPage!=null){
	      redirUrl+="&forwordPage="+forwordPage;
	   }
	   if(type!=null){
	      redirUrl+="&type="+type;
	   }
	   if(alReadyExportPicID!=null){
	      redirUrl+="&alReadyExportPicID="+alReadyExportPicID;
	   }
	   if(chartId!=null){
	      redirUrl+="&chartId="+chartId;
	   }
	   if(picCount!=null){
	      redirUrl+="&picCount="+picCount;
	   }else{
	      redirUrl+="&picCount=1";
	   }
	   //System.out.println("redirUrl*****************"+redirUrl);
	   response.sendRedirect(redirUrl);
	  }
	%>