package com.gem.babyplan.web.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course_Class;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.Course_ClassService;
import com.gem.babyplan.service.TeacherService;


@WebServlet("/CourseListServlet")
public class CourseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String className = request.getParameter("className");
		
		TeacherService service = new TeacherService();
		Teacher teacher = service.getTeacherByTeacherNum(teacherNumber);
		int power = teacher.getPower();
		
		ClassesService cservice = new ClassesService();
		List<Classes> classes = cservice.selectAll();
		Classes classes1 = cservice.getClassByClassName(className);
		String classNumber1 = classes1.getClassNumber();
		
		Course_ClassService ccService = new Course_ClassService();
		
		int records = ccService.getCount();
		int pageSize = 1;
		int totalPage = 0;
		if((records%pageSize)==0){
			totalPage = records/pageSize;
		}else{
			totalPage = records/pageSize+1;
		}
		String tpage = request.getParameter("curPage");
		String goPage = request.getParameter("goPage");
		System.out.println("goPage="+goPage);
		int curPage = 1;
		if((goPage!=null)&&(!goPage.equals(""))){
			curPage = Integer.parseInt(goPage);
		}
		
		if(tpage!=null){
			curPage = Integer.parseInt(tpage);
		}

		List<Course_Class> course_Class = null;
		if(className==null||className.equals("全部班级")){
			System.out.println("pageSize="+pageSize);
			course_Class = ccService.getPagedCourse_Class(curPage, pageSize);
		}else{
			course_Class = ccService.getCourse_ClassByClassNumber(classNumber1);
		}
		System.out.println(course_Class);
		request.setAttribute("teacherNumber", teacherNumber);
		System.out.println(teacherNumber);
		
		request.setAttribute("power", power);
		request.setAttribute("className", className);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("records", records);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("classes", classes);
		request.setAttribute("course_Class", course_Class);
		request.getRequestDispatcher("/page/classes/course_classList_Admin.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
