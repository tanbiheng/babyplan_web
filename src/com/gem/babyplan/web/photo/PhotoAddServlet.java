package com.gem.babyplan.web.photo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.service.AlbumService;


@WebServlet("/PhotoAddServlet")
public class PhotoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		
		
		AlbumService albumService = new AlbumService();
		List<Album> albums = albumService.selectAll();
		
		request.setAttribute("albums", albums);
		request.setAttribute("teacherNumber", teacherNumber);
		request.getRequestDispatcher("/page/classes/photoAdd.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
