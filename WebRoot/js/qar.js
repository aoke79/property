	

	/**
	 * 是次数还是千次率的选择
	 * @param pageFlag	添加或者修改页面的标识
	 * @param radioValue	
	 */
	function clickRadio(pageFlag,radioValue){
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("td[flag=valueType2]").nextAll().remove();
		var ewqnumDiv = $("#"+pageFlag).find("#ewqnumDiv").html();	// 千次率范围
		var ewnumDiv = $("#"+pageFlag).find("#ewnumDiv").html();	// 次数范围
		var ewdwhnumDiv = $("#"+pageFlag).find("#ewdwhnumDiv").html();// 航段数
		var spaceDiv = $("#"+pageFlag).find("#spaceDiv").html();	// 空白td chooseRadioDiv
		var ewEqFtestdDiv = $("#"+pageFlag).find("#ewEqFtestdDiv").html();	// 飞行员选择主操作技术标准
		var str = "";
		var strDwhNum = "";
		if(radioValue == 0){
			str += ewnumDiv;
			strDwhNum = "<tr>"+ewEqFtestdDiv+spaceDiv+"</tr>";
		}else{
			str += ewqnumDiv;
			strDwhNum = "<tr>"+ewdwhnumDiv+ewEqFtestdDiv+"</tr>";
		}
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("td[flag=spanTd]").remove();
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("td[flag=valueType2]").after(str);
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("td[flag=valueType2]").parent("tr").nextAll().remove();
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").append(strDwhNum);
	} 
	
	function ewtypeChange(pageFlag,ewtypeObj){
		var contentTd = $("#"+pageFlag).find("#ruleTableDiv").html();
		var ewtypevalue = ewtypeObj.val();			// 种类的值
		var text = ewtypeObj.find("option:selected").text();
		//alert(text);
		$("#"+pageFlag).find("td[name=contentTd]").html(contentTd);	
		$("#"+pageFlag).find("td[name=contentTd]").find("select[flag=ewtype]").val(ewtypevalue);		// 给种类赋值	
		var ewcaseDiv = $("#"+pageFlag).find("#ewcaseDiv").html();	// 类型
		$("#"+pageFlag).find("td[name=contentTd]").find("tr[name=ruleTr]").append(ewcaseDiv);
		selectStr($("#"+pageFlag).find("td[name=contentTd]").find("#ewcase"),ewtypevalue);		// 根据选择将类型的selectr的option找到
		
		var dateDiv = $("#"+pageFlag).find("#dateDiv").html();		// 日期
		var alertDiv = $("#"+pageFlag).find("#alertDiv").html();	// 程度
		var ewqnumDiv = $("#"+pageFlag).find("#ewqnumDiv").html();	// 千次率范围
		var ewnumDiv = $("#"+pageFlag).find("#ewnumDiv").html();	// 次数范围
		var ewdwhnumDiv = $("#"+pageFlag).find("#ewdwhnumDiv").html();// 航段数
		var spaceDiv = $("#"+pageFlag).find("#spaceDiv").html();	// 空白td chooseRadioDiv
		var currentFlagRadioDiv = $("#"+pageFlag).find("#currentFlagRadioDiv").html();	// 当为趋势图的时候的单选按钮
		var ewCurrentNumDiv = $("#"+pageFlag).find("#ewCurrentNumDiv").html();	// 当为趋势图的时候的单选按钮
		if(ewtypevalue != ""){
			var str = "";
			if(ewtypevalue == "E"){			// 航班
				str += "<tr>"+alertDiv+ewnumDiv+"</tr>";
			}else if(ewtypevalue == "F"){	// 飞行员
				str += "<tr>"+alertDiv+spaceDiv+"</tr>";				
			}else if(ewtypevalue == "N"){	// 趋势
				str += "<tr>"+dateDiv+alertDiv+"</tr><tr>"+currentFlagRadioDiv+ewCurrentNumDiv+"</tr>";
			}else{
				str += "<tr>"+dateDiv+alertDiv+"</tr><tr>"+ewqnumDiv+spaceDiv+"</tr>";
			}
			if(ewtypevalue == "F"){
				$("#"+pageFlag).find("span[flag=requiredSpan]").css("display","inline");
				$("#"+pageFlag).find("#ewcase").attr("item","submit");
				$("#"+pageFlag).find("#ewcase").attr("tips","类型");
			}else{
				$("#"+pageFlag).find("span[flag=requiredSpan]").css("display","none");
				$("#"+pageFlag).find("#ewcase").removeAttr("item");
				$("#"+pageFlag).find("#ewcase").removeAttr("tips");
			}
		}		
		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").append(str);	
		$("#"+pageFlag).find("td[name=contentTd]").find("td[name=tdTitle]").remove();
		$("#"+pageFlag).find("td[name=contentTd]").find("td[name=tdBody]").remove();		
	}	
	
	function ewtypeChangeByListJsp(pageFlag,ewtypeObj){
		var contentTd = $("#"+pageFlag).find("#ruleTableDiv").html();
		var ewtypevalue = ewtypeObj.val();			// 种类的值
		var text = ewtypeObj.find("option:selected").text();
		selectStr($("#"+pageFlag).find("#ewcase"),ewtypevalue);		// 根据选择将类型的selectr的option找到
		$("#"+pageFlag).find("#ewcase").addClass('width','200px');
	}		
	
	/**
	 * 给条件下拉框赋值
	 */
	function selectStr(selectObj,optStr){
		// 条件的下拉框
		var str="<option value=''>请选择</option>";
		if(optStr == "E"){			// 航班
			str += "<option value='G'>起飞阶段</option>"+
			"<option value='H'>中间阶段</option>"+
			"<option value='J'>落地阶段</option>";
		}else if(optStr == "F"){	// 飞行员
			str += "<option value='K'>时间</option>"+
			"<option value='L'>航班号</option>";
		}else if(optStr == "N"){
			str +=  "<option value='C'>分公司</option>"+
			"<option value='D'>大队</option>"+
			"<option value='F'>飞行员</option>";
		}else{
			str += "<option value='A'>机场</option>"+
				"<option value='B'>机型</option>"+
				"<option value='C'>分公司</option>"+
				"<option value='D'>大队</option>"+
				"<option value='M'>事件</option>";
		}
		
		selectObj.empty();				// 清除数据
		selectObj.html(str);			// 添加数据
		selectObj.find("option").each(function(){	// 将选择的数据去除
			if($(this).val() == optStr && optStr != ""){					
				$(this).remove();
			}
		});	
		
	}
	
/*
 *在IE中处理option单击事件
 */
 function simOptionClick4IE(pageFlag,ewcaseObj){
		var ewcasevalue = ewcaseObj.val();
	
			 var evt=window.event;     
			    var selectObj=evt?evt.srcElement:null;
			      // IE Only    
			      if (evt && selectObj &&  evt.offsetY && evt.button!=2    
			        && (evt.offsetY > selectObj.offsetHeight || evt.offsetY<0 ) ) {      
			        setTimeout(function(){			        	   
			        	if(ewcasevalue=="K"||ewcasevalue=="L"){
			        	   var dateDiv = $("#"+pageFlag).find("#dateDiv").html();		// 日期
			        	   var alertDiv = $("#"+pageFlag).find("#alertDiv").html();	// 程度
			       		   var ewqnumDiv = $("#"+pageFlag).find("#ewqnumDiv").html();	// 千次率范围
			       		   var ewnumDiv = $("#"+pageFlag).find("#ewnumDiv").html();	// 次数范围
			       		   var chooseRadioDiv = $("#"+pageFlag).find("#chooseRadioDiv").html();	// 飞员需要选择规则的添加是千次率还是次数
			       		   var spaceDiv = $("#"+pageFlag).find("#spaceDiv").html();	// 空白td chooseRadioDiv
			       		   var ewEqFtestdDiv = $("#"+pageFlag).find("#ewEqFtestdDiv").html();	// 飞行员选择主操作技术标准
			       		   
			       		   if(ewcasevalue=="L"){		// 航班				       			
			       		   		$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("tr").eq(1).nextAll().remove();
			       				$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").append("<tr>"+alertDiv+ewnumDiv+"</tr>"+"<tr>"+ewEqFtestdDiv+spaceDiv+"</tr>");
			       		   }else{			       			  
			       				$("#"+pageFlag).find("td[name=contentTd]").find("#ruleTable").find("tr").eq(1).nextAll().remove();
			       				$("#"+pageFlag).find("td[name=contentTd]")
			       					.find("#ruleTable").append("<tr>"+dateDiv+alertDiv+"</tr><tr>"+chooseRadioDiv+spaceDiv+"</tr>");
			       		   }
			         	}
			       }, 60);    
			    }
	}
