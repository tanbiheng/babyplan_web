<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家长建议</title>
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
</head>
<body>
<form name="frmAction" method="post" action="">
	<table width="95%" style="padding:20px;">
	<c:forEach var="suggest" items="${list}">
		<tr height="40">
			<td>${suggest.suggestTime}</td>
		</tr>
		<tr>
			<td width="95%">${suggest.suggestText}</td>
			<td align="center"><a href="/babyplan/SuggestDeleteServlet?suggest=${suggest.suggestId}">删除</a></td>
		</tr>
		<tr>
			<td colspan="2"><hr color="#E5E5E5"></td>
		</tr>
		</c:forEach>
	</table>
	<table width='95%' class="tex004" align="center" style="padding:20px;">
			<tr>
				<td align='left'><a href='/babyplan/SuggestListServlet?curPage=1'><span>首页</span></a>
					<c:if test="${page>1}">
						<a href='/babyplan/SuggestListServlet?curPage=${page-1}'><span>上一页</span></a>
					</c:if> <c:if test="${page<pages}">
						<a href='/babyplan/SuggestListServlet?curPage=${page+1}'><span>下一页</span></a>
					</c:if> <a href='/babyplan/SuggestListServlet?curPage=${pages}'>尾页</a></td>
				
				<td align='left'>共<c:out value="${count}"></c:out>项&nbsp;&nbsp;<c:out
						value="${pages}"></c:out>页
				</td>
			</tr>
	</table>	
</form>
</body>
</html>