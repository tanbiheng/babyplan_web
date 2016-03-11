package com.gem.babyplan.android.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentString = request.getParameter("parentString");
		Gson gson = new Gson();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		Student student = parent.getStudent();
		String studentNumber = student.getStudentNumber();
		
		StudentService studentService = new StudentService();
		Student student2 = studentService.getStudentByNum(studentNumber);
		
		System.out.println(student2);
		
		String studentString = null;
		Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if(student2!=null){
			studentString = gson2.toJson(student2);
		}
		System.out.println("解析后结果："+studentString);
		PrintWriter out = response.getWriter();
		out.print(studentString);
		out.flush();
		if(out!=null){
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
