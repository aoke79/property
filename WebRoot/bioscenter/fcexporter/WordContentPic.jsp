<%@ page language="java" contentType="application/msword; charset=UTF-8"%>
<%@page import="java.net.URL" %>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.InputStreamReader" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Arrays" %>
<%

// 导出word  application/msword    导出Excel   application/vnd.ms-excel 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String alReadyExportPicID=request.getParameter("alReadyExportPicID");
String chartId=request.getParameter("chartId");
String imageID=request.getParameter("imageID");
String exportFormat=request.getParameter("exportFormat");
String picC=request.getParameter("picCount");
String forwordPage=request.getParameter("forwordPage");
String type=request.getParameter("type");
int picCount=1;
if(picC!=null){
  picCount=new Integer(picC);
}

String redirUrl=basePath+forwordPage;
if("0".equals(alReadyExportPicID)&&picCount>1){
   alReadyExportPicID=imageID;
   //跳转到显示页面
   redirUrl+="?alReadyExportPicID="+alReadyExportPicID;
   redirUrl+="&chartId="+chartId;
   redirUrl=redirUrl + "&autoExport=1&type="+type + "&forwordPage="+forwordPage;
   response.sendRedirect(redirUrl);
}else{
   int picNo=1;
   if(picCount>1){
     alReadyExportPicID=alReadyExportPicID+","+imageID;
     String imgIdArray[]=alReadyExportPicID.split(",");
     picNo=imgIdArray.length;
   }
   if(picNo<picCount){
     //跳转到显示页面
     redirUrl+="?alReadyExportPicID="+alReadyExportPicID;
     redirUrl+="&chartId="+chartId;
     redirUrl=redirUrl+ "&autoExport=1&type="+type+ "&forwordPage="+forwordPage;
     response.sendRedirect(redirUrl);
     
   
   }else{
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
   <%
      try{
           List <String> imgIdArray1=new ArrayList<String>();
           
           if(!("0".equals(alReadyExportPicID))){
              //imgIdArray1.add(alReadyExportPicID.split(",").);
              imgIdArray1=new ArrayList<String>(Arrays.asList(alReadyExportPicID.split(",")));   
           }else{
              imgIdArray1.add(imageID);
           }
           
           URL url=new URL(basePath+forwordPage);
           BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
           String s="";
           while((s=br.readLine())!=null){              
             for(int iImg=0;iImg<imgIdArray1.size();iImg++){
             	 String repToImageStr="<img src=\""+basePath+"bioscenter/fcexporter/imageShow.jsp?imageID="+imgIdArray1.get(iImg)+"&exportFormat="+exportFormat+"\">";
                //注意这里，需要根据实际情况修改
                String toBeReped="";
                if(iImg==0){
                   toBeReped="<div id=\"chartdivPG0\" align=\"center\">这里是图表PG0，这里的两个div必须在一行上！</div>";
                }
                if(iImg==1){
                   toBeReped="<div id=\"chartdivPG1\" align=\"center\">这里是图表PG1，这里的两个div必须在一行上！</div>";
                }
                
                s=s.replaceAll(toBeReped,repToImageStr);
              }
            
              out.println(s);
           }
           br.close();
      }catch(Exception e){
           e.printStackTrace();
      }
     }}%>
  </body>
</html>
