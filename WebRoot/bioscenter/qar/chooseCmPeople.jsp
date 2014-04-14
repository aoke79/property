<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 
<script type="text/javascript">
	//点击单选框赋值
	function userNameRadio(value){
	 var obj=$(value); 
	 $("#secQarPilotReportJsp").find("input[name=query.in_hrpilotid]").val(obj.val());//考生id
	 
	 $("#secQarPilotReportJsp").find("input[name=queryName.pilotName]").val($("#userName"+obj.val()).val());//考生成绩
	 $("#chooseCmPeopleJsp").find("button.close").click();	
	} 
	//输入姓名查询匹配的项
	function search(){
		$("#d1").show();
		//keyword为空，将不发送Ajax请求
		if($("#chooseCmPeopleJsp").find("#matchName").val()==""){
		   clearTable();//清空列表
			return;
		}
	    //拿到输入框的值
	    var info=$("#chooseCmPeopleJsp").find("#myUserName").val();
	    //列表中的姓名
	    var name=$("input[tdname=0]");
	    var userNameInfo='';
	    for(k=0;k<name.length;k++){ 
	     userNameInfo+=$(name[k]).val()+",";
	    }
		//如果不为空，发送Ajax请求检索
		$.post("qar/qar-report!searchName.do",
		{"info":info,"userNameInfo":userNameInfo},
		function(data){ 
		   clearTable();//清空列表d
		   //设置table背景色
		   $("#d1").css({"background-color": "#FFFFFF" }); 
		   var jArrName=data.cmPeopleNameList;//名称list
		  
		   var infoTd=document.getElementById('infoTd'); 
		   
		   for(i=0;i<jArrName.length;i++){ 
			   $("#infoTd").find("tr").css({"line-height":"50px"});
				var tr = infoTd.insertRow(infoTd.rows.length);
				//鼠标移上去添加行背景色
				tr.onmouseover = function(){
					this.style.backgroundColor="#FFFF66";
				}
				//鼠标移开恢复行背景色
				tr.onmouseout = function(){
					this.style.backgroundColor="#FFFFFF";
				}
				$("#infoTd").find("tr").css({"line-height":"20px"});
				
				
				var nameTd = tr.insertCell(tr.cells.length);
				//将名称和ID拼起来，ID 是个隐藏域。
				nameTd.innerHTML = jArrName[i];
				//为单元格添加单击事件
				nameTd.onclick = function(){
				//截取字符串获取名称
				var name=this.innerHTML;
				$("#chooseCmPeopleJsp").find("#myUserName").val(name);//给名称input框赋值
			 	clearTable();//清空列表
			 	 $("#d1").hide();
				}; 
			} 
		},'json');
	}
	//清空列表
	function clearTable(){ 
	  var infoId=document.getElementById('infoTd');
		for(j=infoId.rows.length-1;j>=0;j--){
			infoId.deleteRow(j);
		}
	}
</script>
 
<div class="page" id="chooseCmPeopleJsp">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return dwzSearch(this, 'dialog');"
			action="qar/qar-report!chooseCmPeople.do" method="post">
			<input type="hidden" name="pageNum" value="1"/>	
			<input type="hidden" name="numPerPage" value="${numPerPage}" />
			<input type="hidden" id="orderField" name="orderBlock" value="${orderBlock}" />
		    <input type="hidden" id="orderMethod" name="orderMethod" value="${orderMethod}" />
		  
			<div class="searchBar" style= "z-index:20;position:relative">

				<table class="searchContent">
					   <tr>
						 <tr> 
							<td>
							     <p>
				 	                 <label>姓名:</label>
									  <input id="myUserName"  type="text" name="query.eq_name"  value="${query.eq_name }" onkeyup="search()"/>
	                              </p> 
							</td>
							<td>
							     <p>
				 	                 <label>单位:</label>
									  <input id="myCompany" name="query.company"  value="${query.company }" />
	                              </p> 
							</td> 
						</tr>  
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit" id="searchItemhidden" >
										<s:property value="getText('search')" />
									</button>
								</div>
							</div>
						</li> 
					</ul>
				</div>
			</div>
		</form>
	</div>

	<div class="pageContent"  > 
		<table class="table" layouth="140" targettype="dialog" id="dddd">
			<thead>
				<tr>
					<th width="40">						
					</th>
					<th width="120" orderField="deptId">
						姓名
					</th>					
					<th width="140" orderField="month">
						单位
					</th>
					
				</tr>
			</thead>
			<tbody>

			     <s:iterator value="cmPeopleList">
					<tr tdname="${username}" target="sid_user" rel="${hrid}" id="tr${hrid}" trname="1" >
						<td>
							<input name="ids" value="${hrid}" type="radio" onclick="userNameRadio(this);">
						</td>
	                        <td >
	                        <input type="hidden" id="userName${hrid}" value="${name}" tdname="0"> 
								${name}
							</td>
						
							<td>
							<input type="hidden" id="userCompany${hrid}" value="${sysOrganization.name}" tdname="1">
								${sysOrganization.name }
							</td>
							 
					</tr>
           </s:iterator>
           
			</tbody>
		</table> 
		<button style="display:none" class="close"></button> 
		  <div class="panelBar">

			<div class="pages">
				<span><s:property value="getText('display')" />
				</span>
				<s:select list='#{"20":"20","10":"10","5":"5"}' name="numPerPage"
					cssClass="paginationStyle"
					onchange="dwzPageBreak({targetType:$('#dddd'), numPerPage:this.value},1)"></s:select>
				
				<span><s:property value="getText('column')" />
					<s:property value="getText('total')" />
					<c:out value='${sysPageInfo.maxCount}' />
					<s:property value="getText('column')" />
				</span>
			</div>
			<div class="pagination" targetType="dialog"
				totalCount="${sysPageInfo.maxCount}" numPerPage="${numPerPage}"
				pageNumShown="5" currentPage="${sysPageInfo.currentPage}">
			</div>
		</div> 
		 
	</div>
</div> 
<div id="d1"  style= "z-index:60;position:absolute;float:left;overflow-y:auto;top:64px;left:103px;height:45px;width:125px;">
		<table id="infoTd">
		</table>
</div>