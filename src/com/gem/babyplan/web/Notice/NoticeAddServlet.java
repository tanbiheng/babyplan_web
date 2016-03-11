package com.gem.babyplan.web.Notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.NoticeService;
import com.gem.babyplan.service.TeacherService;

/**
 * Servlet implementation class NoticeAddServlet
 */
@WebServlet("/NoticeAddServlet")
public class NoticeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		System.out.println(teacherNumber);

		
//		TeacherService tservice = new TeacherService();
//		Teacher teacher = tservice.getTeacherByTeacherNum(teacherNumber);
//		Classes classes = teacher.getClasses();
//		System.out.println(classes);
//		
//		String noticeText = request.getParameter("noticeText");
//		Notice notice = new Notice();
//		notice.setNoticeText(noticeText);
//		notice.setClasses(classes);
//		NoticeService nservice = new NoticeService();
//		nservice.save(notice);
		request.setAttribute("teacherNumber", teacherNumber);
		request.getRequestDispatcher("/page/classes/noticeAdd.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
