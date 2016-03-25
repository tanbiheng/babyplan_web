package com.gem.babyplan.android.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.entity.Course_Class;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.CourseService;
import com.gem.babyplan.service.Course_ClassService;
import com.gem.babyplan.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/CourseInfoServlet")
public class CourseInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CourseInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentString=request.getParameter("parentString");
		Gson gson=new Gson();
		Type type=new TypeToken<Parent>(){}.getType();
		Parent parent=gson.fromJson(parentString, type);
		Student student=parent.getStudent();
		String studentNumber=student.getStudentNumber();
		StudentService studentService=new StudentService();
		Student student2=studentService.getStudentByNum(studentNumber);
		
		Classes classes=student2.getClasses();
		String classNumber=classes.getClassNumber();
		Course_ClassService course_ClassService=new Course_ClassService();
		List<Course_Class> lists=course_ClassService.getCourse_ClassByClassNumber(classNumber);
		
		
		
		System.out.println(lists);
		Gson gson2=new Gson();
		String ccString=null;
		if(lists!=null){
			ccString=gson2.toJson(lists);
		}
		PrintWriter pWriter=response.getWriter();
		pWriter.println(ccString);
		pWriter.flush();
		if(pWriter!=null){
			pWriter.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
