<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>班级通知列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function selectAll(){
	var chkAll=document.getElementsByName("chkAll")[0];
	var chk=document.getElementsByName("chkNotice");
	for(var i=0;i<chk.length;i++){
		chk[i].checked=chkAll.checked;
	}
}
function add(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/NoticeAddServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
function query(){
	var frmAction = document.getElementsByName("frmAction")[0];
	frmAction.action = "/babyplan/NoticeListServlet?teacherNumber=${teacherNumber}&curPage=1";
	frmAction.submit();
}
function del(){
	var frmAction = document.getElementsByName("frmAction")[0];
	frmAction.action = "/babyplan/NoticeDeleteServlet?teacherNumber=${teacherNumber}";
	frmAction.submit();
}
function go(){
	var frmAction = document.getElementsByName("frmAction")[0];
	frmAction.action = "/babyplan/NoticeListServlet?teacherNumber=${teacherNumber}&className=全部班级";
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
<body topmargin="10" leftmargin="10" >
<form name="frmAction"  method="post" action="" >
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
<table width="99%" border="0" cellpadding="3" cellspacing="1"  align="center" style="padding-top:20px;">
   	<tr>
		<td align="right">
		<input type="button" onclick="add()" value="添加" style="width:80px;">
		<input type="button" onclick="del()" value="删除" style="width:80px;">
		</td>
    </tr>
</table>
<table width="99%" border="0" cellpadding="3" cellspacing="1" class="toolTable" align="center" >
  	<!--此处为列标题-->
   	<tr align="center" class="colom" height="30">
		<td ><input type="checkBox" name="chkAll" onClick="selectAll()"/></td>
		<td align='center'>班级名称</td>
		<td >通知内容</td>
		<td>通知时间</td>
    </tr>
     	<c:forEach items="${notice}" var="notice" >
     	<tr class='even' height="30">
			<td align='center'><input type='checkbox'  name='chkNotice' value="${notice.noticeId}">
			<td align=center>${notice.classes.className}</td>
			<td align=center width="45%"><a href="/babyplan/NoticeDetailServlet?noticeId=${notice.noticeId}&teacherNumber=${teacherNumber}">${notice.noticeText}</a></td>
			<td align=center>${notice.noticeTime}</td>
			</tr>  
		</c:forEach>
</table>
<c:if test="${className==null||className=='全部班级'}">
<table width='99%' class="tex004" align="center">
	<tr>
		<td align='left'>
	    	<a  href='/babyplan/NoticeListServlet?curPage=1&teacherNumber=${teacherNumber}' ><span>首页</span></a>
	    	<c:if test="${curPage>1}">
	    		<a href='/babyplan/NoticeListServlet?curPage=${curPage-1}&teacherNumber=${teacherNumber}' ><span>上一页</span></a>
	    	</c:if>
	    	<c:if test="${curPage<totalPage}">
	    		<a href='/babyplan/NoticeListServlet?curPage=${curPage+1}&teacherNumber=${teacherNumber}' ><span>下一页</span></a>
	    	</c:if>
	        <a  href='/babyplan/NoticeListServlet?curPage=${totalPage}&teacherNumber=${teacherNumber}'>尾页</a>
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

