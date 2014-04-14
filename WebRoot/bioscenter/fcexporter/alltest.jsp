<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int picCunt=2;//要导出的图片数量，注意：52行和100行
%>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0">    
   <script language="JavaScript" src="../../js/FusionCharts.js"></script>
   <script language="JavaScript" src="../../js/FusionChartsExportComponent.js"></script>
   <script language="JavaScript" src="../../js/jquery-1.4.2.min.js"></script>
</head>
<body bgcolor="#ffffff">
<div>
  <a href="javascript:exportWord('myChartId0','http://localhost:8080/report/bioscenter/fcexporter/FCExporterContentPic.jsp?chartId=myChartId0&picCount=<%=picCunt%>&alReadyExportPicID=0&type=excel&forwordPage=bioscenter/fcexporter/alltest.jsp')">点我：输出内容和图片到Excel</a>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:exportWord('myChartId0','http://localhost:8080/report/bioscenter/fcexporter/FCExporterContentPic.jsp?chartId=myChartId0&picCount=<%=picCunt%>&alReadyExportPicID=0&type=word&forwordPage=bioscenter/fcexporter/alltest.jsp')">点我：输出内容和图片到word</a>
  
  <table><tr><td>
   <div id="chartdivPG0" align="center">这里是图表PG0，这里的两个div必须在一行上！</div>
   </td></tr></table>
   <br>
  
   <div>我给中间加点这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！<br>
   </div></div>
   <table><tr><td></br>
  <div id="chartdivPG1" align="center">这里是图表PG1，这里的两个div必须在一行上！</div>
   </td></tr></table>
   
   <div>我加点内容：<br>  这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！这是下面的内容！</div>
  
   <script type="text/javascript">
     //显示所有图表
     var myChart = new FusionCharts("swf/Column2D.swf", "myChartId0", "500", "300", "0", "1");
     myChart.setDataURL("xml/word_content_pic_PG0.xml");
     myChart.render("chartdivPG0");
     var myChart = new FusionCharts("swf/Column2D.swf", "myChartId1", "500", "300", "0", "1");
     myChart.setDataURL("xml/word_content_pic_PG1.xml");
     myChart.render("chartdivPG1");
     /**
        导出图片和内容到Word
       exportHandlerStr 导出图片数据流的接收程序URL地址，包含CGI信息
     */
     var chartID_x;
     var exportHandlerStr_x;
     function exportWord(chartID,exportHandlerStr){
        chartID_x=chartID;
        exportHandlerStr_x=exportHandlerStr;
        setTimeout("export_x()",100);
     }
     function export_x(){
         var chartObject = getChartFromId(chartID_x);
         if(chartObject.hasRendered()){
           chartObject.exportChart({ exportAtClient: '0',
                                        exportFormat: 'JPEG',
                                        exportAction: 'download',
                                        exportHandler:exportHandlerStr_x
                                    } );
         }else{
           //注意此处递归，小心使用
           setTimeout("export_x()",100);
         }
     }
     $(document).ready(function(){
        var chartId="<%=request.getParameter("chartId")%>";//当前需要进行导出的图表ID
        var alReadyExportPicID="<%=request.getParameter("alReadyExportPicID")%>";//已经存储到数据库的图片ID列表，用","分割
        var autoExport="<%=request.getParameter("autoExport")%>";//是否自动导出当前图片
        var forwordPage="<%=request.getParameter("forwordPage")%>";
        var type="<%=request.getParameter("type")%>";

        //导出当前图片
        if(autoExport=='1'){
           //注意：要导出的图片数量在此定义
           var picCount='<%=picCunt%>';
           var exportHandlerStr="http://localhost:8080/report/bioscenter/fcexporter/FCExporterContentPic.jsp";
           //计算当前要到导出的图片ID
           var chartNo=parseInt(chartId.replace(/[^\d]/g,''))+1;
           var curChartId="myChartId"+chartNo;
           exportHandlerStr+="?alReadyExportPicID="+alReadyExportPicID+"&chartId="+curChartId;
           exportHandlerStr+="&picCount="+picCount;
           exportHandlerStr+="&type="+type;
           exportHandlerStr+="&forwordPage="+forwordPage;
           exportWord(curChartId,exportHandlerStr);
        }             
     });
    </script>      
</body>
</html>