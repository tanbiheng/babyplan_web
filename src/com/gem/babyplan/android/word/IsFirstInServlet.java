package com.gem.babyplan.android.word;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.ParentsService;

/**
 * Servlet implementation class IsFirstInServlet
 */
@WebServlet("/IsFirstInServlet")
public class IsFirstInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//判断是否第一次进来的
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String parentId=request.getParameter("parentId");
		ParentsService ps = new ParentsService();
		String teacherNum = ps.getTeacherNumByParentId(Integer.parseInt(parentId));
		System.out.println(teacherNum);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(teacherNum);
		pWriter.flush();
		pWriter.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
