<!--中文编码设置-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>上传视频</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<script src='/babyplan/common/date/date.js'></script>
<script src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/PrivateVideoSaveServlet?teacherNumber=${teacherNumber}";
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
		
	}).blur(function(){
		var v=$(this).val();
		if(v==""){
			$(this).val($(this).prop("defaultValue"));
		}
		
	});
});
</script>

<body>
<form name="frmAction"  method="post" action="" enctype="multipart/form-data">
	<table width="60%" align="center">
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" style="text-indent: 1em;"><b>上传视频</b></td>
			</tr>
			<tr>
				<td width="150" height="60" align="right">视频地点：</td>
				<td>
					<select name="privateAddress">
						<option value="0">教室</option>
						<option value="1">寝室</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="150" align="right" >视频描述：</td>
				<td><textarea name="privateDescribe" cols="50" title="default" rows="5">描述</textarea></td>
			</tr>
			<tr>
				<td width="150" align="right" height="40">选择视频：</td>
				<td><input type="file" value="选择视频" name="privateVideoURL"></td>
			</tr>
			<tr>
				<td width="150" align="right" height="40">选择缩略图：</td>
				<td><input type="file" value="选择索缩略图" name="privateThumbnail"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#E4E4E4">
				<td colspan="2" height="40" align="right" style="text-indent: 1em;">
					<input type="button" name="test" value="确定" style="width:80px;" onclick="ensure()"/>
					<input type="button" name="test" value="返回" onclick="javascript:location.href='/babyplan/PrivateVideoListServlet?teacherNumber=${teacherNumber}'" style="width:80px;"/>
				</td>
			</tr>
	</table>
</form>
</body>
</html>
