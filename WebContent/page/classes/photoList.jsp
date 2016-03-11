<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>班级相册列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script type="text/javascript" src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function selectAll(){
	var chkAll=document.getElementsByName("chkAll")[0];
	var chk=document.getElementsByName("chkphoto");
	for(var i=0;i<chk.length;i++){
		chk[i].checked=chkAll.checked;
	}
}
function upload(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/PhotoAddServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
function dels(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/PhotoDeleteServlet?albumId=${albumId}&teacherNumber=${teacherNumber}";
	oForm.submit();
}
/* function edit() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/page/classes/albumEdit.jsp";
	oForm.submit();
}
function edits() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/page/classes/photoEdit.jsp";
	oForm.submit();
} */
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#batch").click(function(){
		$(":input[name=chkphoto]").css("display","block");
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
<body style="background-color:#F8F8F8">
<form name="frmAction"  method="post" action="" >
<table width="95%" border="0" cellpadding="3" cellspacing="1" align="center" id="chkoperation"
		style="display:none;">
			<tr>
				<td>
				<input type="checkBox" name="chkAll" onClick="selectAll()" />全选&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <input type="button" onclick="edits()" value="修改" style="width: 80px;"> &nbsp;&nbsp; -->
				<input type="button" onclick="dels()" value="删除" style="width: 80px;">&nbsp;&nbsp;
				<input type="button" onclick="javascript:location.href='/babyplan/PhotoListServlet?albumId=${albumId}&teacherNumber=${teacherNumber}'" value="返回" style="width: 80px;">
				</td>
			</tr>
		</table>
	<table >
		<tr id="batch2"·>
			<td height="80" style="padding:20px;">
				<input type="button" value="上传照片" onclick="upload()">&nbsp;
				<input type="button" id="batch" value="批量管理" style="width:80px;">&nbsp;
				<input type="button" onclick="javascript:location.href='/babyplan/AlbumListServlet?className=全部班级&teacherNumber=${teacherNumber}'"  value="返回" style="width:80px;">&nbsp;
				<input type="button" id="more" value="更多" style="width:80px;">&nbsp;
				<div id="show" style="background-color:#ffffee;width:120px;height:70px;display:none;
				position: absolute;left:310px;top:65px;">
					<ul style="list-style: none;">
						<li style="line-height:25px;"><p><a href="/babyplan/AlbumEditServlet?albumId=${albumId}&teacherNumber=${teacherNumber}">编辑相册</a></p></li>
						<li style="line-height:25px;"><p><a href="#">删除相册</a></p></li>
					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<c:forEach var="photos" items="${photos}">
				<div id="photo" style="width:140px;height:170px;margin:20px;float: left;">
					<div style="width:140px;height:140px;
					background-image: url(${photos.photoURL});background-size:cover;">
						<input type="checkbox" name="chkphoto" style="display:none" id="chk" value="${photos.photoId}">
					</div>
					<%-- <div style="width:140px;margin: 10px;"> 
						${photos.photoName}
					</div> --%>
					<div style="width:140px;height:30px;">
						<div style="width:100px;margin-top: 10px;float: left;"> 
							${photos.photoName}
						</div>
						<div name="edit" style="width:40px;margin-top: 10px;float: left;"> 
							<a href="/babyplan/PhotoEditServlet?albumId=${albumId}&photoId=${photos.photoId}&teacherNumber=${teacherNumber}">编辑</a>
						</div>
					</div>
				</div>
			</c:forEach>
			</td>
		</tr>
	</table>
	<%-- <table width='95%' class="tex004" align="center">
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
</form>
</body>
</html>

