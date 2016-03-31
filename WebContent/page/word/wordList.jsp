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
	
	function addWord(yy) 
	{
		alert("ok");
		  debugger;
		var parentId=document.getElementsByName("parentId")[yy].value;
		//debugger;
		var teacherNum=document.getElementsByName("teacherNum")[yy].value;
		var text=document.getElementsByName("textWord")[yy].value;
		 debugger;
		var superId=document.getElementsByName("superWordId")[yy].value;
		var oForm = document.getElementsByName("frmAction")[0];
		//debugger;
		
		//debugger;
		if(parentId&&teacherNum&&text&&superId)
		{
	//  alert(parentId+"--"+teacherNum+"--"+superId+"--"+text);
	   oForm.action = "/babyplan/WordAddFromTeacherServlet?parentId="+parentId+"&teacherNum="+teacherNum+"&text="+text+"&superId="+superId;
	   debugger;
	    oForm.submit(); 
	   // window.location="https://www.baidu.com";
	    debugger;
	  // window.location = "/babyplan/WordAddFromTeacherServlet?parentId="+parentId+"&teacherNum="+teacherNum+"&text="+text+"&superId="+superId; 
	    debugger;
		}
	else {
		alert("都不能为空啊，大哥！");
	}
		
	}
	
</script>
<!-- <script type="text/javascript">
	$(document).ready(function(){
		$("[title=words]").click(function(){
			$("[title=show]").css("display","block");
		});
		$("[name=hide]").click(function(){
			$("[title=show]").css("display","none");
		});
	});
</script> -->
</head>
<body>
<form name="frmAction" method="POST" action="">
<c:forEach items="${hashMap}" var="entry" varStatus="xx">
	<table width="95%" style="padding:20px;">
	
		<tr>
			<td width="70" >
			<img src="${entry.key.parent.parentHeader}" width="50" height="50" style="vertical-align: top;"></td>
			<td align="left"><b><font color="#990099">${entry.key.parent.parentNickName}</font></b></td>
		</tr>
		<tr>
			<td></td>
			<!-- 家长发起的会话-->
			<td>${entry.key.wordText}<p>&nbsp;</p></td>	
		</tr>
		<!-- 回复内容 -->
		<c:choose>
		<c:when test="${entry.value.size()==0}">
		<input type="hidden" name="superWordId" value="${entry.key.wordId}">
		</c:when>
		<c:otherwise>
		
			<c:forEach items="${entry.value}" var="word" varStatus="vs">
		
		<c:if test="${vs.last==true}">
		  <input type="hidden" name="superWordId" value="${word.wordId}">
		</c:if>
					<tr style="font-size: 12px;">
						<c:choose>

							<c:when test="${entry.value.size()%2==0}">
						
								<c:choose>

									<c:when test="${vs.count%2==0}">
										<td></td>
										<td><font color="#4277A5">${entry.key.parent.parentNickName}&nbsp;</font>回复：${word.wordText}</td>
									</c:when>
									<c:otherwise>
										<td></td>
										<td><font color="#4277A5">${entry.key.teacher.teacherName}&nbsp;</font>回复：${word.wordText}</td>

									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>                          
                              <c:choose>
									<c:when test="${vs.count%2==0}">
										<td></td>
										<td><font color="#4277A5">${entry.key.parent.parentNickName}&nbsp;</font>回复：${word.wordText}</td>
									</c:when>
									<c:otherwise>
										<td></td>
										<td><font color="#4277A5">${entry.key.teacher.teacherName}&nbsp;</font>回复：${word.wordText}</td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>	
		
		</c:otherwise>
		</c:choose>
	
		<tr>
			<td></td>
			<td>
			<p>&nbsp;</p>
			${entry.key.wordTime}&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <font id="words" color="#0033FF">回复</font> -->
			<c:if test="${entry.value.size()%2==0}">
			
			
			<!-- <textarea name="wordText"  cols="60" rows="3"></textarea> -->
		
			</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="4"><hr color="#E5E5E5"></td>
			<td><input type="button" value="提交回复" onclick="addWord(${xx.index})" name="tijiao">&nbsp;&nbsp;</td>
			<td><input type="text" name="textWord" value="请留言"  /></td>
		</tr>
		<tr>
		<td>
		<input type="hidden" name="teacherNum" value="${entry.key.teacher.teacherNumber}">
			<input type="hidden" name="parentId" value="${entry.key.parent.parentId}">
		</td>
		
		</tr>
	</table>
	</c:forEach>
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