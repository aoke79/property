	/****************************************************
	* 功能:   check E-mail是否合法
	* 返回值: 合法时返回True;否则返回False
	* 作者:   HaoFeng
	* 日期:   2009/03/13
	*****************************************************/
	function IsEmail(userMail) {
		if ((userMail==null) || (userMail.length==0)) return true;
		if (userMail.indexOf("@")==-1) return false;
	    var NameList=userMail.split("@");
	    if (NameList.length!=2) return false;
	    if (NameList[0].length<1 ) return false;
	    if (NameList[1].indexOf(".")<=0) return false;
	    if (userMail.indexOf("@")>userMail.indexOf(".")) return false; 
	    if (userMail.indexOf(".")==userMail.length-1) return false;
	    return true;
	}
	/****************************************************
	* 功能:   check 日期是否为 2009/03/13格式
	* 返回值: 2009/02/01时返回True;2009/2/1返回False;支持闰年2月29号判断.
	* 作者:   HaoFeng
	* 日期:   2009/03/13
	*****************************************************/
	function check(str){
		var a=/^((((1[6-9]|[2-9]\d)\d{2})\/(0?[13578]|1[02])\/(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})\/(0?[13456789]|1[012])\/(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})\/0?2\/(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\/0?2\/29\/))$/
		if (!a.test(str)){			
			return false;
		} else {			
			return true;
		}
	}
	
	/****************************************************
	* 功能:   设置一个按钮状态为可用
	* 返回值: void
	* 作者:   HaoFeng
	* 日期:   2009/03/13
	*****************************************************/
	function setBtnEnabled (btnSubmit) {		
		btnSubmit.disabled = false;		
	}
	
	/****************************************************
	* 功能:   设置一个按钮状态为不可用
	* 返回值: void
	* 作者:   HaoFeng
	* 日期:   2009/03/13
	*****************************************************/
	function setBtnDisabled (btnSubmit) {		 
		btnSubmit.disabled = true;
	}
	
	/****************************************************
	* 功能:   为文本框设置光标
	* 返回值: void
	* 作者:   HaoFeng
	* 日期:   2009/03/13
	*****************************************************/			
	function setBtnEnabled (textParam) {		
		textParam.focus();
	}
	/****************************************************
	* 功能:   取得文本长度
	* 返回值: 文本长度
	* 作者:   HaoFeng
	* 日期:   2009/03/15
	******************************************************/
	function getTextLength(str){
     	return str.replace(/[^\x00-\xff]/g,"**").length;
    }
	/****************************************************
	* 功能:   判断文本长度是否大于 n
	* 返回值: 大于n时返回True 否则返回false
	* 作者:   HaoFeng
	* 日期:   2009/03/15
	******************************************************/
	function checkTextLength(TextValue,lengthValue){
	
		//判断文本长度
		if (getTextLength(TextValue) > 
								lengthValue){
			return true;
		}else{
			return false;
		}
	}	
	
	
	
	
	
	
	
	