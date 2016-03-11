<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>卡通片列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function selectAll(){
	var chkAll=document.getElementsByName("chkAll")[0];
	var chk=document.getElementsByName("chkcartoon");
	for(var i=0;i<chk.length;i++){
		chk[i].checked=chkAll.checked;
	}
}
function add(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/page/amusement/cartoonAdd.jsp";
	oForm.submit();
}
//点击进入相应动漫的集数
/* function show(){
	var oForm=document.getElementsByName("frmAction")[0];
	//得到一个数组
	var carId=document.getElementsByName("carId");
	for()
		{}
	alert(carId);
	//oForm.action="/babyplan/page/amusement/stationList.jsp";
	//oForm.submit();
} */
/* function upload(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/page/amusement/stationAdd.jsp";
	oForm.submit();
} */
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
<body style="background-color:#F8F8F8">
<form name="frmAction"  method="post" action="" >
	<table width="95%" border="0" cellpadding="3" cellspacing="1"
			class="toolTable" align="center" height="60">
			<tr class="colom">
				<td style="padding: 30px;"><input type="text" name="name" value="请输入名称" id="selectText">&nbsp;&nbsp;
					<input type="button" onclick="query()" value="查询" style="width: 80px;"></td>
			</tr>
	</table>
	<table>
		<tr>
			<td height="80" style="padding-left:25px;">
				<input type="button" value="上传集数" onclick="location.href='/babyplan/AddStationServlet'">&nbsp;
				<input type="button" onclick="add()" value="添加动漫" style="width:80px;">	
			</td>
		</tr>
		<tr>
		<c:forEach var="cs" items="${list}">	
			<td>
				<div id="photo" onclick="show()" style="width:160px;height:190px;background-color:#FFFFFF;
				margin-left:20px;float: left;" >
					<div style="width:140px;height:140px;
					background-image: url(${cs.cThumbnail});background-size:cover;margin:10px;" onclick="location.href='/babyplan/StationListServlet?id=${cs.cartoonId}'">
						<div style="font-size: 30px;color: white;padding-top: 105px;float:right">250</div>
						
					</div>
					<div style="width:140px;margin: 10px;"> 
						${cs.cartoonName}
						
					</div>
				</div>
			</td>
			<td>
			
			</td>
			</c:forEach>
		</tr>
		
	</table>
	<table width='95%' class="tex004" align="center">
			<tr>
				<td align='left'><a href='/babyplan/CarToonListServlet'><span>首页</span></a>
					<c:if test="${page>1}">
						<a href='/babyplan/CarToonListServlet?curPage=${page-1}'><span>上一页</span></a>
					</c:if> <c:if test="${page<pages}">
						<a href='/babyplan/CarToonListServlet?curPage=${page+1}'><span>下一页</span></a>
					</c:if> <a href='/babyplan/CarToonListServlet?curPage=${pages}'>尾页</a></td>
				<!-- <td>到第&nbsp;<input type="text" name="curPage"
					style="width: 30px;">&nbsp;页&nbsp;<input type="button" name="goPage" value="go">
				</td> -->
				<td align='left'>共<c:out value="${count}"></c:out>项&nbsp;&nbsp;<c:out
						value="${pages}"></c:out>页
				</td>
			</tr>
		</table>
</form>
</body>
</html>

