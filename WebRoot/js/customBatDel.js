
function deleteSelected(btn){
	//回调方法，不考虑状态，无论正确错误都执行刷新
	function refreshCallback(json){
		DWZ.ajaxDone(json);
		if(json.navTabId){
			navTab.reloadFlag(json.navTabId);
		}else{
			navTabPageBreak();
		}
		if("closeCurrent"==json.callbackType){
			setTimeout(function(){navTab.closeCurrentTab();},100);
		}else if("forward"==json.callbackType){
			navTab.reload(json.forwardUrl);
		}
	}
	//复选框ids字符串拼接
	function _getIds(selectedIds){
		var ids="";
		navTab.getCurrentPanel().find("input:checked").filter("[name="+selectedIds+"]").each(function(i){
			var val=$(this).val();
			ids+=i==0?val:","+val;
		});
		return ids;
	}
	//ajax，删除
	function _doRemove(){
		var _data={};
		_data[selectedIds]=ids;
		$.ajax({
			type:'POST',url:$this.attr('url'),dataType:'json',cache:false,
			data:_data,
			success:refreshCallback,
			error:DWZ.ajaxError
		});
	}
	var $this=$(btn);
	var selectedIds=$this.attr("rel")||"ids";
	var ids=_getIds(selectedIds);
	if(!ids){
		alertMsg.error($this.attr("warn")||DWZ.msg("alertSelectMsg"));
		return false;
	}
	var title=$this.attr("title");
	if(title){
		alertMsg.confirm(title,{okCall:_doRemove});
	}else{
		_doRemove();
	}
	return false;
}