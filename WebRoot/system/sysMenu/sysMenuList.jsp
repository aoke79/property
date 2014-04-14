<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/property/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.contextmenu.r2.packed.js"></script>
<script>
	$('a[flag=menuTree]').contextMenu('menu', {
      //菜单样式
      menuStyle: {
        border: '1px solid #b8d0d6',
        width: '100'
      },
      //菜单项样式
      itemStyle: {
        fontFamily : '宋体, Arial',
        backgroundColor : '#f5f5f5',
        color: '#183152',
        border: 'none',
        padding: '1px',
        lineHeight: '25px',
        letterSpacing: '1px',
        paddingLeft: '7px'
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
            'menuAdd': function(t) {
            	var url = "sys-menu/sys-menu!add.do?sysMenu.parentMenu.id=" + $(t).attr("nodeId");
             	$("#menuAdd").children("a").attr("href",url).click();
            },
            'menuDel': function(t) {
              var url = "sys-menu/sys-menu!delete.do?sysMenu.id=" + $(t).attr("nodeId");
              $("#menuDel").children("a").attr("href",url).click();
            },
            'menuEdit': function(t) {
           		var url = "sys-menu/sys-menu!edit.do?sysMenu.id=" + $(t).attr("nodeId");
             	$("#menuEdit").children("a").attr("href",url).click();
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
    });
	
	$(function(){ 
   		closeParentIds.length = 0;
	    <s:iterator value="parentIds" var="openId">
		    var openId = "<s:property value='#openId'/>"
		    closeParentIds.push(openId);
	    </s:iterator>
	    $("a[flag=sinoframe]").each(function(){
	    	if($(this).attr("nodeId") == "${currentTreeNode}"){
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
<div class="contextMenu" id="menu">
      <ul>
        <li id="menuAdd"><img src="<%=basePath %>/images/smart_add.gif"/>&nbsp;<a class="menuItem" href="#"  messageHint="<s:property value='getText("addLeaveTips")' />" target="navTab"><s:property value="getText('add')"/></a></li>
        <li id="menuDel"><img src="<%=basePath %>/images/deleteMenu.gif" />&nbsp;<a class="menuItem" href="#" target="navTabTodo" title="<s:property value='getText("deleteTips")'/>"><s:property value="getText('delete')"/></a></li>
        <li id="menuEdit"><img src="<%=basePath %>/images/modifyMenu.gif" />&nbsp;<a class="menuItem" href="#"  messageHint="<s:property value='getText("editLeaveTips")' />" target="navTab"><s:property value="getText('modify')"/></a></li>
      </ul>
</div>
<div class="page">
	<div class="pageContent">
	<s:if test='parentId == null'>
	<ul class="tree collapse treeFolder" layoutH="0">
	</s:if>
	<s:else>
	<ul class="tree treeFolder" layoutH="0">
	</s:else>
		<s:iterator value="menuList.{?#this.id == '-1'}" var="first">
				<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a>
				<s:iterator value="menuList.{?#this.parentMenu != null && !#this.name.equals('个人常用菜单') && #this.parentMenu.id=='-1'}" var ="second">
					<ul>
						<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a>
							<s:iterator value="menuList.{?#this.parentMenu != null && #this.parentMenu.id==#second.id}" var="third">
								<ul>
									<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a>
										<s:iterator value="menuList.{?#this.parentMenu != null && #this.parentMenu.id==#third.id}" var="fourth">
											<ul>
												<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a>
													<s:iterator value="menuList.{?#this.parentMenu != null && #this.parentMenu.id==#fourth.id}" var="fifth">
														<ul>
															<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a>
																<s:iterator value="menuList.{?#this.parentMenu != null && #this.parentMenu.id==#fifth.id}">
																	<ul>
																		<li><a flag="menuTree" nodeId="${id}"><s:if test="%{#session['WW_TRANS_I18N_LOCALE'].equals(@java.util.Locale@US)}">${enName}</s:if><s:else>${name}</s:else></a></li>
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