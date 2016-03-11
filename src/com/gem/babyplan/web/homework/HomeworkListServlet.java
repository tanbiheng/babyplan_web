package com.gem.babyplan.web.homework;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Homework;
import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.HomeworkService;
import com.gem.babyplan.service.NoticeService;


@WebServlet("/HomeworkListServlet")
public class HomeworkListServlet extends HttpServlet {
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
		
		HomeworkService homeworkService = new HomeworkService();
		int records = homeworkService.getCount();
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

		List<Homework> homework = null;
		if(className==null||className.equals("全部班级")){
			homework = homeworkService.getPagedHomework(curPage, pageSize);
		}else{
			homework = homeworkService.getHomeworkByClassNumber(classNumber1);
		}
		System.out.println(homework);
		request.setAttribute("teacherNumber", teacherNumber);
		System.out.println(teacherNumber);
		
		request.setAttribute("className", className);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("records", records);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("classes", classes);
		request.setAttribute("homework", homework);
		request.getRequestDispatcher("/page/classes/homeworkList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
