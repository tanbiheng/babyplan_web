package com.gem.babyplan.web.Notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.NoticeService;


@WebServlet("/NoticeDeleteServlet")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String curPage = request.getParameter("curPage");
		String noticeIds[] = request.getParameterValues("chkNotice");
		if(noticeIds!=null){
			int[] noticeIds1 = new int[noticeIds.length]; 
			for (int i = 0; i < noticeIds.length; i++) {
				noticeIds1[i]=Integer.parseInt(noticeIds[i]);
			}
				NoticeService service = new NoticeService();
				service.delete(noticeIds1);
		}
		request.getRequestDispatcher("/NoticeListServlet?className=全部班级&teacherNumber="+teacherNumber+"&curPage=1").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
