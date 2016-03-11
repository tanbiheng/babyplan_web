<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>班级视频列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script type="text/javascript" src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function selectAll() {
		var chkAll = document.getElementsByName("chkAll")[0];
		var chk = document.getElementsByName("chkprivate");
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = chkAll.checked;
		}
	}
	function upload() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/PrivateVideoAddServlet?teacherNumber=${teacherNumber}";
		oForm.submit();
	}
	function go(){
		var frmAction = document.getElementsByName("frmAction")[0];
		frmAction.action = "/babyplan/PrivateVideoListServlet?teacherNumber=${teacherNumber}";
		frmAction.submit();
	}
	function edits() {
		var oForm = document.getElementsByName("frmAction")[0];
		oForm.action = "/babyplan/page/video/privateEdit.jsp";
		oForm.submit();
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#batch").click(function() {
			$(":input[name=chkprivate]").css("display", "block");
			$("#chkoperation").css("display", "block");
			$("#batch2").css("display", "none");
			$("[name=edit]").css("display", "none");
		});
	});
</script>
<body style="background-color: #F8F8F8">
	<form name="frmAction" method="post" action="">
		<table width="95%" border="0" cellpadding="3" cellspacing="1"
			align="center" id="chkoperation" style="display: none;">
			<tr>
				<td><input type="checkBox" name="chkAll" onClick="selectAll()" />全选&nbsp;
					<input type="button" onclick="dels()" value="删除"
					style="width: 80px;"> <input type="button"
					onclick="javascript:location.href='privateList.jsp'" value="返回"
					style="width: 80px;"></td>
			</tr>
		</table>
		<table>
			<tr id="batch2">
				<td height="80" style="padding: 20px;"><input type="button"
					value="上传视频" onclick="upload()">&nbsp; <input type="button"
					id="batch" value="批量管理" style="width: 80px;"></td>
			</tr>

			<tr>
				<td><c:forEach var="privateVideos" items="${privateVideos}">
						<div id="photo"
							style="width: 300px; height: 400px; margin-left: 30px; float: left;">
							<div style="width: 300px; height: 300px;">
								<input type="checkbox" name="privateId"
									value="${privateVideos.privateId}" style="display: none"
									id="chk">
								<video src="${privateVideos.privateVideoURL}" width="300px"
									height="300px" controls="controls"
									poster="${privateVideos.privateThumbnail}"></video>
							</div>
							<div style="width: 150px; margin-top: 10px;">
								${privateVideos.privateAddress}
								<div style="float: right">
									<a href="/babyplan/page/video/privateEdit.jsp">编辑</a>
								</div>
							</div>
							<div name="edit"
								style="width: 150px; float: left; margin-top: 10px; font-size: 14px;">
								${privateVideos.privateTime}</div>
						</div>
					</c:forEach></td>
			</tr>
		</table>
		<table width='99%' class="tex004" align="center">
			<tr>
				<td align='left'><a
					href='/babyplan/PrivateVideoListServlet?curPage=1&teacherNumber=${teacherNumber}'><span>首页</span></a>
					<c:if test="${curPage>1}">
						<a
							href='/babyplan/PrivateVideoListServlet?curPage=${curPage-1}&teacherNumber=${teacherNumber}'><span>上一页</span></a>
					</c:if> <c:if test="${curPage<totalPage}">
						<a
							href='/babyplan/PrivateVideoListServlet?curPage=${curPage+1}&teacherNumber=${teacherNumber}'><span>下一页</span></a>
					</c:if> <a
					href='/babyplan/PrivateVideoListServlet?curPage=${totalPage}&teacherNumber=${teacherNumber}'>尾页</a>
				</td>
				<td>到第&nbsp;<input type="text" name="goPage"
					style="width: 30px;">&nbsp;页&nbsp; <input type="button"
					name="pagego" value="go" onclick="go()"></td>
				<td align='left'>共<c:out value="${pageSize}"></c:out>项&nbsp;&nbsp;<c:out
						value="${curPage}/${totalPage}"></c:out>页
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

