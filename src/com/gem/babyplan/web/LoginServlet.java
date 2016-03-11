package com.gem.babyplan.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.TeacherService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		System.out.println(teacherNumber);
		String teacherPwd = request.getParameter("teacherPwd");
		System.out.println(teacherPwd);
		
		TeacherService service = new TeacherService();
		Teacher teacher = service.getTeacherByTeacherNum(teacherNumber);
		
		if(teacher!=null && teacherPwd.equals(teacher.getTeacherPwd())){
			String teacherName = teacher.getTeacherName();
			int power = teacher.getPower();
			request.setAttribute("power", power);
			request.setAttribute("teacherName", teacherName);
			request.setAttribute("teacherNumber", teacherNumber);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			String tips = "*用户名或者密码不正确！";
			request.setAttribute("tips", tips);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
