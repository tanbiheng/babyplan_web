<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>故事儿歌列表</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>
<script type="text/javascript" src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function selectAll(){
	var chkAll=document.getElementsByName("chkAll")[0];
	var chk=document.getElementsByName("chkss");
	for(var i=0;i<chk.length;i++){
		chk[i].checked=chkAll.checked;
	}
}
function upload(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/page/amusement/ssAdd.jsp";
	oForm.submit();
}
function edit() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/page/amusement/ssEdit.jsp";
	oForm.submit();
}
//删除选中的文件
function dels(){
     
	var oForm = document.getElementsByName("frmAction")[0];
	//alert(oForm);
	oForm.action = "/babyplan/StorySongDeleteServlet";
	oForm.submit();
	
}
//模糊查询
function query()
{
	//不能为空
	var oForm = document.getElementsByName("frmAction")[0];
	var textValue = document.getElementsByName("name")[0].value;
	//alert(textValue);
	if (textValue) {
	oForm.action = "/babyplan/StorySongSerachServlet?name="+textValue;
	oForm.submit();
		
	} else {
		//为空，什么也不做
      alert("请输入关键字！！");
	}


	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#batch").click(function(){
		$(":input[name=chkss]").css("display","block");
		$("#chkoperation").css("display","block");
		$("#batch2").css("display","none");
		$("#batch3").css("display","none");
		$("[name=edit]").css("display","none");
	});
});
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
<table width="95%" border="0" cellpadding="3" cellspacing="1" align="center" id="chkoperation"
		style="display:none;">
			<tr>
				<td>
				<input type="checkBox" name="chkAll" onClick="selectAll()" />全选&nbsp;
				<input type="button" onclick="dels()" value="删除" style="width: 80px;">
				<input type="button" onclick="javascript:location.href='/babyplan/StorySongListServlet'" value="返回" style="width: 80px;">
				</td>
			</tr>
		</table>
		<table width="95%" border="0" cellpadding="3" cellspacing="1" id="batch3"
			class="toolTable" align="center" height="60">
			<tr class="colom">
				<td style="padding: 30px;"><input type="text" name="name" value="请输入名称" id="selectText">&nbsp;&nbsp;
					<input type="button" onclick="query()" value="查询" style="width: 80px;"></td>
			</tr>
	</table>
	<table >
		<tr id="batch2">
			<td height="80" style="padding-left:25px;">
				<input type="button" value="上传文件" onclick="upload()">&nbsp
				<input type="button" id="batch" value="批量删除" style="width:80px;">
			</td>
		</tr>
		<c:if test="${empty mlist}">
${emptyData}


</c:if>
	<c:if test="${not empty mlist}">	
		<tr>
			<td>
			<c:forEach var="ss" items="${mlist}" >
				<div id="photo" style="width:140px;height:170px;margin-left:20px;float: left;">
					<div style="width:140px;height:140px;">
					<video src="${ss.ssURL}"  controls="controls"  width="140px" height="140px" poster="${ss.ssThumbnail}"></video>
					<input type="checkbox" name="chkss" style="display:none" id="chk" value="${ss.ssId}">	
					</div>
					<div name="edit" style="width:140px;height:30px;">
						<div style="width:100px;margin-top: 10px;float: left;"> 
							${ss.ssName}
						</div>
						<div style="width:40px;margin-top: 10px;float: left;"> 
							<a href="/babyplan/StorySongEditServlet?id=${ss.ssId}">编辑</a>
						</div>
					</div>
				</div>
				</c:forEach>
				
			</td>
		</tr>
		</c:if>
	</table>
	<table width='95%' class="tex004" align="center">
			<tr>
				<td align='left'><a href='/babyplan/StorySongListServlet'><span>首页</span></a>
					<c:if test="${page>1}">
						<a href='/babyplan/StorySongListServlet?curPage=${page-1}'><span>上一页</span></a>
					</c:if> <c:if test="${page<pages}">
						<a href='/babyplan/StorySongListServlet?curPage=${page+1}'><span>下一页</span></a>
					</c:if> <a href='/babyplan/StorySongListServlet?curPage=${pages}'>尾页</a></td>
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

