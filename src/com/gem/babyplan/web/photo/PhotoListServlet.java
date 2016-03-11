package com.gem.babyplan.web.photo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.service.PhotoService;


@WebServlet("/PhotoListServlet")
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
				
		
		String albumId = request.getParameter("albumId");
		
		PhotoService photoService = new PhotoService();
		List<Photo> photos = photoService.getPhotoByAlbumId(Integer.parseInt(albumId));
		
		request.setAttribute("albumId", albumId);
		request.setAttribute("teacherNumber", teacherNumber);
		request.setAttribute("photos", photos);
		request.getRequestDispatcher("/page/classes/photoList.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
