<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="/babyplan/css/demo.css" type="text/css">
<title>留言管理</title>
<script type="text/javascript" src="/babyplan/easyui/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#words").click(function(){
			$("#show").css("display","block");
		});
		$("[name=hide]").click(function(){
			$("#show").css("display","none");
		});
	});
</script>
</head>
<body>
<form name="frmAction" method="post" action="">
	<table width="95%" style="padding:20px;">
		<tr>
			<td width="70" >
			<img src="/babyplan/img/1.jpg" width="50" height="50" style="vertical-align: top;"></td>
			<td align="left"><b><font color="#990099">猪八戒</font></b></td>
		</tr>
		<tr>
			<td></td>
			<td>孩子今天感冒了，记得喂药哦</td>	
		</tr>
		<tr>
			<td></td>
			<td>
			<p>&nbsp;</p>
			2016-02-04&nbsp;&nbsp;10:10&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <font id="words" color="#0033FF">回复</font> -->
			<input type="button" value="回复" id="words">&nbsp;&nbsp;
			<input type="button" value="删除" id="words">
			</td>
		</tr>
		<tr>
			<td></td>
			<td id="show" style="display: none;"><textarea name="wordText" id="" cols="60" rows="3"></textarea><br><br>
				<input type="submit" value="确定" name="hide">
				<input type="reset" value="取消" name="hide">
			</td>
		</tr>
		<tr>
			<td colspan="4"><hr color="#E5E5E5"></td>
		</tr>
	</table>
	<table width='95%' class="tex004" align="center" style="padding:20px;">
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
	</table>
</form>		
</body>
</html>