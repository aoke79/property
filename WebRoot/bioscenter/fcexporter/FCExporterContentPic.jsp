<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.io.File" %>
<%@page import="java.util.StringTokenizer" %>
<%@page import="java.net.URL" %>
<%@page import="com.fusioncharts.exporter.FusionChartsExportHelper" %>
<%@page import="com.fusioncharts.exporter.beans.ExportBean" %>
<%@page import="com.fusioncharts.exporter.ErrorHandler" %>

<%
	/*
FCExporter.jsp has logic to validate the parameters, putting default values for missing parameters and 
then forwarding the request to appropriate jsp based on the requested export format.
*/

StringBuffer err_warn_Codes = new StringBuffer();
String WEB_ROOT_PATH = application.getRealPath("/");
String pathSeparator = File.separator; // will return either "\" or "/", depends on OS
String validation_def_filepath = WEB_ROOT_PATH+pathSeparator+FusionChartsExportHelper.RESOURCEPATH+"validation_def.jsp";
String relativePathToValidationDef = FusionChartsExportHelper.RESOURCEPATH+"validation_def.jsp";


/* ToDo - Not complete */
File f = new File(validation_def_filepath);
if(f.exists()) {
		// include this file
%>
<jsp:include page="<%=relativePathToValidationDef%>"/>
<%}


ExportBean localExportBean=FusionChartsExportHelper.parseExportRequestStream(request);
String exportFormat = (String)localExportBean.getExportParameterValue("exportformat");
String exporterFilePath = FusionChartsExportHelper.getExporterFilePath(exportFormat);
String exportTargetWindow = (String)localExportBean.getExportParameterValue("exporttargetwindow");

if (localExportBean.getMetadata().getWidth() == -1 || localExportBean.getMetadata().getHeight() == -1 || 
		localExportBean.getMetadata().getWidth() == 0 || localExportBean.getMetadata().getHeight() == 0 ) {
	
	//If Width/Height parameter is not sent, the ChartMetadata will have width/height as -1
	//Raise Error E101 - Width/Height not found
	err_warn_Codes.append("E101,");	
}

if (localExportBean.getMetadata().getBgColor() == null) {
	
	//Background color not available
	err_warn_Codes.append("W513,");	
}

if (localExportBean.getStream() == null  ) {
	
	//If image data not available
	//Raise Error E100
	err_warn_Codes.append("E100,");	
}
String exportAction = (String)localExportBean.getExportParameterValue("exportaction");
boolean isHTML = false;
if(exportAction.equals("download"))
	isHTML=true;
if(!exportAction.equals("download")) {
	String fileNameWithoutExt = (String)localExportBean.getExportParameterValue("exportfilename");
	String extension = FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());;
	String fileName = fileNameWithoutExt+"."+ extension;	
	err_warn_Codes.append(ErrorHandler.checkServerSaveStatus(WEB_ROOT_PATH,fileName));
}
if(err_warn_Codes.indexOf("E") != -1) {
String meta_values= localExportBean.getMetadataAsQueryString(null,true,isHTML);
%>
<jsp:forward page="FCExporterError.jsp" >
<jsp:param name="errorMessage" value="<%=err_warn_Codes.toString()%>" />
<jsp:param name="otherMessages" value="<%=meta_values%>" />
<jsp:param name="exportTargetWindow" value="<%=exportTargetWindow%>" />
<jsp:param name="isHTML" value="<%=isHTML%>" />
</jsp:forward>
<%
	return;
}
//Now include the jsp for this file format
//Check if this file exists before including
%>
<jsp:useBean id="exportBean" scope="request" class="com.fusioncharts.exporter.beans.ExportBean">
	<jsp:setProperty name="exportBean" property="metadata" value="<%=localExportBean.getMetadata()%>"/> 
	<jsp:setProperty name="exportBean" property="stream" value="<%=localExportBean.getStream()%>"/>
	<jsp:setProperty name="exportBean" property="exportParameters" value="<%=localExportBean.getExportParameters()%>"/>
</jsp:useBean>
<% 
File exporter = new File(WEB_ROOT_PATH+"bioscenter\\fcexporter\\"+pathSeparator+exporterFilePath);
if(exporter.exists()){
  String alReadyExportPicID=request.getParameter("alReadyExportPicID");
  String chartId=request.getParameter("chartId");
  String picCount=request.getParameter("picCount");
  String forwordPage=request.getParameter("forwordPage");
  String type=request.getParameter("type");
  if(picCount==null){
    picCount="1";
  }
 if ("excel".equals(type)) { 
%>
<jsp:forward page="<%=exporterFilePath%>">
   <jsp:param name="wordjsp" value="ExcelContentPic.jsp"/>
    <jsp:param name="alReadyExportPicID" value="<%=alReadyExportPicID%>"/>
   <jsp:param name="chartId" value="<%=chartId%>"/>
   <jsp:param name="picCount" value="<%=picCount%>"/>
   <jsp:param name="forwordPage" value="<%=forwordPage%>"/>
   <jsp:param name="type" value="<%=type%>"/>
</jsp:forward>
<% } else{
%>
<jsp:forward page="<%=exporterFilePath%>">
   <jsp:param name="wordjsp" value="WordContentPic.jsp"/>
    <jsp:param name="alReadyExportPicID" value="<%=alReadyExportPicID%>"/>
   <jsp:param name="chartId" value="<%=chartId%>"/>
   <jsp:param name="picCount" value="<%=picCount%>"/>
   <jsp:param name="forwordPage" value="<%=forwordPage%>"/>
   <jsp:param name="type" value="<%=type%>"/>
</jsp:forward>
<%
}}else {/* exception*/}%>
