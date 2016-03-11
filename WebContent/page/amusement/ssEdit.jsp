<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>编辑故事儿歌信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	var ssName = document.getElementsByName("albumName")[0].value;
	var ssDes = document.getElementsByName("albumDescribe")[0].value;
	//alert(ssName);
	if(ssName && ssDes)
		{
	oForm.action = "/babyplan/StorySongEditEnsureServlet";
	oForm.submit();
		}else {
			alert("名字和简介均不能空！！");
		}
}
</script>

<body>
<form name="frmAction"  method="post" action="" >
	<table width="60%" align="center">
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" style="text-indent: 1em;"><b>编辑信息</b></td>
			</tr>
			<tr>
				<td width="150" height="60" align="right">名称：</td>
				<td><input type="text" name="albumName" title="default" value="${storysong.ssName}" style="width:420px;"></td>
			</tr>
			<tr>
				<td width="150" align="right" >简介：</td>
				<td><textarea name="albumDescribe" cols="50" title="default" rows="5">${storysong.ssBrief}</textarea></td>
			</tr>
			<tr>
			  <td><input type="hidden" name="id" value="${storysong.ssId}"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" align="right" style="text-indent: 1em;">
					<input type="button" name="test" value="确定" style="width:80px;" onclick="ensure()"/>&nbsp;&nbsp;
					<input type="button" name="test" value="返回" onclick="javascript:location.href='/babyplan/StorySongListServlet'" style="width:80px;"/>
				</td>
			</tr>
	</table>
</form>
</body>
</html>
