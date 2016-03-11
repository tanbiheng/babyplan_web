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
		var chk = document.getElementsByName("chkparent");
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = chkAll.checked;
		}
	}
	function add() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/page/user/parentAdd.jsp";
		oForm.submit();
	}
	function edit() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/page/user/parentEdit.jsp";
		oForm.submit();
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
	<form name="frmAction" method="post" action="add.jsp">
		<table width="99%" border="0" cellpadding="3" cellspacing="1"
			class="toolTable" align="center" height="60">
			<tr class="colom">
				<td style="padding: 30px;"><input type="text" name="name" value="请输入姓名" id="selectText">&nbsp;&nbsp;
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
				<td align='center'>宝宝名字</td>
				<td>姓名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>联系方式</td>
				<td>家庭住址</td>
			</tr>
			<c:forEach var="parent" items="${list}">
			<tr class='even' height="30">
				<td align='center'><input type='checkbox' name='chkparent' value='1'>
				<td align=center>${parent.student.studentName}</td>
				<td align=center>${parent.parentName}</td>
				<td align=center>${parent.parentNickName}</td>
				<td align=center>${parent.parentSex}</td>
				<td align=center>${parent.parentTelePhone}</td>
				<td align=center>${parent.address}</td>
			</tr>
			</c:forEach>

		</table>
		<table width='99%' class="tex004" align="center">
			<tr>
				<td align='left'><a href='/babyplan/ParentsListServlet?curPage=1'><span>首页</span></a>
					<c:if test="${page>1}">
						<a href='/babyplan/ParentsListServlet?curPage=${page-1}'><span>上一页</span></a>
					</c:if> <c:if test="${page<pages}">
						<a href='/babyplan/ParentsListServlet?curPage=${page+1}'><span>下一页</span></a>
					</c:if> <a href='/babyplan/ParentsListServlet?curPage=${pages}'>尾页</a></td>
				<td align='left'>共<c:out value="${count}"></c:out>项&nbsp;&nbsp;<c:out
						value="${pages}"></c:out>页
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

