// 选择查询条件 
	function chooseCase(flag,pageFlag){		
		$("#"+pageFlag).find("#chooseCaseBtn").attr("href","qar/qar-report!chooseCase.do?queryFlag="+flag+"&pageFlag="+pageFlag);		
		$("#"+pageFlag).find("#chooseCaseBtn").click();
	}
	
	
	// 返回报表的链接（处理参数 ，返回连接）
	function returnUrl(url,titleFlag,btnFlag,pageFlag){
		
		var param = ""; 
		
		var gtdate = $("#"+pageFlag).find("input[name=query.dtgteq_ddate]").val();
		var ltdate = $("#"+pageFlag).find("input[name=query.dtlteq_ddate]").val();
		
		if(gtdate == ""){
			alertMsg.warn("请填写航班运行开始日期");
			return false;
		}
		if(ltdate == ""){
			alertMsg.warn("请填写航班运行结束日期");
			return false;
		}
		if(ltdate < gtdate){
			alertMsg.warn("航班运行结束日期不能小于开始日期");
			return false;
		}
		// 典型事件统计需要的判断
		if(pageFlag == "secQarFlyDetailReportJsp"){
			if(titleFlag == "monthNumSign"){ 
				var qarid = $("#"+pageFlag).find("input[name=query.in_qarid]").val();
				if(qarid == "" || qarid.split(",").length > 1){
					alertMsg.warn("请选择一个超限项目");
					return false;
				}
			}
		}
		// 统计分析 需要的判断
		if(pageFlag == "secQarFlyReportJsp"){	
			if(titleFlag == "overrunSingle"){ 
				var qarid = $("#secQarFlyReportJsp").find("input[name=query.in_eventNo]").val();
				var alertchar = $("#secQarFlyReportJsp").find("select[name=query.eq_alertchar]").val();
				if(qarid == "" || qarid.split(",").length > 1){
					alertMsg.warn("请选择一个超限项目");
					return false;
				}
				if(alertchar == ""){
					alertMsg.warn("请选择超限程度");
					return false;
				}
			}
			
		}
		var query = "&";
		$("#"+pageFlag).find(":input[case=case]").each(function(){
			query += $(this).attr("name") + "=" +$(this).val() + "&";
			
		});
		var queryName = "&";
		$("#"+pageFlag).find(":input[case=name]").each(function(){
			
			queryName += $(this).attr("name") + "=" +$(this).val() + "&";
			
			
		});
		
		
		if(pageFlag == "secQarFlyDetailReportJsp"){
			var qwEqfTestd = "";
			$("#"+pageFlag).find("input[name=query.in_qwEqfTestd]:checked").each(function(){
				qwEqfTestd += $(this).val() + ",";
			});
			if(qwEqfTestd != ""){
				qwEqfTestd = qwEqfTestd.substring(0,qwEqfTestd.length-1);
				query += "query.in_qwEqfTestd=" + qwEqfTestd +"&";
			}
			query += "query.eq_qwEnight=" +( $("#"+pageFlag).find("input[name=query.eq_qwEnight]:checked").val() == undefined ? "" :$("#"+pageFlag).find("input[name=query.eq_qwEnight]:checked").val());
		}
		
		if(btnFlag != null){
			param += "btnFlag="+btnFlag;
			$("#"+pageFlag).find("input[name=param]").val(query);
		}
		
		$.pdialog.open("qar/qar-report!disposeUrl.do", 
		"reportShow", 
		"报表显示", 
		{width:1000,height:570,mask:true},"url="+url+query+queryName+"&titleFlag="+titleFlag+"&pageFlag="+pageFlag+"&"+param);
	}
	
	