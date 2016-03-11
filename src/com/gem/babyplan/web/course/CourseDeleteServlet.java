package com.gem.babyplan.web.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.Course_ClassService;


@WebServlet("/CourseDeleteServlet")
public class CourseDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		//String curPage = request.getParameter("curPage");
		String courseClassIds[] = request.getParameterValues("chkCourseClass");
		if(courseClassIds!=null){
			int[] courseClassIds1 = new int[courseClassIds.length]; 
			for (int i = 0; i < courseClassIds.length; i++) {
				courseClassIds1[i]=Integer.parseInt(courseClassIds[i]);
			}
				Course_ClassService service = new Course_ClassService();
				service.delete(courseClassIds1);
		}
		request.getRequestDispatcher("/CourseListServlet?className=全部班级&teacherNumber="+teacherNumber+"&curPage=1").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
