<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.contextmenu.r2.packed.js"></script>
<script>
$('a[flag=sinoframe]').contextMenu('orgMenu', {
      //菜单样式
      menuStyle: {
        border: '1px solid #b8d0d6',
        width: '125'
      },
      //菜单项样式
      itemStyle: {
        fontFamily : '宋体',
        backgroundColor : '#f5f5f5',
        color: '#183152',
        border: 'none',
        padding: '1px',
        lineHeight: '24px',
        letterSpacing: '1px',
        paddingLeft: '5px'
      },
      //菜单项鼠标放在上面样式
      itemHoverStyle: {
        color: '#4d8ec8',
        backgroundColor: 'white',
        border: 'none'
      },
      //事件    
      bindings: 
          {
            'orgAdd': function(t) {
            	var url = "sys-organization/sys-organization!toAddOrg.do?sysOrganization.parent_Fun.id=" + $(t).attr("nodeId");
             	$("#orgAdd").children("a").attr("href",url).click();
            },
            'deleteOrg': function(t) {
              var url = "sys-organization/sys-organization!delOrg.do?sysOrganization.id=" + $(t).attr("nodeId")
              $("#deleteOrg").children("a").attr("href",url).click();
            },
            'modifyOrg': function(t) {
           		var url = "sys-organization/sys-organization!toEditPage.do?sysOrganization.id=" + $(t).attr("nodeId")
             	$("#modifyOrg").children("a").attr("href",url).click();
            },
            'permission': function(t) {
           	 var url = "sys-permission/sys-permission!list.do?sysOrganization.id=" + $(t).attr("nodeId")
             $("#permission").children("a").attr("href",url).click();
            },
            'orgMapping': function(t) {
           	 var url = "sys-org-code-mapping/sys-org-code-mapping!list.do?sysOrganization.id=" + $(t).attr("nodeId")
             $("#orgMapping").children("a").attr("href",url).click();
            }
          }
    });
    
    $(function(){
 		windowHeight = $(window).height();
    	$(".menuItem").mousedown(function(){
    	
    		if($(this).attr("href")=="<%=basePath%>/#"){
    			$(this).parent("li").click();
    			return false;
    		}
    		$(this).parent("li").click();
    	})
    })
    
   $(function(){ 
   		closeParentIds.length = 0;
	    <s:iterator value="parentIds" var="openId">
		    var openId = "<s:property value='#openId'/>"
		    closeParentIds.push(openId);
	    </s:iterator>
	    $("a[flag=sinoframe]").each(function(){
	    	if($(this).attr("nodeId") == "${currentTreeNode}"){
	    		//alert("${nodeId}");
	    		$(this).parent("li:first").addClass("selected");
	    		<s:set name="currentTreeNode" value="null"></s:set>
	    	}
	    	$(this).mousedown(function(){
	    		$(".selected").removeClass("selected");
	    	})
	    })
    })
    
</script>
<style>

li{
	text-align: left;
}
</style>
<div flag="rightMenu">
<div class="contextMenu" id="orgMenu">
      <ul>
        <s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!toAddOrg.do"}.isEmpty()'>
        <li id="orgAdd"><img src="<%=basePath %>/images/smart_add.gif"/>&nbsp;<a class="menuItem" href="#" target="navTab">添加</a></li>
		</s:if> 
		<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!delOrg.do"}.isEmpty()'>
        <li id="deleteOrg"><img src="<%=basePath %>/images/deleteMenu.gif" />&nbsp;<a class="menuItem" href="#" target="navTabTodo" title="确定要删除吗?">删除</a></li>
		</s:if>
		<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-organization/sys-organization!toEditPage.do"}.isEmpty()'>
        <li id="modifyOrg"><img src="<%=basePath %>/images/modifyMenu.gif" />&nbsp;<a class="menuItem" href="#" target="navTab">修改</a></li>
		</s:if>
		<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-permission/sys-permission!list.do"}.isEmpty()'>
        <li id="permission"><img src="<%=basePath %>/images/dataStrict.png" />&nbsp;<a class="menuItem"	href="#" target="navTab" rel="sysPermission">数据权限</a></li>
		</s:if>
		<s:if test='!#session.sysOperateList.{?#this.operateAction=="sys-org-code-mapping/sys-org-code-mapping!list.do"}.isEmpty()'>
        <li id="orgMapping"><img src="<%=basePath %>/images/outerMapped.png" />&nbsp;<a class="menuItem"	href="#" target="navTab" rel="orgMappingManager">外部系统映射</a></li>
		</s:if>
      </ul>
</div>
<div class="page">
<div class="pageContent" layoutH="0">

<s:if test='parentId == null'>
<ul class="tree collapse treeFolder" id="orgTree">
</s:if>
<s:else>
<ul class="tree treeFolder" id="orgTree">
</s:else>
	<s:iterator value='listSysOrganization.{?#this.parent_Fun==null}' var="first">
		<li><a flag="sinoframe" ttype="${orgType }" tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}" >${name}</a>
			<s:iterator value="listSysOrganization.{?#this.parent_Fun != null && #this.parent_Fun.id==#first.id}" var="second">
		<ul>
			<li><a flag="sinoframe" ttype="${orgType }" tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}">${name}</a>
				<s:iterator value="listSysOrganization.{?#this.parent_Fun != null && #this.parent_Fun.id==#second.id}" var="third">
					<ul>
						<li><a flag="sinoframe" ttype="${orgType }"  tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}">${name}</a>
							<s:iterator value="listSysOrganization.{?#this.parent_Fun != null && #this.parent_Fun.id==#third.id}" var="fourth">
								<ul>
									<li><a flag="sinoframe" ttype="${orgType }" tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}">${name}</a>
										<s:iterator value="listSysOrganization.{?#this.parent_Fun != null && #this.parent_Fun.id==#fourth.id}" var="five">
										<ul>
											<li><a flag="sinoframe" ttype="${orgType }" tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}">${name}</a>
												<s:iterator value="listSysOrganization.{?#this.parent_Fun != null && #this.parent_Fun.id==#five.id}">
												<ul>
													<li><a flag="sinoframe" ttype="${orgType }" tvalue="${name}" nodeId="${id}" orgLevel="${orgLevel}" >${name}</a></li>
												</ul>
												</s:iterator>
											</li>
										</ul>
										</s:iterator>
									</li>
								</ul>
							</s:iterator>
						</li>
					</ul>
				</s:iterator>
			</li>
		</ul>
		</s:iterator>
	</li>
	</s:iterator>
</ul>
</div>
</div>
</div>
