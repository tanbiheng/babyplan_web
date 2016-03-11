package com.gem.babyplan.web.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.StudentService;

/**
 * Servlet implementation class AjaxUniqueNumberServlet
 */
@WebServlet("/AjaxUniqueNumberServlet")
public class AjaxUniqueNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过异步请求来判断是否唯一
		String studentNumber=request.getParameter("num");
		System.out.println(studentNumber);
		//检测数据库中是否存在
		StudentService service = new StudentService();
		Student student =service.getStudentByNum(studentNumber);
		if (student!=null)
		{
			response.getWriter().write("<font color='#990000'>该学号已经存在，请重新输入一个学号</font>");
			
		}else {
			response.getWriter().write("<font color='#003300'>恭喜你，可以使用这个学号</font>");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
