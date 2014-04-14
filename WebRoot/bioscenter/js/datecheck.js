function isAvailable(start,end){
	start = start.replace("-", "/");
	start = start.replace("-", "/");
	end = end.replace("-", "/");
	end = end.replace("-", "/");
	var startDate = new Date(start).valueOf();
	var endDate = new Date(end).valueOf();
	if(endDate >= startDate){
		return true;
	}
	return false;
}

function formSubmit(form,button){
	button.disabled = true;
	form.submit();
}