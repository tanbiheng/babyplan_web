package com.gem.babyplan.web.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.TeacherService;

/**
 * Servlet implementation class TeacherDeleteServlet
 */
@WebServlet("/TeacherDeleteServlet")
public class TeacherDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//教师端的删除
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String teacherNum=request.getParameter("teacherNum");
	TeacherService tService = new TeacherService();
	tService.deleteTeacher(teacherNum);
	//返回页面
	request.getRequestDispatcher("/page/user/teacherList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
