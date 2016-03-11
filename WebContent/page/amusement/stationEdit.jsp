<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传动漫</title>
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan";
	oForm.submit();
}

</script>
<body>
<form name="frmAction"  method="post" action="" >
<div style="width:300px;margin-top: 200px;margin-left: 400px;">
	<table align="center">
		<tr height="50" align="left">
			<td>名称：&nbsp;<input type="text" name="whichStation">
			</td>
		</tr>
		<tr align="left">
			<td>集数：&nbsp;<input type="text" name="cartoonName"></td>
		</tr>
		<tr>
		<td height="40">
		<input type="button" onclick="ensure()" value="确定" style="width:80px;">&nbsp;&nbsp;
		<input type="button" onclick="javascript:location.href='stationList.jsp'" value="返回" style="width:80px;">
		</td>
    </tr>
	</table>
</div>
</form>
</body>
</html>