package com.gem.babyplan.web.Notice;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.service.NoticeService;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/NoticeDetailServlet")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String noticeId = request.getParameter("noticeId");
		System.out.println(noticeId);
		
		NoticeService service = new NoticeService();
		Notice notice = service.getNoticeByNoticeId(Integer.parseInt(noticeId));
		String noticeText = notice.getNoticeText();
		Date date = notice.getNoticeTime();
		
		request.setAttribute("noticeText", noticeText);
		request.setAttribute("date", date);
		request.setAttribute("teacherNumber", teacherNumber);
		request.getRequestDispatcher("/page/classes/noticeDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
