function addQudao(){
var win;
win=window.open("addQudao1.jsp","","height=368,width=250");
}


function tick() {
   var today
   today = new Date()
   Clock.innerHTML = today.toLocaleString()
   window.setTimeout("tick()", 1000);
}


function showHint(str) {
	if (str.length == 0) {
		document.getElementById("txtHint").innerHTML = "";
		return;
	}
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("\u60a8\u7684\u6d4f\u89c8\u5668\u4e0d\u652f\u6301Ajax\uff01");
		return;
	}
	var url = "/oneshow/user!show.action";
	url = url + "?name=" + str;
	xmlHttp.onreadystatechange = stateChanged;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}
function stateChanged() {
	if (xmlHttp.readyState == 4) {
	var res = xmlHttp.responseText;
	if(res==1){
		alert("\u8BF7\u8F93\u5165\u6E20\u9053ID");
		}else if(res==2){
		alert("\u8BF7\u8F93\u5165\u6E20\u9053\u540D\u79F0");
		}else if(res==3){
		alert("\u8BE5\u6E20\u9053ID\u6216\u6E20\u9053\u540D\u79F0\u5DF2\u5B58\u5728");
		}else if(res==4){
		alert("\u6DFB\u52A0\u5DF2\u6210\u529F,\u8BF7\u5173\u95ED\u672C\u9875");
		}
	}
}
function GetXmlHttpObject() {
	var xmlHttp = null;
	try {
   // Firefox, Opera 8.0+, Safari    
		xmlHttp = new XMLHttpRequest();
	}
	catch (e) {
   // Internet Explorer
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}
 function check(){
   var add_id = document.getElementById("add_id").value;
   var add_name = document.getElementById("add_name").value;
   var add_pass = document.getElementById("add_pass").value;
   if(add_id.length==0||add_id.length==""){
   alert("\u8BF7\u8F93\u5165\u6E20\u9053ID");
   return false;
   }
   if(add_name.length==0||add_name.length==""){
   alert("\u8BF7\u8F93\u5165\u6E20\u9053\u540D\u79F0");
   return false;
   }
    if(add_pass.length==0||add_pass.length==""){
   alert("\u8BF7\u8F93\u5165\u6E20\u9053\u5BC6\u7801");
   return false;
   }
   xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("\u60a8\u7684\u6d4f\u89c8\u5668\u4e0d\u652f\u6301Ajax\uff01");
		return;
	}
   var url = "/partner/addQudao";
	url = url + "?add_id=" + add_id+"&add_name="+add_name+"&add_pass="+add_pass;
	xmlHttp.onreadystatechange = stateChanged;
	xmlHttp.open("POST", url, true);
	xmlHttp.send(null);
   }
   
   function check11(){
   var add_id = document.getElementById("add_id").value;
   var add_name = document.getElementById("add_name").value;
   var add_pass = document.getElementById("add_pass").value;
   if(add_id.length==0||add_id.length==""){
   alert("\u8BF7\u8F93\u5165\u6E20\u9053ID");
   return false;
   }
   if(add_name.length==0||add_name.length==""){
   alert("PLEASE ENTER CHANNEL NAME");
   return false;
   }
   if(add_pass.length==0||add_pass.length==""){
   alert("PLEASE ENTER CHANNEL NAME");
   return false;
   }
   return true;
   }
   
   function changeQudao(id){
         window.location="hezuo?id="+id;
   }
   
   
   function kou(aa){
   var id = aa;
   var kou = document.getElementById("ko").value;
   window.location="hezuo?id="+id+"&kou="+kou+"&caozuo=2";
   }
   
    function modify(){
   var old_pass = document.getElementById("old_pass").value;
   var new_pass = document.getElementById("new_pass").value;
   var con_pass = document.getElementById("con_pass").value;
   if(old_pass.length==0||old_pass.length==""){
   alert("\u8BF7\u8F93\u5165\u539F\u5BC6\u7801");
   return false;
   }
   if(new_pass.length==0||new_pass.length==""){
   alert("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
   return false;
   }
    if(con_pass.length==0||con_pass.length==""){
   alert("\u8BF7\u786E\u8BA4\u65B0\u5BC6\u7801");
   return false;
   }
   if(new_pass!=con_pass){
   alert("\u4E24\u6B21\u8F93\u5165\u7684\u5BC6\u7801\u4E0D\u4E00\u6837");
   return false;
   }
   return true;
   }

