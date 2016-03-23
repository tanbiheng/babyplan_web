package com.gem.babyplan.android.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.TeacherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/TeacherInfoServlet")
public class TeacherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		TeacherService teacherService=new TeacherService();
		List<Teacher> teachers=teacherService.getAllTeachers();
		System.out.println(teachers);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String teaString=null;
		if(teachers!=null){
			teaString=gson.toJson(teachers);
		}
		
		PrintWriter pWriter=response.getWriter();
		pWriter.println(teaString);
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
