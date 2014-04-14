/********************************************************************
* 功能:   禁止网页右键点击
* 作者:   HaoFeng
* 日期:   2009/03/13
*********************************************************************/
document.onkeydown = function() { if (event.ctrlKey) {return false; } } 
document.onselectstart = function() { return false; }
document.oncontextmenu = function() { return false; }