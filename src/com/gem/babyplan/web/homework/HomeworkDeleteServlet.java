package com.gem.babyplan.web.homework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.HomeworkService;
import com.gem.babyplan.service.PhotoService;


@WebServlet("/HomeworkDeleteServlet")
public class HomeworkDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		
		String[] hwIds = request.getParameterValues("hwId");
		
		if(hwIds!=null){
			int[] hwIds1 = new int[hwIds.length]; 
			for (int i = 0; i < hwIds.length; i++) {
				hwIds1[i]=Integer.parseInt(hwIds[i]);
			}
				HomeworkService homeworkService = new HomeworkService();
				homeworkService.delete(hwIds1);;
		}
		request.getRequestDispatcher("/HomeworkListServlet?className=全部班级&teacherNumber="+teacherNumber).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
