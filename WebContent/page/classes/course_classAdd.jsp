<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>新增课程信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/CourseSaveServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
function back(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/CourseListServlet?className=全部班级&teacherNumber=${teacherNumber}";
	oForm.submit();
}

</script>

<body  topmargin="10" leftmargin="10"  >
<form name="frmAction"  method="post" action="" >
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>新增课程信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap height="35">班级名称&nbsp;</td>
			<td >
				<select name="className">
					<option value="请选择班级" style="size:400px;">请选择班级</option>
					<c:forEach var="classes" items="${classes}">
					<option value="${classes.className}">${classes.className}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap  height="35">课程名&nbsp;</td>
			<td >
				<select name="courseName">
					<option value="" style="size:400px;">请选择课程</option>
					<c:forEach var="courses" items="${courses}">
					<option value="${courses.courseName}">${courses.courseName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap  height="35">星期几&nbsp;</td>
			<td >
				<select name="weekDay">
					<option value="请选择时间" style="size:400px;">请选择时间</option>
					<option value="星期一">星期一</option>
					<option value="星期二">星期二</option>
					<option value="星期三">星期三</option>
					<option value="星期四">星期四</option>
					<option value="星期五">星期五</option>
				</select>
			</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap  height="35">第几节&nbsp;</td>
			<td >
				<select name="dayNumber">
					<option value="哪一节" style="size:400px;">哪一节</option>
					<option value="第一节">第一节</option>
					<option value="第二节">第二节</option>
					<option value="第三节">第三节</option>
					<option value="第四节">第四节</option>
					<option value="第五节">第五节</option>
					<option value="第六节">第六节</option>
				</select>
			</td>
		</tr>
		<tr >
		   	<td  height=35 colspan="2" >
	   			<input class="input" type="button" value="确定" onclick="ensure()" >
				<input class="input" type="button" value="返回" onclick="back()" >
		   </td>
		</tr>        
	</table>
</form>
	<iframe name='hideFrame' style="display:none"></iframe>
</body>
</html>
