<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/property/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<style>
.clowBox {
	border: 1px solid red;
}
.requireBox {
	font-size: 14px;
	color: red;
	padding-top: 9px;
	padding-left: 2px;
}
.path {
	width: 85%;
	overflow-x: hidden;
	overflow-y: auto;
}
</style>

<script type="text/javascript">

	// 初始数据
	var queryFlag = "<s:property value='queryFlag'/>";
	var pageFlag="<s:property value='pageFlag'/>";
	var inputKey = $("#"+pageFlag).find("input[name=query.in_"+queryFlag+"]").val();
	// 如果有被选中的则选中
	if(inputKey != ""){
		var inputKeyArry = inputKey.split(",");
		for(var i = 0; i < inputKeyArry.length; i++ ){
			$("#chooseCaseJsp").find("input[name="+queryFlag+"]").each(function(){
				if($(this).val() == inputKeyArry[i])
					$(this).attr("checked","checked");
			});
		}
	}
	
	// 点击确定按钮时触发事件，将所选值返回secQarFlyDetailReportJsp页面
	$("#chooseCaseJsp").find("#chooseCaseBtn").click(function(){
		var inputKey = "";			// 给secQarFlyDetailReportJsp 页面上隐藏的赋值
		var inputValue = "";		// 给显示的赋值
		$("#chooseCaseJsp").find("input[name="+queryFlag+"]:checked").each(function(){
			inputKey += $(this).val()+",";
			inputValue += $(this).attr("inputValue")+","; 
		
		});
		
		if(inputKey != ""){
			inputKey = inputKey.substring(0,inputKey.length-1);
		}
		if(inputValue != ""){
			inputValue = inputValue.substring(0, inputValue.length-1);
		}
		$("#"+pageFlag).find("input[name=query.in_"+queryFlag+"]").val(inputKey);
		$("#"+pageFlag).find("input[flag="+queryFlag+"]").val(inputValue);
		$.pdialog.closeCurrent();
	});
	
	// 清空
	// $("#chooseCaseJsp").find("#chooseCaseClearBtn").click(function(){
	//	$("#chooseCaseJsp").find("input:checked").removeAttr("checked");
	//	$("#${pageFlag}").find("input[name=query.in_"+queryFlag+"]").val("");
	//	$("#${pageFlag}").find("input[name="+queryFlag+"]").val("");
	//});
	
	// 全选
	$("#chooseCaseJsp").find("#chooseBtn").click(function(){
		$("#chooseCaseJsp").find("input").attr("checked","checked");
	});
	
	// 全不选 
 	$("#chooseCaseJsp").find("#notChooseBtn").click(function(){
 		$("#chooseCaseJsp").find("input").removeAttr("checked");
 	});
</script>

<div class="page" id="chooseCaseJsp">
   <div class="pageContent">  
	  <form class="pageForm required-validate" >   
	     <div class="pageFormContent" layoutH="56">						
			<input type="hidden" name="queryFlay" value="<s:property value="queryFlay"/>">
			<div class="panelBar">	
				<ul class="toolBar" >
					<li><a class="add" id="chooseBtn"><span>全选</span></a></li>
					<li><a class="add" id="notChooseBtn"><span>全不选</span></a></li>
				</ul>
			</div>
							<s:if test="queryFlag == 'qwDutyDept' || queryFlag == 'crftoneuseid'">
								<!-- 机队 -->
								<s:iterator value="sysOrganizationList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="id"/>" inputValue="<s:property value="name"/>"><s:property value="name"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
								
							</s:if>
							<s:elseif test="queryFlag == 'qwEatrId' || queryFlag == 'acType'">
							<!-- 机型 -->
								<s:iterator value="baseAirplanTypeList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="atrid"/>"  inputValue="<s:property value="atrid"/>"><s:property value="atrid"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'crftid' || queryFlag == 'arn'">
							<!-- 飞机号 -->
								<s:iterator value="baseAirplanList" status="indexs">									
									<p style="width:40%"><input type="checkbox" name="${queryFlag }" value="<s:property value="crftid"/>" inputValue="<s:property value="crftid"/>" ><s:property value="crftid"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'qwEpath' || queryFlag == 'phase'">
							<!-- 飞行阶段 -->
								<s:iterator value="secBphaseList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="id"/>"  inputValue="<s:property value="phdesc"/>"><s:property value="phdesc"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'qwEweather'">
							<!-- 天气 -->
								<s:iterator value="qwEweatherList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="id"/>"  inputValue="<s:property value="secbname"/>"><s:property value="secbname"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'qwEfroad'">
							<!-- 道面 -->
								<s:iterator value="qwEfroadList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="id"/>"  inputValue="<s:property value="secbname"/>"><s:property value="secbname"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'qwEway'">
							<!-- 进近方式 -->
								<s:iterator value="qwEwayList" status="indexs">									
									<p><input type="checkbox" name="${queryFlag }" value="<s:property value="id"/>"  inputValue="<s:property value="secbname"/>"><s:property value="secbname"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'qarid' || queryFlag == 'eventNo'">
							<!-- 事件号 -->
								<s:iterator value="secQaritemList" status="indexs">									
									<p style="width:40%"><input type="checkbox" name="${queryFlag }" value="<s:property value="qarid"/>"  inputValue="<s:property value="itemIdAndDesc"/>"><s:property value="itemIdAndDesc"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							<s:elseif test="queryFlag == 'threecord' || queryFlag == 'dep' || queryFlag == 'arr'">
							<!-- 机场 -->
								<s:iterator value="baseAirportList" status="indexs">									
									<p style="width:40%"><input type="checkbox" name="${queryFlag }" value="<s:property value="threecore"/>"  inputValue="<s:property value="airportname"/>"><s:property value="airportname"/>
									<s:if test="(#indexs.index+1)%2==0"><br/></s:if></p>
								</s:iterator>
							</s:elseif>
							
						
		 </div>
		<div class="formBar" >
			<ul>	
						
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="chooseCaseBtn">确定</button>
						</div>
					</div>
				</li><%--
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="chooseCaseClearBtn">清空</button>
						</div>
					</div>
				</li>
				--%><li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
   </div>
</div>

