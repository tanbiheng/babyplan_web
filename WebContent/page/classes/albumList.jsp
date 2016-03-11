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
<script type="text/javascript">
function selectAll(){
	var chkAll=document.getElementsByName("chkAll")[0];
	var chk=document.getElementsByName("chkalbum");
	for(var i=0;i<chk.length;i++){
		chk[i].checked=chkAll.checked;
	}
}
function add(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/AlbumAddServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
function show(albumId){
	//alert(albumId)
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/PhotoListServlet?teacherNumber=${teacherNumber}&albumId="+albumId;
	oForm.submit(); 
}
function upload(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/PhotoAddServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
function go(){
	var frmAction = document.getElementsByName("frmAction")[0];
	frmAction.action = "/babyplan/AlbumListServlet?teacherNumber=${teacherNumber}&className=全部班级";
	frmAction.submit();
}

function query(){
	var frmAction = document.getElementsByName("frmAction")[0];
	frmAction.action = "/babyplan/AlbumListServlet?teacherNumber=${teacherNumber}&curPage=1";
	frmAction.submit();
}
</script>

<body style="background-color:#F8F8F8">
<form name="frmAction"  method="post" action="" >
	<table width="96%" align="center">
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
		<tr>
			<td height="80" style="padding-left:30px;">
				<input type="button" value="上传照片" onclick="upload()">
				<input type="button" onclick="add()" value="创建相册" style="width:80px;">
			</td>
		</tr>
		<tr>
			<td>
				<c:forEach var="albums" items="${albums}">
				<div id="photo" onclick="show(${albums.albumId})" style="width:160px;height:190px;background-color:#FFFFFF;
				margin-left:20px;float: left;">
					<div style="width:140px;height:140px;
					background-image: url(${albums.coverPaper});background-size:cover;margin:10px;">
					<div style="font-size: 30px;color: white;padding-top: 105px;float:right">${count}</div>
					</div>
					<div style="width:140px;margin: 10px;"> 
						${albums.albumName}
					</div>
				</div>
				</c:forEach>
			</td>
		</tr>
	</table>
<c:if test="${className==null||className=='全部班级'}">
<table width='96%' class="tex004" align="center">
	<tr>
		<td align='left'>
	    	<a  href='/babyplan/AlbumListServlet?curPage=1&teacherNumber=${teacherNumber}' ><span>首页</span></a>
	    	<c:if test="${curPage>1}">
	    		<a href='/babyplan/AlbumListServlet?curPage=${curPage-1}&teacherNumber=${teacherNumber}' ><span>上一页</span></a>
	    	</c:if>
	    	<c:if test="${curPage<totalPage}">
	    		<a href='/babyplan/AlbumListServlet?curPage=${curPage+1}&teacherNumber=${teacherNumber}' ><span>下一页</span></a>
	    	</c:if>
	        <a  href='/babyplan/AlbumListServlet?curPage=${totalPage}&teacherNumber=${teacherNumber}'>尾页</a>
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

