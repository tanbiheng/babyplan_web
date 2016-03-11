<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>新增家长信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	var studentNum = document.getElementsByName("studentNum")[0].value;
	var parentName = document.getElementsByName("parentName")[0].value;
	var parentTelePhone = document.getElementsByName("parentTelePhone")[0].value;
	var parentPwd = document.getElementsByName("parentPwd")[0].value;
	var parentNickName = document.getElementsByName("parentNickName")[0].value;
	var parentHeader = document.getElementsByName("parentHeader")[0].value;
	var backgroundPhoto = document.getElementsByName("backgroundPhoto")[0].value;
	var address = document.getElementsByName("address")[0].value;
	if(studentNum && parentHeader && parentName && parentNickName && parentPwd && parentTelePhone && backgroundPhoto && address)
		{
	oForm.action = "/babyplan/ParentAddServlet";
	oForm.submit();
		}
	else {
		alert("都不能为空啊，大哥！");
	}
}

function checkUnique() 
{
  //首先得到请求数据，以及要在span改变的数据
	var studentNumber = document.getElementsByName("studentNum")[0].value;
	var spanId = document.getElementById("spanId");
	if(studentNumber)
		{
	 //开启一个异步请求
	   var req;
	   if (window.XMLHttpRequest)
	    {
	        req = new XMLHttpRequest();
	    }
	    //捕获交互事件
	    req.onreadystatechange=function()
	    {
	    	
	        if ( req.readyState==4 && req.status==200) 
	        {
	            //捕获到了值，实际上是person这个值
	            var data = req.responseText;
	            spanId.innerHTML = data;
	          //  alert(data);      
	        }	
	    }
	    //使用一般情况下都是get方式提交的
	    req.open("GET","/babyplan/CheckBabyUniqueServlet?num="+studentNumber,true);
	    req.send(null);
		}else
			 {
			alert("想要校验，你至少要输入值啊，大哥！！！");
		}
}

function checkTelephone() 
{
  //首先得到请求数据，以及要在span改变的数据
	var studentNumber = document.getElementsByName("parentTelePhone")[0].value;
	var spanId = document.getElementById("spanId2");
	if(studentNumber)
		{
	 //开启一个异步请求
	   var req;
	   if (window.XMLHttpRequest)
	    {
	        req = new XMLHttpRequest();
	    }
	    //捕获交互事件
	    req.onreadystatechange=function()
	    {
	    	
	        if ( req.readyState==4 && req.status==200) 
	        {
	            //捕获到了值，实际上是person这个值
	            var data = req.responseText;
	            spanId.innerHTML = data;
	          //  alert(data);      
	        }	
	    }
	    //使用一般情况下都是get方式提交的
	    req.open("GET","/babyplan/CheckTelephoneServlet?num="+studentNumber,true);
	    req.send(null);
		}else {
			alert("值不能为空！！");
		}
}
</script>

<body  topmargin="10" leftmargin="10"  >
<form name="frmAction"  method="post" action="" enctype="multipart/form-data">
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>新增家长信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>宝宝学号&nbsp;</td>
			<td ><input type="text" name="studentNum"><input type="button" value="检测是否存在或者已经被使用" onclick="checkUnique()"/><span id="spanId"></span></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>姓名&nbsp;</td>
			<td ><input type="text" name="parentName" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>联系方式&nbsp;</td>
			<td ><input type="text" name="parentTelePhone" ><input type="button" value="检测手机号是否存在" onclick="checkTelephone()"/><span id="spanId2"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>密码&nbsp;</td>
			<td ><input type="text" name="parentPwd" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>性别&nbsp;</td>
			<td ><input type="radio" checked value="男" name="parentSex" >男
				<input type="radio" value="女" name="parentSex" >女</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>昵称&nbsp;</td>
			<td ><input type="text" name="parentNickName" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>头像&nbsp;</td>
			<td ><input type="file" name="parentHeader" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>背景图&nbsp;</td>
			<td ><input type="file" name="backgroundPhoto" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>家庭住址&nbsp;</td>
			<td ><input type="text" name="address" ></td>
		</tr>
		<tr >
		   	<td  height=35 colspan="2" >
	   			<input class="input" type="button" value="确定" onclick="ensure()" >
				<input class="input" type="button" value="返回" onclick="location.href='/babyplan/ParentsListServlet'" >
		   </td>
		</tr>        
	</table>
</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>
