<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
      var statusCode="${message.statusCode}";
      var message="${message.messageInfo}";
      var navTabId="${message.navTabId}";
      var forwardUrl="${message.forwardUrl}"; 
      var callbackType="${message.callbackType}"
      var response = {statusCode:statusCode,
            message:message,
            navTabId:navTabId,
            forwardUrl:forwardUrl,
            callbackType:callbackType
      };
      if(window.parent.donecallback) window.parent.donecallback(response);
</script>