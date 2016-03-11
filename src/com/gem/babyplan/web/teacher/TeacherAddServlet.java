package com.gem.babyplan.web.teacher;

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
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.TeacherService;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class TeacherAddServlet
 */
@WebServlet("/TeacherAddServlet")
@MultipartConfig
//添加教师信息
public class TeacherAddServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String teacherNum=request.getParameter("teacherNumber");
		String teacherPwd=request.getParameter("teacherPwd");
		String power=request.getParameter("power");
		String classNumber=request.getParameter("classNumber");
		String teacherName=request.getParameter("teacherName");
		String teacherSex=request.getParameter("teacherSex");
		String teacherBirthday=request.getParameter("teacherBirthday");
		String teacherTelePhone=request.getParameter("teacherTelePhone");
		String graduateSchool=request.getParameter("graduateSchool");
		String degree=request.getParameter("degree");
		String specialty=request.getParameter("specialty");
		String reward=request.getParameter("reward");
		String evaluate=request.getParameter("evaluate");
		System.out.println(teacherNum);
		System.out.println(teacherPwd);
		System.out.println(power);
		System.out.println(classNumber);
		System.out.println(teacherName);
		System.out.println(teacherSex);
		System.out.println(teacherBirthday);
		System.out.println(teacherTelePhone);
		System.out.println(graduateSchool);
		System.out.println(degree);
		System.out.println(specialty);
		System.out.println(reward);
		System.out.println(evaluate);
		Part partHeader=request.getPart("teacherHeader");
		Part partShow =request.getPart("rewardShow");
		String teacherHeader = partHeader.getSubmittedFileName();
		String teacherShow=partShow.getSubmittedFileName();
		Teacher teacher = new Teacher();
		TeacherService tService = new TeacherService();
		//查找班级
		ClassesService csService = new ClassesService();
		Classes classes=csService.getClassesByClassNumber(classNumber);
		teacher.setClasses(classes);
		teacher.setDegree(degree);
		teacher.setEvaluate(evaluate);
		teacher.setGraduateSchool(graduateSchool);
		teacher.setPower(Integer.parseInt(power));
		teacher.setReward(reward);
		//存的是获奖的字符串url地址
		teacher.setRewardShow(ConstantBabyPlan.TEACHER_REWORD_URL+teacherShow);
		teacher.setSpecialty(specialty);
		//转换一下
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sFormat.parse(teacherBirthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teacher.setTeacherBirthday(date);
		teacher.setTeacherHeader(ConstantBabyPlan.TEACHER_HEADER_URL+teacherHeader);
		teacher.setTeacherName(teacherName);
		teacher.setTeacherNumber(teacherNum);
		teacher.setTeacherPwd(teacherPwd);
		teacher.setTeacherSex(teacherSex);
		teacher.setTeacherTelePhone(teacherTelePhone);
		tService.addTeacher(teacher);
		//将图片存在实际的硬盘中
		partHeader.write(ConstantBabyPlan.TEACHER_HEADER_FILE+teacherHeader);
		partShow.write(ConstantBabyPlan.TEACHER_REWORD_FILE+teacherShow);
		//存取成功的话，调回list页面
		request.getRequestDispatcher("/TeacherListServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
