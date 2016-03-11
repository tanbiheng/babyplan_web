package com.gem.babyplan.web.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.entity.Course_Class;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.CourseService;
import com.gem.babyplan.service.Course_ClassService;


@WebServlet("/CourseUpdateServlet")
public class CourseUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String courseClassId = request.getParameter("courseClassId");
		String courseName = request.getParameter("courseName");
		String className = request.getParameter("className");
		String weekDay = request.getParameter("weekDay");
		String dayNumber = request.getParameter("dayNumber");
		
		ClassesService service = new ClassesService();
		Classes classes = service.getClassByClassName(className);
		
		CourseService courseService = new CourseService();
		Course course = courseService.getCourseByCourseName(courseName);
	
		Course_ClassService course_ClassService = new Course_ClassService();
		Course_Class course_Class = course_ClassService.getCourse_ClassByCourseClassId(Integer.parseInt(courseClassId));
		course_Class.setCourseClassId(Integer.parseInt(courseClassId));
		course_Class.setClasses(classes);
		course_Class.setCourse(course);
		course_Class.setWeekDay(weekDay);
		course_Class.setDayNumber(dayNumber);
		
		course_ClassService.update(course_Class);
		
		request.getRequestDispatcher("/CourseListServlet?className=全部班级&teacherNumber="+teacherNumber).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
