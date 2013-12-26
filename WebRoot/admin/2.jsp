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
  <script  src="js/jquery_171_min.js"></script>

   <style >
     table{ border:1px solid #999;     border-collapse:collapse;      margin:0 auto;     width:600px;     }
    table tr:hover{  background-color:#8dc8f2;    cursor:pointer;    }
     table tr td{     border:1px solid #999;      height:40px;     }
   </style>

   <table width="100%"  height="100%"  cellspacing="5">
   <tr>
     <td ><input type="radio" name="ra" id="" /></td><td >如何</td><td >实现</td><td >点击</td>
   </tr>
   <tr>
     <td ><input type="radio" name="ra" id="" /></td><td >表格中的</td><td >行（tr），</td><td >从而</td>
   </tr>
   <tr>
     <td ><input type="radio" name="ra" id="" /></td><td >选中</td><td >表格</td><td ><input type="text" vale="第一列中的"/></td>
   </tr>
   <tr>
    <td ><input type="radio" name="ra" id="" /></td><td >input</td><td >?</td><td >?</td>
   </tr>
   </table>
   
<p>如何实现点击表格中的行，即可选中input? 建议用jquery 中each解决。</p>


  <script>
    $(function(){
           $("table").find("tr").each(
             function(){
               $(this).click(
                 function(){$(this).find("input").attr("checked", "checked")}
                // function(){$(this).find("input").removeAttr("checked")}    这是用在toggle上的
               
               );            
             }
           );

     });
  </script>

希望对大家有用！
</body>
