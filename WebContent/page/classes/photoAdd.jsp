<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传照片</title>
</head>
<script type="text/javascript">
function ensure() {
	var oForm = document.getElementsByName("frmAction")[0];
	oForm.action = "/babyplan/PhotoSaveServlet?teacherNumber=${teacherNumber}";
	oForm.submit();
}
</script>
<body>
<form name="frmAction"  method="post" action="" enctype="multipart/form-data" >
<div style="width:200px;margin-top: 200px;margin-left: 400px;">
	<table align="center">
		<tr>
		<td>照片名称<input type="text" name="photoName"/></td>
		</tr>
		<tr>
		<td>照片描述<textarea rows="5" cols="30" name="photoDescribe"></textarea></td>
		
		</tr>
		<tr height="50" align="left">
			<td>上传到&nbsp;&nbsp;<select name="albumId" >
			<c:forEach var="albums" items="${albums}">
					<option value="${albums.albumId}" selected>${albums.albumName}</option>
			</c:forEach>
				</select>
			</td>
		</tr>
		<tr align="left">
			<td><input type="file" value="选择照片" name="photoURL"></td>
		</tr>
		<tr>
		<td height="40">
		<input type="button" onclick="ensure()" value="确定" style="width:80px;">&nbsp;&nbsp;
		<input type="button" onclick="javascript:location.href='/babyplan/AlbumListServlet?teacherNumber=${teacherNumber}&className=全部班级'" value="返回" style="width:80px;">
		</td>
    </tr>
	</table>
</div>
</form>
</body>
</html>