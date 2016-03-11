<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>添加动漫</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function ensure()
	{
	var oForm=document.getElementsByName("frmAction")[0];
	var name=document.getElementsByName("albumName")[0].value;
	var text=document.getElementsByName("albumDescribe")[0].value;
	var file=document.getElementsByName("pic")[0].value;
	if(name && text && file)
		{
		
	oForm.action="/babyplan/CartoonAddServlet";
	oForm.submit();
		}
	else 
		{
		alert("动漫名称，简介，以及上传的缩略图均不能为空！！");
	}
	
} 
	function returnList()
	{
		var oForm=document.getElementsByName("frmAction")[0];
		oForm.action="/babyplan/CarToonListServlet";
		oForm.submit();
	}
</script>

<body>
<form name="frmAction"  method="post" action="" enctype="multipart/form-data">
	<table width="60%" align="center">
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" style="text-indent: 1em;"><b>添加动漫名称</b></td>
			</tr>
			<tr>
				<td width="150" height="60" align="right">动漫名称：</td>
				<td><input type="text" name="albumName" title="default" value="名称" style="width:420px;"></td>
			</tr>
			<tr>
				<td width="150" align="right" >动漫简介：</td>
				<td><textarea name="albumDescribe" cols="50" title="default" rows="5">简介</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="file" name="pic"></td>
			</tr>
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" align="right" style="text-indent: 1em;">
					<input type="button" name="test" value="确定" style="width:80px;" onclick="ensure()"/>
					<input type="button" name="test" value="返回" onclick="returnList()" style="width:80px;"/>
				</td>
			</tr>
	</table>
</form>
</body>
</html>
