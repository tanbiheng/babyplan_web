<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>家长信息列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function selectAll() {
		var chkAll = document.getElementsByName("chkAll")[0];
		var chk = document.getElementsByName("hwId");
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = chkAll.checked;
		}
	}
	function add() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/HomeworkAddServlet?teacherNumber=${teacherNumber}";
		oForm.submit();
	}

	function go(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/HomeworkListServlet?teacherNumber=${teacherNumber}&className=全部班级";
		frmAction.submit();
	}
	function dels(){
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/HomeworkDeleteServlet?teacherNumber=${teacherNumber}";
		oForm.submit();
	}
	function query(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/HomeworkListServlet?teacherNumber=${teacherNumber}&curPage=1";
		frmAction.submit();
	}
	
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#batch").click(function(){
		$("[name=show]").css("display","block");
		$("#chkoperation").css("display","block");
		$("#batch2").css("display","none");
	});
});
$(document).ready(function(){
	$("#more").click(function(){
		$("#show").toggle();
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
	<table width="90%" border="0" cellpadding="3" cellspacing="1" align="center" id="chkoperation"
		style="display:none;">
			<tr>
				<td>
				<input type="checkBox" name="chkAll" onClick="selectAll()" />全选&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="dels()" value="删除" style="width: 80px;">&nbsp;&nbsp;
				<input type="button" onclick="javascript:location.href='/babyplan/HomeworkListServlet?className=全部班级&teacherNumber=${teacherNumber}'" value="返回" style="width: 80px;">
				</td>
			</tr>
		</table>
		<table >
		<tr id="batch2"·>
			<td height="80" style="padding-left:60px;">
				<input type="button" value="添加" onclick="add()">&nbsp;&nbsp;
				<input type="button" id="batch" value="批量删除" style="width:80px;">&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<table width="90%"  align="center">
	<c:forEach var="homework" items="${homework}">
		<tr>
			<td width="5" name="show" style="display:none"><input type="checkbox"  name="hwId" value="${homework.hwId}"></td>
			<td>
				<ul>
					<li><a href="/babyplan/HomeworkEditServlet?teacherNumber=${teacherNumber}&hwId=${homework.hwId}">${homework.hwExplain}</a></li>
				</ul>
			</td>
		</tr>
	</c:forEach>
	</table>
<c:if test="${className==null||className=='全部班级'}">
<table width='90%' class="tex004" align="center">
	<tr>
		<td align='left'>
	    	<a  href='/babyplan/HomeworkListServlet?curPage=1&teacherNumber=${teacherNumber}' ><span>首页</span></a>
	    	<c:if test="${curPage>1}">
	    		<a href='/babyplan/HomeworkListServlet?curPage=${curPage-1}&teacherNumber=${teacherNumber}' ><span>上一页</span></a>
	    	</c:if>
	    	<c:if test="${curPage<totalPage}">
	    		<a href='/babyplan/HomeworkListServlet?curPage=${curPage+1}&teacherNumber=${teacherNumber}' ><span>下一页</span></a>
	    	</c:if>
	        <a  href='/babyplan/HomeworkListServlet?curPage=${totalPage}&teacherNumber=${teacherNumber}'>尾页</a>
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

