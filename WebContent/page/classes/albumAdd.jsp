<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>创建相册</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure(){
	var oForm=document.getElementsByName("frmAction")[0];
	oForm.action="/babyplan/AlbumSaveServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}

</script>
<script>
$(document).ready(function(){
	$("[title=default]").focus(function(){
		var v=$(this).val();
		if(v==$(this).prop("defaultValue")){
			$(this).val("");
		}
		$(this).css("border-color","#33FFFF");
	}).blur(function(){
		var v=$(this).val();
		if(v==""){
			$(this).val($(this).prop("defaultValue"));
		}
		$(this).css("border-color","white");
	});
});
</script>

<body>
<form name="frmAction"  method="post" action="" enctype="multipart/form-data">
	<table width="60%" align="center">
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" style="text-indent: 1em;"><b>创建相册</b></td>
			</tr>
			<tr>
				<td width="150" height="60" align="right">相册名称：</td>
				<td><input type="text" name="albumName" title="default"  style="width:420px;"></td>
			</tr>
			<tr>
				<td width="150" height="60" align="right">设置封面：</td>
				<td><input type="file" name="coverPaper" title="default"  style="width:420px;"></td>
			</tr>
			<tr>
				<td width="150" align="right" >相册描述：</td>
				<td><textarea name="albumDescribe" cols="50" title="default" rows="5">说说这个相册的故事...</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" align="right" style="text-indent: 1em;">
					<input type="button" name="test" value="确定" style="width:80px;" onclick="ensure()"/>
					<input type="button" name="test" value="返回" onclick="javascript:location.href='/babyplan/AlbumListServlet?teacherNumber=${teacherNumber}&className=全部班级'" style="width:80px;"/>
				</td>
			</tr>
	</table>
</form>
</body>
</html>
