<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传动漫</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure()
{
	var oForm=document.getElementsByName("frmAction")[0];
	var fileValue =document.getElementsByName("loadFile")[0].value;
	var name =document.getElementsByName("whichStation")[0].value;
	var brief =document.getElementsByName("ssBrief")[0].value;
	if(fileValue && name && brief)
		{
	oForm.action="/babyplan/AddStationOfCartoonServlet";
	oForm.submit();
		}
	else {
		alert("集数，简介，文件均不能不为空!!");
	}
}


</script>
<body>
<body  topmargin="10" leftmargin="10"  >
<form name="frmAction"  method="post" action="/" enctype="multipart/form-data">
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>上传动漫集数</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap height="35">第几集&nbsp;</td>
			<td ><input type="text" name="whichStation" value=""> 
			</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap>简介&nbsp;</td>
			<td ><textarea cols=30 rows=5  name="ssBrief" ></textarea>
			</td>
		</tr>
		<tr height="50"  bgcolor='#ffffff'>
			<td align='right'>上传到</td>
			<td><select name="loadLoaction" >
			    <c:forEach var="cartoon" items="${list}">
					<option value="${cartoon.cartoonId}" selected>${cartoon.cartoonName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr height="35"  bgcolor='#ffffff'>
			<td align='right'>选择文件</td>
			<td><input type="file" name="loadFile" value=""></td>
		</tr>
		<tr >
		   	<td  height=35 colspan="2" >
				<input class="input" type="button" value="确定" onclick="ensure()"  style="width:80px;">
				<input type="button" onclick="location.href='/babyplan/CarToonListServlet'" value="返回" style="width:80px;">
		   </td>
		</tr>        
	</table>
</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>