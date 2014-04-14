function bios_getXmlHttpReq() {
	var xmlHttp;
	if (window.ActiveXObject) {
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e2){
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	} else if(window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	alert("Browser dose not support Ajax!");
}

function bios_sendRequest(xmlHttpReq, url, data){
	xmlHttpReq.open("POST", url, true);
	xmlHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	xmlHttpReq.send(data);
}