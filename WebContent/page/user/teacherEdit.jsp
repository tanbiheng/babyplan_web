<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>修改教师信息</title>
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
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>修改教师信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>教师号&nbsp;</td>
			<td ><input type="text" name="classNumber" value="201210119133"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>密码&nbsp;</td>
			<td ><input type="text" name="teacherPwd" value="123456"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>权限&nbsp;</td>
			<td >
				<select name="power" style="width:50px;">
					<option value="0">0</option>
					<option value="1" selected>1</option>
				</select>
			</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>班级号&nbsp;</td>
			<td ><input type="text" name="classNumber" value="A03"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>姓名&nbsp;</td>
			<td ><input type="text" name="teacherName" value="白骨精"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>性别&nbsp;</td>
			<td ><input type="radio" checked value="男" name="teacherSex" >男
				<input type="radio" value="女" name="teacherSex" >女</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>头像&nbsp;</td>
			<td ><input type="file" name="teacherHeader" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>出生日期&nbsp;</td>
			<td >
				<input type="text" name="teacherBirthday"  value="2016-01-01" >
				<INPUT type='button' value='选择日期'
				onclick=javascript:document.all['teacherBirthday'].value=selectDate() ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>联系方式&nbsp;</td>
			<td ><input type="text" name="teacherTelePhone" value="1395662746"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>毕业院校&nbsp;</td>
			<td ><input type="text" name="graduateSchool" value="武汉大学"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>学历&nbsp;</td>
			<td ><input type="text" name="degree" value="本科"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>专业&nbsp;</td>
			<td ><input type="text" name="specialty" value="信息与计算科学"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>在校奖励&nbsp;</td>
			<td ><input type="text" name="reward" value="专业一等奖"></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>证书展示&nbsp;</td>
			<td ><input type="file" name="rewardShow" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>专业技能评价&nbsp;</td>
			<td ><textarea cols=30 rows=5 name="evaluate" >英语八级</textarea>
			</td>
		</tr>

		<tr >
		   	<td  height=35 colspan="2" >
	   			<input class="input" type="button" value="确定" onclick="ensure()" >
				<input class="input" type="button" value="返回" onclick="javascript:location.href='teacherList.jsp'" >
		   </td>
		</tr>        
	</table>
	</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>
