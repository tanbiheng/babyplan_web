<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知详情</title>
</head>
<body>
	<table width="50%" align="center">
		<tr height="30">
			<td align="center" ><b>班级通知</b><div align="right"><a href="/babyplan/NoticeListServlet?teacherNumber=${teacherNumber}">返回</a></div></td>
		</tr>
		<tr style="line-height:30px;text-indent: 2em;">
			<td><div style="border: 1px solid #999;">${noticeText}
			<p align="right">${date}</p>
			</div>
			</td>
		</tr>
	</table>
	
</body>
</html>