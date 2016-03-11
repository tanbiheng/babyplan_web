package com.gem.babyplan.web.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.CourseService;


@WebServlet("/CourseAddServlet")
public class CourseAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		ClassesService cservice = new ClassesService();
		List<Classes> classes = cservice.selectAll();
		
		CourseService courseService = new CourseService();
		List<Course> courses = courseService.selectAll();
		
		request.setAttribute("teacherNumber", teacherNumber);
		request.setAttribute("classes", classes);
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("/page/classes/course_classAdd.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
