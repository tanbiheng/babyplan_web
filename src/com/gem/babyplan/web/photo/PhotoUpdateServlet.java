package com.gem.babyplan.web.photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.service.PhotoService;


@WebServlet("/PhotoUpdateServlet")
public class PhotoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String albumId = request.getParameter("albumId");
		String teacherNumber = request.getParameter("teacherNumber");
		String photoId = request.getParameter("photoId");
		String photoName = request.getParameter("photoName");
		String photoDescribe = request.getParameter("photoDescribe");
		
		PhotoService photoService = new PhotoService();
		Photo photo = photoService.getPhotoById(Integer.parseInt(photoId));
		photo.setPhotoName(photoName);
		photo.setPhotoDescribe(photoDescribe);
		
		photoService.updatePhoto(photo);
		
		request.getRequestDispatcher("/PhotoListServlet?teacherNumber="+teacherNumber+"&albumId="+albumId).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
