String.prototype.trim=function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim=function(){
        return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
        return this.replace(/(\s*$)/g,"");
}


Validator = {
Require : /.+/,
Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
Phone : /^((\(\d{4}\))|(\d{4}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,
Mobile : /^((\(\d{3}\))|(\d{3}\-))?13\d{9}$/,
Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
IdCard : /^\d{15}(\d{2}[A-Za-z0-9])?$/,
Currency : /^\d+(\.\d+)?$/,
Number : /^\d+$/,
Zip : /^[0-9]\d{5}$/,
QQ : /^[1-9]\d{4,8}$/,
Integer : /^[-\+]?\d+$/,
Double : /^[-\+]?\d+(\.\d+)?$/,
English : /^[A-Za-z]+$/,
EngCn	:  /^[A-Za-z0-9\-]+$/,
Chinese : /^[\u0391-\uFFE5]+$/,
UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
IsSafe : function(str){return !this.UnSafe.test(str);},
SafeString : "this.IsSafe(value)",
Limit : "this.limit(value.length,getAttribute('min'), getAttribute('max'))",
LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
Range : "getAttribute('min') < parseInt(value) && parseInt(value) < getAttribute('max')",
IntegerRange : "!isNaN(value) && value.indexOf('.') == -1 && parseInt(value) == value && getAttribute('min') < parseInt(value) && parseInt(value) < getAttribute('max')",
Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
CompareTo : "this.compare(value, getAttribute('operator'), document.getElementsByName(getAttribute('to'))[0].value)",
Custom : "this.Exec(value, getAttribute('regexp'))",
Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
ErrorItem : [document.forms[0]],
ErrorMessage : ["以下原因导致提交失败：\t\t\t\t"],
Validate : function(theForm, mode){
var obj = theForm || event.srcElement;
var count = obj.elements.length;
this.ErrorMessage.length = 1;
this.ErrorItem.length = 1;
this.ErrorItem[0] = obj;
for(var i=0;i<count;i++){
with(obj.elements[i]){

value = value.trim();

var _dataType = getAttribute("dataType");
if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined") continue;
this.ClearState(obj.elements[i]);
if(getAttribute("require") == "false" && value == "") continue;
switch(_dataType){
case "Date" :
case "Repeat" :
case "Range" :
case "IntegerRange" :
case "Compare" :
case "CompareTo":
case "Custom" :
case "Group" :
case "Limit" :
case "LimitB" :
case "SafeString" :
if(!eval(this[_dataType])) {
this.AddError(i, getAttribute("msg"));
}
break;
case "EngCn":
if(!this[_dataType].test(value)){
this.AddError(i, getAttribute("msg"));
break;
}
if(!eval("this.limit(value.length, getAttribute('min'), getAttribute('max'))")) {
this.AddError(i, getAttribute("msg"));
break;
}
break;
default :
if(!this[_dataType].test(value)){
this.AddError(i, getAttribute("msg"));
}
break;
}
}
}
if(this.ErrorMessage.length > 1){
mode = mode || 1;
var errCount = this.ErrorItem.length;
switch(mode){
case 2 :
for(var i=1;i<errCount;i++)
this.ErrorItem[i].style.color = "red";
case 1 :
alert(this.ErrorMessage.join("\n"));
if (this.ErrorItem[1].type != "hidden") {
	this.ErrorItem[1].focus();
}
break;
case 3 :
for(var i=1;i<errCount;i++){
try{
var span = document.createElement("SPAN");
span.id = "__ErrorMessagePanel";
span.style.color = "red";
this.ErrorItem[i].parentNode.appendChild(span);
span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
}
catch(e){alert(e.description);}
}
if (this.ErrorItem[1].type != "hidden") {
	this.ErrorItem[1].focus();
}
//this.ErrorItem[1].style.backgroundColor = '#ffcb99';
break;
default :
alert(this.ErrorMessage.join("\n"));
break;
}
return false;
}
return true;
},
limit : function(len,min, max){
min = min || 0;
max = max || Number.MAX_VALUE;
return min <= len && len <= max;
},
LenB : function(str){
return str.replace(/[^\x00-\xff]/g,"**").length;
},
ClearState : function(elem){
with(elem){
if(style.color == "red")
style.color = "";
var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
if(lastNode.id == "__ErrorMessagePanel")
parentNode.removeChild(lastNode);
}
},
AddError : function(index, str){
this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
},
Exec : function(op, reg){
return new RegExp(reg,"g").test(op);
},
compare : function(op1,operator,op2){
switch (operator) {
case "NotEqual":
return (op1 != op2);
case "GreaterThan":
return (op1 > op2);
case "GreaterThanEqual":
return (op1 >= op2);
case "LessThan":
return (op1 < op2);
case "LessThanEqual":
return (op1 <= op2);
default:
return (op1 == op2); 
}
},
MustChecked : function(name, min, max){
var groups = document.getElementsByName(name);
var hasChecked = 0;
min = min || 1;
max = max || groups.length;
for(var i=groups.length-1;i>=0;i--)
if(groups[i].checked) hasChecked++;
return min <= hasChecked && hasChecked <= max;
},
IsDate : function(op, formatString){
	op = op.replace("-", "/");
	var date = new Date(op);
	
	if (isNaN(date)) {
		return false;
	} else {
		return true;
	}
}
}