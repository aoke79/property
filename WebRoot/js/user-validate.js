
//*************************** 验证身份证代码开始**************************/
 
/**  
 * 身份证15位编码规则：dddddd yymmdd xx p   
 * dddddd：地区码   
 * yymmdd: 出生年月日   
 * xx: 顺序类编码，无法确定   
 * p: 性别，奇数为男，偶数为女  
 * <p />  
 * 身份证18位编码规则：dddddd yyyymmdd xxx y   
 * dddddd：地区码   
 * yyyymmdd: 出生年月日   
 * xxx:顺序类编码，无法确定，奇数为男，偶数为女   
 * y: 校验码，该位数值可通过前17位计算获得  
 * <p />  
 * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]  
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]   
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )   
 * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置  
 *   
 */  
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 
    idCard = trim(idCard.replace(/ /g, ""));   
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);   
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");// 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// 加权求和   
    }   
    valCodePosition = sum % 11;// 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return 'female'-女、'male'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
    
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else{   
        return null;   
    }   
//  可对传入字符直接当作数组来处理   
// if(idCard.length==15){   
// alert(idCard[13]);   
// if(idCard[13]%2==0){   
// return 'female';   
// }else{   
// return 'male';   
// }   
// }else if(idCard.length==18){   
// alert(idCard[16]);   
// if(idCard[16]%2==0){   
// return 'female';   
// }else{   
// return 'male';   
// }   
// }else{   
// return null;   
// }   
}   
 /**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   

//根据身份证18位取其出生年月
function getBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    return new Date(year,parseFloat(month)-1,parseFloat(day)).pattern("yyyy-MM-dd");
     
} 
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }   
//根据身份证15位取其出生年月
function getBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      return new Date(year,parseFloat(month)-1,parseFloat(day)).pattern("yyyy-MM-dd");  
}
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
} // JavaScript Document 	

     
/**      
 * 对Date的扩展，将 Date 转化为指定格式的String      
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
 * eg:      
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}  


//*************************** 验证身份证代码结束**************************/



//*************************** 验证用户录入信息是否正确开始**************************/

var identityCode=true;
$(function(){
	//验证登录名是否已经有人使用。
	$("#loginName").blur(function(){
		
//		if($("#loginName").siblings("span.error").text()==""){
//		$.post("user-info/cm-user!validateLoginName.do",{"cmUser.loginName":$(this).val(),"cmUser.userId":$("#userId").val()},
//			function(data){
//				if(data.success==false){
//					$("#loginNameError").html(data.myError);
//					loginName=false;
//				}else{
//					loginName=true;
//				}	
//		});	
//		}	
	})
//	
//	// 验证email是否已经有人使用。
//	$("#email").blur(function(){
//
//		if($("#email").siblings("span.error").text()==""){
//			$.post("user-info/cm-user!validateEmail.do",{"cmUser.email":$(this).val(),"cmUser.userId":$("#userId").val()},
//			function(data){
//				if(data.success==false){				
//					$("#emailError").html(data.myError);
//					email=false;
//				}else{
//					email=true;
//				}
//			});
//		}
//	});
//	
	$("#identityCode").blur(function(){
		if($(this).val()=="") return false;
		if($(this).val().length!=15&&$(this).val().length!=18){
			$("#identityCodeError").html("身份证号码的长度为15位或18位");
			identityCode=false;
		}
		identityCode=IdCardValidate($("#identityCode").val());
		if(identityCode==false){
			$("#identityCodeError").html("请查看你输入的身份证号是否正确");
			identityCode=false;
		}else{
			if(maleOrFemalByIdCard($("#identityCode").val())=="female"){
				$("#female").attr("checked",true);
			}else{
				$("#male").attr("checked",true);
			}
			if($(this).val().length==15){
				$("#birthday").attr("value",getBrithBy15IdCard($("#identityCode").val()));
			}else if($(this).val().length==18){
				$("#birthday").attr("value",getBrithBy18IdCard($("#identityCode").val()));
			}
		}
	})
//	$("#subUser").mousedown(function(){
//		$.post("user-info/cm-user!validateIp.do",{"ipFirst":$("#ipFirst").val(),"ipLast":$("#ipLast").val()},
//			function(data){
//				if(data.success==false){			
//					$("#ipError").html(data.myError);
//					ip=false;
//				}else{
//					ip=true;
//						
//					var url="";
//					if(typeof $("#userId").val()=="undefined"){
//						url="user-info/cm-user!doadd.do";
//					}else{
//						url="user-info/cm-user!doupdate.do";
//					}
//					
//					$("#loginName").blur();
//					$("#email").blur();
//					$("#identityCode").blur();
//					if($("#ipFirst").val()==""){
//						$("#ipError").html("第一个ip地址不可以为空");
//						return false;
//					}	
//					
//					if(loginName==false||email==false||identityCode==false||ip==false){
//						return false;
//					}
//					$("#formUser").attr("action",url);
//					showProgressBar=true;
//
//				}
//			});
//	})
//
//
//	//当鼠标的定位时将错误信息清除
//	$("#loginName").focus(function(){
//		$("#loginNameError").html("");
//	})	
//	//当鼠标定位时将错误信息清除掉
//	$("#email").focus(function(){
//		$("#emailError").html("");
//	});
//	//当鼠标定位时将错误信息清除掉
//	$("#identityCode").focus(function(){
//		$("#identityCodeError").html("");
//		
//	});
	
	
});
//*************************** 验证用户录入信息是否正确结束**************************/

