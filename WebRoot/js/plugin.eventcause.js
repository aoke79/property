	
	//增加行
	function addMain(){
		var templete = $("<tr id='main_"+addcountmain+"' flag='data'></tr>");
		//递增序号，替换掉tr或者td中存在[0]、_0或者(0)防止id相同,这么设计主要是为了后台取值时方便
		templete = templete.append(blockmain.replace(/\[0\]/g,"["+addcountmain+"]").replace(/_0/g,"_"+addcountmain).
							replace("processStat(\"0\")","processStat("+addcountmain+")"));
		//templete.find("span").parent().html("<span class='midlight' onclick='deleteMain(this)'>删除</span>");
		//找到最后一条存在的行,在其后拼接一行
		//var flag = false;
		for(var i=addcountmain-1;i>=0;i--){
			if($("#main_"+i).length>0){
				$("#main_"+i).after(templete);
			}
			break;
		}
		
		addcountmain++; colcountmain++;
	}
	
	function addSub(){
		var templetes = $("<tr id='sub_"+addcountsub+"' flag='data'></tr>");
		//递增序号，替换掉tr或者td中存在[0]、_0或者(0)防止id相同,这么设计主要是为了后台取值时方便
		templetes = templetes.append(blocksub.replace(/\[0\]/g,"["+addcountsub+"]").replace(/_0/g,"_"+addcountsub).
							replace("processStat(\"0\")","processStat("+addcountsub+")"));
		
		//templete.find("span").parent().html("<span class='midlight' onclick='deleteSub(this)'>删除</span>");
		//找到最后一条存在的行,在其后拼接一行
		//var flag = false;
		for(var i=addcountsub-1;i>=0;i--){
			if($("#sub_"+i).length>0){
				$("#sub_"+i).after(templetes);
			}
			break;
		}
		addcountsub++; colcountsub++;
	}
