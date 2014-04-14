<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>

<link href="<%=basePath%>/css/user.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/js/jquery.caret.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.ipaddress.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/user-validate.js"></script>
<script type="text/javascript">
      $(function(){
           var ids = '${loginRules.ex}'
           $("#"+ids).attr("checked","checkedid");
           
           var ts = 'T'+'${loginRules.timeType}'
           
           $("#"+ts).attr("selected","selected");

      });
</script>
<div class="page">
   <div class="pageContent">
      <form method="post" id="formUser" action="user-info/login-rules!modify.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
      <input id="ssss" name="loginRules.id" type="hidden" size="30"  value="${ loginRules.id}"/>
		 <div class="pageFormContent" layoutH="56">
         	     <p>
		 	<label>时间类型:</label>
		 	<select name="loginRules.timeType">
<option value="4" id="T4">分钟</option>
<option value="3" id="T3">小时</option>
<option value="2" id="T2">天</option>
<option value="1" id="T1">月</option>
<option value="0" id="T0">年</option>
		 	</select>
	     </p>
		 <p>
		 	<label>多久:</label>
		 	<input alt="只能输入整数" id="times" name="loginRules.times"  type="text" class="required"  size="30" value="${loginRules.times }"/>
	     </p>


	      <p>
		 	<label>限制次数:</label>
		 	<input alt="只能输入整数" id="numbers" name="loginRules.numbers" type="text" size="30" class="required"  value="${ loginRules.numbers}"/>
	     </p>
	      <p>
		 	<label>是否执行规则:</label>
		 	<input name="loginRules.ex" id="Y" type="radio" value="Y"     >是
  			<input name="loginRules.ex" id="N" type="radio" value="N" >否
	     </p>

		 </div>		 
		 <div class="formBar">
				<ul>
					<li>					
					<div class="buttonActive">
						<div class="buttonContent">
<button type="submit" id="but"><s:property value="getText('save')"/></button>
						</div>
					</div>					
					</li>

				</ul>
			</div>
	  </form>
   </div>
</div>
