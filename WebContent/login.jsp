<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{margin:0px auto;}
#bgimg {
	background-image: url(img/log.jpg);
	height: 630px;
	width:100%;
	background-size:cover;
}
#bgimg table {
	padding-top: 380px;
	padding-left: 160px;
}
</style>

<script>
function login(){
	//alert("ok");
	var frmAction = document.getElementsByName("frmAction")[0];
	var teacherNumber = document.getElementsByName("teacherNumber")[0];
	//alert(teacherNumber.value);
	var teacherPwd = document.getElementsByName("teacherPwd")[0];
	//alert(teacherPwd.value);
	if(!teacherNumber.value || !teacherPwd.value){
		alert("用户命密码不能为空");
	}
	else{
		//alert("登录成功")
		frmAction.action="/babyplan/LoginServlet";
		frmAction.submit();
	}
}

function myreset(){
	//alert("ok");
	var teacherNumber = document.getElementsByName("teacherNumber")[0];
	var teacherPwd = document.getElementByName("teacherPwd")[0];
	teacherNumber.value=null;
	teacherPwd.value=null; 
}
</script>

</head>



<body>
<form name="frmAction" method="post" action="">
	<div id="bgimg">
		<%-- <table width="85%" >
		
		<tr>
			<td width="65">用户名：</td>
			<td width="190"><input type="text" name="teacherNumber"></td>
			<td width="60" align="right">密码：</td>
			<td width="190"><input type="password" name="teacherPwd" ></td>
			<td width="70"><input type="image" src="img/log_btn.png" onclick="login()"></td>
			<td><input type="image" src="img/reset_btn.png" onclick="myreset()"></td>
		</tr>
		
		<tr>
		<td></td>
		<td colspan="5"><span><font color="red"><b>${tips}</b></font></span></td>
		</tr>
	</table> --%>
	<table width="520">
			<tr height="40">
				<td width="65" id="ensure" >用户名：</td>
				<td width="140"><input type="text" name="teacherNumber" style="width:130px"></td>
				<td ><font color="red" size="1" id="notNull"><b>${tips}</b></font></td>
			</tr>
			<tr height="40">
				<td>密&nbsp;码：</td>
				<td><input type="password" name="teacherPwd" style="width:130px"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="image" src="img/log1.jpg" onclick="login()"/>&nbsp;
					<input type="image" src="img/log2.jpg" onclick="myreset()"/>
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>