<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>助记码的select选择完美方案</title>
</head>
<script>
	
	var addressName=new Array("沈阳","北京","大连","抚顺","天津","上海");
	var addressHelpCode=new Array("sy","bj","dl","fs","tj","sh");
	var getSelectHelpCode=document.getElementsByName("selectHelpCode");

	function init(){
		with(getSelectHelpCode[0]){
			length=addressName.length;
			for(var i=0;i<length;i++){
				options[i].value=addressHelpCode[i];
				options[i].text=addressName[i];
			}
		}
	}

	function writeSelect(obj){
		obj.options[obj.selectedIndex].text=obj.options[obj.selectedIndex].text + String.fromCharCode(event.keyCode);
		event.returnValue=false;
	}
	
	function catch_keydown(sel)
	{
		switch(event.keyCode)
		{
			case 46:
				//delete
				sel.options[sel.selectedIndex].text ="";
				event.returnValue = false;
				break;
			case 8:
				//Back Space;
				var s = sel.options[sel.selectedIndex].text;
				sel.options[sel.selectedIndex].text = s.substr(0,s.length-1);
				event.returnValue = false;
				break;
			case 13:
				//Enter;
				vertiyt(sel)
				event.returnValue = false;
				break;
		}
		
	}

	function vertiyt(obj){
	    var yy=false;
		var jj=-1;
		with(obj){
			for(var i=0;i<addressName.length;i++){
				var selectedValue=options[selectedIndex].value;
				var selectedText=options[selectedIndex].text;
				if(addressName[i]==selectedText||addressHelpCode[i]==selectedText){
					yy=true;
					jj=i;
				}
			}
		}
		if(yy==true){
			init();
			obj.options[jj].selected=true;
		}else{
			init();
			alert("没有\""+selectedText+"\"这个地点或是助记码！！");
		}
	}


</script>
<body onload="init()">
<select name="selectHelpCode" onBlur="vertiyt(this)" onkeydown="catch_keydown(this)" onkeypress="writeSelect(this)"><option>--</option></select>
<br>
让select变得可以输入，并且可以验证。
<br>
这个功能是我在一个项目中所用到的。
<br>
希望对大家有用！
</body>
