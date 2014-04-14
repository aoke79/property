<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>

<link href="<%=basePath%>/css/user.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.caret.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ipaddress.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user-validate.js"></script>
<script type="text/javascript">
      $(function(){
           

      });
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" id="formUser" action="user-info/login-rules!doAdd.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
      
		 <div class="pageFormContent" layoutH="56">
         	     <p>
		 	<label>时间类型:</label>
		 	<select name="loginRules.timeType">
		 	<option value="4">分钟</option>
<option value="3" >小时</option>
<option value="2">天</option>
<option value="1">月</option>
<option value="0">年</option>
		 	</select>
	     </p>
		 <p>
		 	<label>多久:</label>
		 	<input id="times" name="loginRules.times"  type="text" class="required"  size="30" alt="只能输入整数"/>
	     </p>


	      <p>
		 	<label>限制次数:</label>
		 	<input id="numbers" name="loginRules.numbers" type="text" size="30" class="required"  alt="只能输入整数"/>
	     </p>
	      <p>
		 	<label>是否执行规则:</label>
		 	<input name="loginRules.ex" id="Y" type="radio" value="Y" checked="checked">是
  			<input name="loginRules.ex" id="N" type="radio" value="N">否
	     </p>

		 </div>		 
		 <div class="formBar">
				<ul>
					<li>					
					<div class="buttonActive">
						<div class="buttonContent">
<input type="submit" value="保存" id="subUser" style="display:none">
							
<button type="submit" id="but"><s:property value="getText('save')"/></button>
						</div>
					</div>					
					</li>

				</ul>
			</div>
	  </form>
   </div>
</div>
