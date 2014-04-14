<%@ page language="java"
	import="java.util.*,com.sinoframe.common.ConstantList"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    String symbol=request.getParameter("symbol");			
%>
<script src="<%=path%>/uploadify/scripts/jquery.uploadify.v2.1.0.js"
	type="text/javascript"></script>
<script src="<%=path%>/uploadify/scripts/swfobject.js"
	type="text/javascript"></script>
<style>
.partDel {
	cursot: pointer;
	text-decoration: underline;
	color: red;
}
</style>

<script type="text/javascript">


        $(document).ready(function() {
        
          if(navigator.userAgent.indexOf("MSIE")>0) { 
           var uploadInfo=$("#uploadInfo<%=symbol%>");
            //true表示第一次用
           var flag=true;
           var script=uploadInfo.attr("script");
           //文件存放路径
           var folder="";
           //选中文件时是否自动上传
           var auto=uploadInfo.attr("auto")=='true'?true:false;
           //是否是多文件上传
           var multi=uploadInfo.attr("multi")=='true'?true:false;
           //最大上传限度
           var simUploadLimit=parseInt(uploadInfo.attr("simUploadLimit"));
           //文件大小限制
           var sizeLimit= parseInt(uploadInfo.attr("sizeLimit"));
            //一次上传的文件数目
           var queueSizeLimit=parseInt(uploadInfo.attr("queueSizeLimit"));
           //应许上传的文件格式
           var fileDesc="<%=ConstantList.FILEDESC%>";
           
           var fileExt="<%=ConstantList.FILEEXT%>";
           var str="";
           var imageSet=".jpg.gif.jpeg.png.bmp";
           
           if($("#fileupload").parent().find("OBJECT").length < 1){
        	   $("#fileupload").uploadify({
               
                'uploader'       : 'uploadify/scripts/uploadify.swf',
                'script'         : script,
                'cancelImg'      : '<%=path%>/uploadify/cancel.png',
                'folder'         : folder,
                'queueID'        : 'fileQueue', //和存放队列的DIV的id一致
                'fileDataName'   : 'fileupload', //和以下input的name属性一致
                'auto'           : auto, //是否自动开始
                'multi'          : multi, //是否支持多文件上传
                'buttonText'     : 'Browse', //按钮上的文字
                'simUploadLimit' : simUploadLimit, //一次同步上传的文件数目
                'sizeLimit'      : sizeLimit, //设置单个文件大小限制
                'queueSizeLimit' : queueSizeLimit, //队列中同时存在的文件个数限制
                'fileDesc'       : fileDesc, //如果配置了以下的'fileExt'属性，那么这个属性是必须的
                'fileExt'        : fileExt,//允许的格式  
            onComplete: function (event, queueID, fileObj, response, data) {
               
               //后台响应的数据 
               var response1=response.replace("/","\\");
               //把后台响应的数据存到隐藏域中
               $("#fileKey<%=symbol%>").val(response1+","+$("#fileKey<%=symbol%>").val());
               
               if(flag){
                //对后台响应的数据进行处理
                var src=response.split("_-_")[1];
                imagesrc=src.substr(src.lastIndexOf('\\')+1);
                imagesrc=imagesrc.replace("/","\\");
                src=src.replace("/","\\");
                //获得文件的后缀名字
                var ext=imagesrc.substr(imagesrc.lastIndexOf('.')+1);
                //根据不同得后缀显示则显示方式不同和操作不同
                /*
                if(imageSet.indexOf(ext.toLowerCase())!=-1){
                 //如果是图片则显示图片，并提供查看原图功能
                 str+=$("#editfile<%=symbol%>").html()+'<div style="display:inline;margin-left:10px">'+
                 '<table style="display:inline" >'+
                 '<tr><td>'+
                 '<a href1="upload-file!showImage.do?fileSrc='+imagesrc+'" href="javascript:void(0);" name="ssss" id="'+src+'"  width="600" height="400" rel="page1">'+
                 '<span flag="bugImg" pageNum="1">'+response.split("_-_")[0]+'</span>'+
                 '</a>'+
                 '</td></tr>'+
                 '</table>'+
                 '<div style="display:inline;margin-left:5px">'+
                 '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                 '</div></div>&nbsp;';
                }else{*/
                  //如果是普通文件则显示文件并提供下载
                  str+=$("#editfile<%=symbol%>").html()+'<div style="display:inline;margin-left:10px">'+
                  '<table style="display:inline" >'+
                  '<tr><td>'+
                  '<a href="upload-file.do?fileSrc='+src+'"  id="'+src+'"   width="600" height="400" >'+
                 // '<img src="<%=basePath%>images/file.png" flag="bugImg" pageNum="1" style="width: 150px; height: 100px; border: 1px solid" ></img>'+
                  '<span>'+response.split("_-_")[0]+'</span>'+
                  '</a>'+
                  '</td></tr>'+
                  '</table>'+
                  '<div style="display:inline;margin-left:5px">'+
                  '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                  '</div></div>&nbsp;';
                //}
                
                flag=false;
               }else{
                 //基本同上
                 var src=response.split("_-_")[1];
                 imagesrc=src.substr(src.lastIndexOf('\\')+1);
                 imagesrc=imagesrc.replace("/","\\");
                 src=src.replace("/","\\");
                 var ext=imagesrc.substr(imagesrc.lastIndexOf('.')+1);
                 /*
                 if(imageSet.indexOf(ext.toLowerCase())!=-1){
                 str+='<div style="display:inline;margin-left:10px">'+
                 '<table style="display:inline">'+
                 '<tr><td>'+
                 '<a href1="upload-file!showImage.do?fileSrc='+imagesrc+'" href="javascript:void(0);" name="ssss" id="'+src+'"   name="mggg" width="600" height="400" >'+
                 '<span  target="dialog" flag="bugImg" pageNum="1" >'+response.split("_-_")[0]+'</span>'+
                 '</a>'+
                 '</td></tr>'+
                 '</table>'+
                 '<div style="display:inline;margin-left:5px">'+
                 '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                 '</div></div>&nbsp;';
                 }
                 else{*/
                  str+='<div style="display:inline;margin-left:10px">'+
                  '<table style="display:inline">'+
                  '<tr><td>'+
                  '<a href="upload-file.do?fileSrc='+src+'"  id="'+src+'"   width="600" height="400" >'+
                 '<span>'+response.split("_-_")[0]+'</span>'+
                 // '<img src="<%=basePath%>images/file.png" flag="bugImg" pageNum="1" style="width: 150px; height: 100px; border: 1px solid" ></img>'+
                  '</a>'+
                  '</td></tr>'+
                  '</table>'+
                  '<div style="display:inline;margin-left:5px">'+
                  '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                  '</div></div>&nbsp;'; 
                //}
                
               }
              
               $("#editfile<%=symbol%>").html(str);
               
               smartDel();
          
       },
       onError: function(event, queueID, fileObj,errorObj) {
            //alert($("#fileupload").parent().html());
       
       
             if('File Size'==errorObj.type){
                if(confirm('文件过大是否要启动超大附件上传？')){
			      $("#IEpageContent").hide();
				  $("#FFpageContent").show();
				}
			  }
             
       },
       onCancel: function(event, queueID, fileObj){
       alertMsg.correct("取消了" + fileObj.name);
       }
            });
           }
        	   
             
            }

        });
</script>

<script type="text/javascript">
function uploadifyUpload(){
   $("#fileupload").uploadifyUpload();
}
</script>

<div class="pageContent" id="IEpageContent">
	<div id="fileQueue" class="fileQueue"></div>
	<input id="fileupload" type="file" name="image" />
	<p>

		<a href="javascript:;" onClick="javascript:uploadifyUpload()">开始上传</a>&nbsp;
		<a href="javascript:jQuery('#fileupload').uploadifyClearQueue()">取消所有上传</a>

	</p>
	<ol class=files></ol>
</div>

<div class="pageContent" id="FFpageContent">
	<div class="pageFormContent" id="FFpageContent">
		<form action="upload-file!upload.do" id="form1" name="form1"
			encType="multipart/form-data" method="post" target="hidden_frame"
			class="pageForm required-validate">
			<div class="pageFormContent" layoutH="56">
				<input type="file" id="fileupload" name="fileupload"
					style="width: 250">
				<input type="hidden" name="folder" value="niu">
				<INPUT type="submit" value="上传文件" id="conId">
				<ol class=files></ol>
				<br>
				<span id="showUpload"></span>
				<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
			</div>
		</form>

	</div>

</div>

<!-- 当时ff时用的js -->
<script type="text/javascript"> 
$("#conId").click(function(){
  $("#showUpload").append('正在上传<img src="<%=basePath%>images/upload.gif"/>');
});

  
function callback(msg)   
{   
              var imageSet=".jpg.gif.jpeg.png.bmp";
              document.getElementById("fileupload").outerHTML = document.getElementById("fileupload").outerHTML;   
              var str='';
              var success=msg.split("_-_")[0]+"上传成功！";
              $("#fileKey<%=symbol%>").val(msg+","+$("#fileKey<%=symbol%>").val());
              var src=msg.split("_-_")[1];
              var temp=src.substr(0,src.lastIndexOf('/'));
              imagesrc=src.substr(temp.lastIndexOf('/')+1);
              var ext=imagesrc.substr(imagesrc.lastIndexOf('.')+1);
              if(imageSet.indexOf(ext.toLowerCase())!=-1){
                  str+=$("#editfile<%=symbol%>").html()+'<div style="display:inline;margin-left:5px">'+
                  '<table style="display:inline" >'+
                  '<tr><td>'+
                  '<a href1="upload-file!showImage.do?fileSrc='+src+'" href="javascript:void(0);" name="ssss" id="'+src+'"   width="600" height="400" rel="page1">'+
                  '<img src="<%=basePath%>'+src+'" flag="bugImg" pageNum="1" style="width: 150px; height: 100px; border: 1px solid" ></img>'+
                  '</a>'+
                  '</td></tr>'+
                  '<tr><td>'+
                  '<span>'+msg.split("_-_")[0]+'</span>'+
                  '</td></tr>'+
                  '</table>'+
                  '<div style="display:inline;margin-left:10px">'+
                  '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                  '</div></div>&nbsp;';
              }else{
                  str+=$("#editfile<%=symbol%>").html()+'<div style="display:inline;margin-left:5px">'+
                  '<table style="display:inline" >'+
                  '<tr><td>'+
                  '<a href="upload-file.do?fileSrc='+src+'"   id="'+src+'"   width="600" height="400" >'+
                  //'<img src="<%=basePath%>images/file.png" flag="bugImg" pageNum="1" style="width: 150px; height: 100px; border: 1px solid"></img>'+
                  '<span>'+msg.split("_-_")[0]+'</span>'+
                  '</a>'+
                  '</td></tr>'+
                  '<tr><td>'+
                  //'<span>'+msg.split("_-_")[0]+'</span>'+
                  '</td></tr>'+
                  '</table>'+
                  '<div style="display:inline;margin-left:10px">'+
                  '<a cape="delElement" class="partDel" imgsrc="'+src+'" onclick="deleteSrc(this,<%=symbol%>)">删除</a>'+
                  '</div></div>&nbsp;';
              }
              $("#editfile<%=symbol%>").html(str).show();
              $("#showUpload").empty();
              $('<li></li>').appendTo('.files').text(success);
    
}   
</script>

<script language="JavaScript">
    <!--
function getOs()
{
    var OsObject = "";
   if(navigator.userAgent.indexOf("MSIE")>0) {
       $("#FFpageContent").hide();
   }else{
      $("#IEpageContent").hide();
   }
  
  
}
getOs();

  -->
</script>


