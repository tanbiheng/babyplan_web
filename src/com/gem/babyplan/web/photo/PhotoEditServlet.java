package com.gem.babyplan.web.photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PhotoEditServlet")
public class PhotoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String photoId = request.getParameter("photoId");
		String albumId = request.getParameter("albumId");
		
		request.setAttribute("albumId", albumId);
		request.setAttribute("photoId", photoId);
		request.setAttribute("teacherNumber", teacherNumber);
		request.getRequestDispatcher("/page/classes/photoEdit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
