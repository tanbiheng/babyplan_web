<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>班级课程列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function selectAll() {
		var chkAll = document.getElementsByName("chkAll")[0];
		var chk = document.getElementsByName("chkCourseClass");
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = chkAll.checked;
		}
	}
	function add() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/CourseAddServlet?teacherNumber=${teacherNumber}";
		oForm.submit();
	}
	function edit() {
		var oForm = document.getElementsByName("frmAction")[0];
		var chkCourseClass =document.getElementsByName("chkCourseClass");
		var count = 0;
		for (var i = 0; i < chkCourseClass.length; i++) {
			if(chkCourseClass[i].checked){
				count++;
			}
		}
		if(count){
			oForm.action = "/babyplan/CourseEditServlet?teacherNumber=${teacherNumber}";
			oForm.submit();
		}else{
			alert("请选择要修改的课程");
		}
	}
	function go(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/CourseListServlet?teacherNumber=${teacherNumber}&className=全部班级";
		frmAction.submit();
	} 
	function del(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/CourseDeleteServlet?teacherNumber=${teacherNumber}&className=全部班级";
		frmAction.submit();
	}
	function query(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/CourseListServlet?teacherNumber=${teacherNumber}";
		frmAction.submit();
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#selectText").focus(function(){
		var v=$(this).val();
		if(v==$(this).prop("defaultValue")){
			$(this).val("");
		}
	}).blur(function(){
		//当前值为空，则这个域的值设为默认值
		var v=$(this).val();
		if(v==""){
			$(this).val($(this).prop("defaultValue"));
		}
	});
});
</script>
<body topmargin="10" leftmargin="10">
	<form name="frmAction" method="post" action="">
		<table width="99%" border="0" cellpadding="3" cellspacing="1"
			class="toolTable" align="center" height="60">
			<tr class="colom">
				<td style="padding: 30px;">
				<select name="className">
					<option value="请选择班级" style= "size:400px;" selected="selected">请选择班级&nbsp;&nbsp;&nbsp;</option>
					<option value="全部班级" style= "size:400px;">全部班级&nbsp;&nbsp;&nbsp;</option>
					<c:forEach var="classes" items="${classes}">
					<option value="${classes.className}">${classes.className}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;
				
					<input type="button" onclick="query()" value="查询" style="width: 80px;"></td>
			</tr>
		</table>
		<p></p>
		<table width="99%" border="0" cellpadding="3" cellspacing="1"
			align="center">
			<tr>
				<td>
				<input type="button" onclick="add()" value="添加" style="width: 80px;">
				<input type="button" onclick="edit()"value="修改" style="width: 80px;"> 
				<input type="button" onclick="del()" value="删除" style="width: 80px;">
				</td>
			</tr>
		</table>
		<table width="99%" border="0" cellpadding="3" cellspacing="1"
			class="toolTable" align="center">
			<!--此处为列标题-->
			<tr align="center" class="colom" height="30">
				<td><input type="checkBox" name="chkAll" onClick="selectAll()" /></td>
				<td>班级名称</td>
				<td>星期几</td>
				<td>第几节</td>
				<td>课程名</td>
			</tr>
			<c:forEach var="courseClass" items="${course_Class}">
			<tr class='even' height="30">
				<td align='center'><input type='checkbox' name='chkCourseClass' value="${courseClass.courseClassId}">
				<td align=center>${courseClass.classes.className}</td>
				<td align=center>${courseClass.weekDay}</td>
				<td align=center>${courseClass.dayNumber}</td>
				<td align=center>${courseClass.course.courseName}</td>
			</tr>
			</c:forEach>
		</table>
<%-- 		<table width='99%' class="tex004" align="center">
			<tr>
				<td align='left'><a href='/studentweb/ListStudent?curPage=1'><span>首页</span></a>
					<c:if test="${page>1}">
						<a href='/studentweb/ListStudent?curPage=${page-1}'><span>上一页</span></a>
					</c:if> <c:if test="${page<pages}">
						<a href='/studentweb/ListStudent?curPage=${page+1}'><span>下一页</span></a>
					</c:if> <a href='/studentweb/ListStudent?curPage=${pages}'>尾页</a></td>
				<td>到第&nbsp;<input type="text" name="curPage"
					style="width: 30px;">&nbsp;页&nbsp;<input type="button" name="goPage" value="go">
				</td>
				<td align='left'>共<c:out value="${count}"></c:out>项&nbsp;&nbsp;<c:out
						value="${pages}"></c:out>页
				</td>
			</tr>
		</table> --%>
<c:if test="${className==null||className=='全部班级'}">
<table width='99%' class="tex004" align="center">
	<tr>
		<td align='left'>
	    	<a  href='/babyplan/CourseListServlet?curPage=1&teacherNumber=${teacherNumber}' ><span>首页</span></a>
	    	<c:if test="${curPage>1}">
	    		<a href='/babyplan/CourseListServlet?curPage=${curPage-1}&teacherNumber=${teacherNumber}' ><span>上一页</span></a>
	    	</c:if>
	    	<c:if test="${curPage<totalPage}">
	    		<a href='/babyplan/CourseListServlet?curPage=${curPage+1}&teacherNumber=${teacherNumber}' ><span>下一页</span></a>
	    	</c:if>
	        <a  href='/babyplan/CourseListServlet?curPage=${totalPage}&teacherNumber=${teacherNumber}'>尾页</a>
		</td>
		<td>到第&nbsp;<input type="text" name="goPage" style="width:30px;">&nbsp;页&nbsp;
		<input type="button" name="pagego" value="go" onclick="go()"></td>
    <td  align='left' >共<c:out value="${pageSize}"></c:out>项&nbsp;&nbsp;<c:out value="${curPage}/${totalPage}"></c:out>页</td>
   </tr>
</table>
</c:if>
	</form>
</body>
</html>

