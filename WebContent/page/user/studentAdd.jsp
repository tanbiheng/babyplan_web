<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>新增学生信息</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	var studentNumber = document.getElementsByName("studentNumber")[0].value;
	var studentName = document.getElementsByName("studentName")[0].value;
	var studentPhoto = document.getElementsByName("studentPhoto")[0].value;
	var studentBirthday = document.getElementsByName("studentBirthday")[0].value;
	if(studentBirthday && studentName && studentNumber && studentPhoto)
		{
	oForm.action = "/babyplan/StudentAddServlet";
	oForm.submit();
		}
	else
		 {
		alert("表中数据均不能空，请选择数据!");
	}
}
//使用ajax异步请求判断数据是否唯一
function checkUnique() 
{
  //首先得到请求数据，以及要在span改变的数据
	var studentNumber = document.getElementsByName("studentNumber")[0].value;
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
	    req.open("GET","/babyplan/AjaxUniqueNumberServlet?num="+studentNumber,true);
	    req.send(null);
		}else {
			alert("请输入值！！");
		}
}

</script>

<body  topmargin="10" leftmargin="10"  >
<form name="frmAction"  method="post" action="" enctype="multipart/form-data" >
	<table width="95%" border="0" cellpadding="3" cellspacing="1" class="toolBar" align="center">
      		<tr align=left class="colom">
			<td colspan="2" align="center" height="35" style="font-size:16px;"><b>新增学生信息</b></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>学号&nbsp;</td>
			<td ><input type="text" name="studentNumber" ><input type="button" value="检测是否唯一" onclick="checkUnique()"/><span id="spanId"></span></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>姓名&nbsp;</td>
			<td ><input type="text" name="studentName" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>照片&nbsp;</td>
			<td ><input type="file" name="studentPhoto" ></td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>性别&nbsp;</td>
			<td ><input type="radio" checked value="男" name="studentSex" >男
				<input type="radio" value="女" name="parentSex" >女</td>
		</tr>
		<tr bgcolor='#ffffff'>
			<td width='100' align='right' nowrap class='even'>班级名称&nbsp;</td>
			<td> <select name="className">
			<c:forEach var="cla" items="${list}">
			   <option value="${cla.classNumber}">${cla.className}</option>
			   </c:forEach>
			</select></td>
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
