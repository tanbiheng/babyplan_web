<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>宝贝计划后台管理系统</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script>	
	$(function(){
		$('.easyui-accordion li a').click(function () {
			var title=$(this).text();
			var url=$(this).attr("url");
			 if ($('#tabs').tabs('exists', title)){
				$('#tabs').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tabs').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		});
	})
</script>
<body class="easyui-layout"> 
	<div data-options="region:'north'" style="height:100px;">
		<img src="img/head4.png" width="1363"/>
	</div>       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
	<div id="aa" class="easyui-accordion" style="width:200px;height:480px;">   
		<div title="班级信息管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
			 <ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/NoticeListServlet?teacherNumber=${teacherNumber}">班级通知</a></span>  
				</li>   
				<li>   
					<span><a url="/babyplan/AlbumListServlet?teacherNumber=${teacherNumber}">班级相册</a></span>   
				</li>   
				<li>   
					<span><a url="/babyplan/CourseListServlet?teacherNumber=${teacherNumber}">班级课表</a></span>   
				</li> 
				<li>   
					<span><a url="/babyplan/HomeworkListServlet?teacherNumber=${teacherNumber}">宝宝作业</a></span>   
				</li>
			</ul>  

		</div>   
		<div title="留言管理"  style="padding:10px; " data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/ShowLeaveMessageServlet?teacherNumber=${teacherNumber}">留言管理</a></span>  
				</li>   
			</ul>     
		</div>  
		<c:if test="${power==0}">
		<div title="视频管理" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/PublicVideoListServlet?teacherNumber=${teacherNumber}">公共视频</a></span>  
				</li> 
				<li >   
					<span><a url="/babyplan/PrivateVideoListServlet?teacherNumber=${teacherNumber}">班级视频</a></span>  
				</li>
			</ul>     
		</div> 
<!-- 		<div title="宝宝乐园管理" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="page/amusement/storySongList.jsp">儿歌故事管理</a></span>  
				</li> 
				<li >   
					<span><a url="page/amusement/cartoonList.jsp">动画管理</a></span>  
				</li>
			</ul>      
		</div> 
		<div title="家长建议" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="page/suggest/suggestList.jsp">家长建议</a></span>  
				</li>   
			</ul>     
		</div> 
		<div title="用户管理" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="page/user/teacherList.jsp">教师信息管理</a></span>  
				</li> 
				<li >   
					<span><a url="page/user/parentList.jsp">家长信息管理</a></span>  
				</li>
				<li >   
					<span><a url="page/user/studentList.jsp">宝宝信息管理</a></span>  
				</li>
			</ul>       
		</div> -->
				<div title="宝宝乐园管理" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/StorySongListServlet">儿歌故事管理</a></span>  
				</li> 
				<li >   
					<span><a url="/babyplan/CarToonListServlet">动画管理</a></span>  
				</li>
			</ul>      
		</div> 
		<div title="家长建议" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/SuggestListServlet">家长建议</a></span>  
				</li>   
			</ul>     
		</div> 
		<div title="用户管理" data-options="iconCls:'icon-reload'">   
			<ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a url="/babyplan/TeacherListServlet">教师信息管理</a></span>  
				</li> 
				<li >   
					<span><a url="/babyplan/ParentsListServlet">家长信息管理</a></span>  
				</li>
				<li >   
					<span><a url="/babyplan/StudentListServlet">宝宝信息管理</a></span>  
				</li>
			</ul>       
		</div>
		</c:if> 
		<div title="安全退出" data-options="iconCls:'icon-reload',selected:true">   
			  <ul id="tt" class="easyui-tree" >   
				<li >   
					<span><a href="login.jsp" style="text-decoration: none;">安全退出</a></span>  
				</li>   
			</ul> 
		</div> 
		</div>  
	</div>   
    <div data-options="region:'center',title:'欢迎你！${teacherName} '" style="padding:5px;background:#eee;">
		<div id="tabs" class="easyui-tabs" style="height:540px;width:100%">    
			<div title="欢迎使用" data-options="closable:true" style="padding:20px;
			background-image: url(img/welcome.jpg);background-size:cover;">   
				<!-- <img src="img/welcome.jpg" width="1100"/> -->
			</div>   
		</div>  
	</div>    
</body> 
</html>