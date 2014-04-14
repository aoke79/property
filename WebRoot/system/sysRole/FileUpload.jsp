<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 必要的 -->
<div id="deleteImg">
	<a  href="#" target="navTabTodo"></a>
</div>
<!-- 必要的 -->
<a id="scl" rel="_bank" target="dialog" width="800" height="800"
	max="true"></a>
<div class="page">

	<div class="pageContent">

		<!-- 此时的action是表单要提交的路径，并不是文件上传路径，这里我只是做了一个测试在文件上传Ation中接受表单数据 -->
		<form method="post" action="upload-file!test.do"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							附件上传：
						</td>
						<td>
							<!--
						        commonFiles/FileUpload.jsp?symbol=1 表示要弹出的文件，其中symbol标示的是当前的页面 
						         下面的fileKey要写成 fileKey+symbol 即 fileKey1 
						         下面的editfile要写成 editfile+symbol 即 editfile1 
						         下面的uploadInfo要写成 uploadInfo+symbol 即 uploadInfo1 
						        symbol最好以业务作为值 免得重复 
						     -->
							<a class="button" mask="true" target="dialog"
								href="commonFiles/FileUpload.jsp?symbol=1" width="500"
								height="280"><span>上传附件</span> </a>
							<!-- 此处的属性可以参考commonFiles/FileUpload.jsp这个文件-->
							<span id="uploadInfo1" script="upload-file!upload.do" auto="true"
								multi="true" buttonText="Browse" simUploadLimit="1"
								sizeLimit="214748364" queueSizeLimit="10"></span>
						</td>
						<td>

						</td>
					</tr>
					<!-- 
					<tr>
						<td>
							用户名：
						</td>
						<td>
							<input name="userName" value="" id="userName" type="text">
						</td>
					</tr>
					 -->
					<tr>
						<td>
							<!-- 在每个上传表单页面要有此标签 -->
							<input name="fileKey" value="" id="fileKey1" type="hidden">
						</td>
					</tr>
				</table>
				<!-- 必要的 -->
				<div id="editfile1"></div>

				<!-- 必要的 -->
				<div id="editfile">
				<s:iterator value="cmAttachmentList">
					<table>
						<tr>
							<td>
								
									<a
										href="upload-file.do?fileSrc=<s:property value="attchpath.substring(attchpath.indexOf(@com.sinoframe.common.ConstantList@UPLOADPATH))"/>"
										id="${attchpath}" width="600" height="400"> ${attchname}
									</a>
								
							</td>
							<td>
								<a class="partDel" onclick="deleteDateAndFile(this)"
									attchid="${attchid}" attchpath="${attchpath}">删除</a>
							</td>
						</tr>
					</table>
					</s:iterator>
				</div>
			</div>



			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									提交
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" onclick="navTab.closeCurrentTab()">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>