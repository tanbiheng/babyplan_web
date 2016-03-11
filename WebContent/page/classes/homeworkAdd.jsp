<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>作业详细信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/HomeworkSaveServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
</script>

<body  topmargin="10" leftmargin="10"  >
	<form name="frmAction"  method="post" action="" enctype="multipart/form-data">
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>作业详细信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>标题&nbsp;</td>
			<td ><input type="text" name="hwTitle" value=""></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>类型&nbsp;</td>
			<td ><select name="hwType">
					<option value="1">家长协助</option>
					<option value="2">全家互动</option>
					<option value="3">独立完成</option>
				</select></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>说明&nbsp;</td>
			<td ><textarea cols=30 rows=5 name="hwExplain" ></textarea></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>内容&nbsp;</td>
			<td ><input type="file" name="hwPicture" value=""></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>截止时间&nbsp;</td>
			<td >
				<input type="text" name="hwEndTime"  value="2016-01-01" >
				<INPUT type='button' value='选择日期'
				onclick=javascript:document.all['hwEndTime'].value=selectDate() ></td>
		</tr>
		<tr >
		   	<td  height=35 colspan="2" >
	   			<input class="input" type="button" value="确定" onclick="ensure()" >
				<input class="input" type="button" value="返回" onclick="javascript:location.href='/babyplan/HomeworkListServlet?className=全部班级&teacherNumber=${teacherNumber}'" >
		   </td>
		</tr>        
	</table>
	</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>
