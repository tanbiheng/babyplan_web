package com.gem.babyplan.web.homework;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Homework;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.HomeworkService;
import com.gem.babyplan.service.TeacherService;


@WebServlet("/HomeworkSaveServlet")
@MultipartConfig
public class HomeworkSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		System.out.println(teacherNumber);
		
		TeacherService tservice = new TeacherService();
		Teacher teacher = tservice.getTeacherByTeacherNum(teacherNumber);
		Classes classes = teacher.getClasses();
		System.out.println(classes);
		
		
		String hwType = request.getParameter("hwType");
		String hwTitle = request.getParameter("hwTitle");
		String hwExplain = request.getParameter("hwExplain");
//		String hwPicture = request.getParameter("hwPicture");
		String hwEndTime = request.getParameter("hwEndTime");
//		String hwStatus = request.getParameter("");
		
		Part part = request.getPart("hwPicture");

		String filename = part.getSubmittedFileName();
		String fileName1 = "/babyresource/homework/"+filename;//存入数据库的地址
		part.write("D:/BabyBaby/homework/"+filename);
		
		HomeworkService homeworkService = new HomeworkService();
		
		Homework homework = new Homework();
		homework.setClasses(classes);
		homework.setHwType(hwType);
		homework.setHwTitle(hwTitle);
		homework.setHwExplain(hwExplain);
		homework.setHwPicture(fileName1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(hwEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		homework.setHwEndTime(date); 
		homeworkService.save(homework);
		
		request.getRequestDispatcher("/HomeworkListServlet?className=全部班级&teacherNumber="+teacherNumber).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
