<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>修改学生信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan";
	oForm.submit();
}
</script>

<body  topmargin="10" leftmargin="10"  >
	<form name="frmAction"  method="post" action="" >
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>修改学生信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>学号&nbsp;</td>
			<td ><input type="text" name="studentNumber" value='201610119133'></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>姓名&nbsp;</td>
			<td ><input type="text" name="studentName" value='杨过'></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>照片&nbsp;</td>
			<td ><input type="file" name="studentPhoto" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>性别&nbsp;</td>
			<td ><input type="radio" checked value="男" name="parentSex" >男
				<input type="radio" value="女" name="parentSex" >女</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>班级名称&nbsp;</td>
			<td ><input type="text" name="className" value='小牛津班'></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>出生日期&nbsp;</td>
			<td >
				<input type="text" name="studentBirthday"  value="2016-01-01" >
				<INPUT type='button' value='选择日期'
				onclick=javascript:document.all['studentBirthday'].value=selectDate() ></td>
		</tr>
		<tr >
		   	<td  height=35 colspan="2" >
	   			<input class="input" type="button" value="确定" onclick="ensure()" >
				<input class="input" type="button" value="返回" onclick="javascript:location.href='studentList.jsp'" >
		   </td>
		</tr>        
	</table>
	</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>
