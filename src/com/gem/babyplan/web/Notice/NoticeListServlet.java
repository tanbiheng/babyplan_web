package com.gem.babyplan.web.Notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.NoticeService;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/NoticeListServlet")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String className = request.getParameter("className");
		
		System.out.println(className);
		
		ClassesService cservice = new ClassesService();
		List<Classes> classes = cservice.selectAll();
		Classes classes1 = cservice.getClassByClassName(className);
		String classNumber1 = classes1.getClassNumber();
		
		NoticeService nservice = new NoticeService();
		int records = nservice.getCount();
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

		List<Notice> notice = null;
		if(className==null||className.equals("全部班级")){
			notice = nservice.getPagedNotice(curPage, pageSize);
		}else{
			notice = nservice.getNoticeByClassNumber(classNumber1);
		}
		System.out.println(notice);
		request.setAttribute("teacherNumber", teacherNumber);
		System.out.println(teacherNumber);
		
		request.setAttribute("className", className);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("records", records);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("classes", classes);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/page/classes/noticeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
