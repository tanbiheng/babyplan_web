package com.gem.babyplan.web.student;

import java.io.File;
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

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class StudentAddServlet
 */
@WebServlet("/StudentAddServlet")
@MultipartConfig
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//增加学生页面
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String studentNumber=request.getParameter("studentNumber");
		String studentName=request.getParameter("studentName");
		String studentSex=request.getParameter("studentSex");
		String classNumber=request.getParameter("className");
		String studentBirthday=request.getParameter("studentBirthday");
		//根据班级名生成对应的文件夹
		ClassesDao cDao = new ClassesDao();
		Classes classes=cDao.getClassesByClassNumber(classNumber);
		File file = new File(ConstantBabyPlan.STUDENT_BACKGROUND_FILE);
		File file2 = new File(file, classes.getClassName());
		file2.mkdir();
		
		Part part =request.getPart("studentPhoto");
		String partBackPhoto = part.getSubmittedFileName();
		//存进数据库
		StudentDao sDao = new StudentDao();
		Student student = new Student();
		student.setClasses(classes);
		student.setStudentName(studentName);
		student.setStudentNumber(studentNumber);
		student.setStudentSex(studentSex);
		//存日期
		//转换一下
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sFormat.parse(studentBirthday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		student.setStudentBirthday(date);
		//存url地址
		student.setStudentPhotoURL(ConstantBabyPlan.STUDENT_BACKGROUND_URL+classes.getClassName()+"/"+partBackPhoto);
		sDao.addStudent(student);
		part.write(file2.toString()+"/"+partBackPhoto);
	   //成功跳进展示页面
		request.getRequestDispatcher("/StudentListServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
