<%@ page language="java" contentType="image/jpeg;charset=utf-8" %>
<%@ page import="java.io.File"%><%@ page import="javax.xml.parsers.DocumentBuilder"%><%@ page import="javax.xml.parsers.DocumentBuilderFactory"%><%@ page import="org.w3c.dom.Document"%><%@ page import="org.w3c.dom.NodeList"%>
<%@page import="com.meritit.fusionchart.OracleBlob"%><%
    
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
    //图片在数据库中的记录ID
    String strID = request.getParameter("imageID");
    String exportFormat = request.getParameter("exportFormat");
    response.reset();
	response.setContentType("image/"+exportFormat);
    response.addHeader("Content-Disposition", "attachment; filename=\""+strID+"."+exportFormat+"\"");
        
    byte[] data = null;
    if(strID != null){
       //获取图片的byte数据
       data = ob.GetImgByteById(null,strID);
       ServletOutputStream op = response.getOutputStream();        
       op.write(data, 0, data.length);
       op.close(); 
       op = null;
       response.flushBuffer();
       //清除输出流，防止释放时被捕获异常
	   //out.clear();
	   out.flush();
       out = pageContext.pushBody();
    }
%>