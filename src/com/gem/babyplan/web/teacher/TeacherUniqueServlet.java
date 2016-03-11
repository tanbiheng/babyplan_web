package com.gem.babyplan.web.teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.StudentService;

/**
 * Servlet implementation class TeacherUniqueServlet
 */
@WebServlet("/TeacherUniqueServlet")
public class TeacherUniqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentNumber=request.getParameter("num");
		System.out.println(studentNumber);
		//检测数据库中是否存在
		TeacherDao tDao = new TeacherDao();
		Teacher teacher = tDao.getTeacherByTeacherNum(studentNumber);
		if (teacher!=null)
		{
			response.getWriter().write("<font color='#990000'>教师号已经存在，请重新输入</font>");
			
		}else {
			response.getWriter().write("<font color='#003300'>恭喜你，可以使用这个教师号</font>");
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
