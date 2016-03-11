package com.gem.babyplan.web.photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.RequestWrapper;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.service.AlbumService;
import com.gem.babyplan.service.PhotoService;


@WebServlet("/PhotoSaveServlet")
@MultipartConfig
public class PhotoSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String photoName = request.getParameter("photoName");
		String photoDescribe = request.getParameter("photoDescribe");
		String albumId = request.getParameter("albumId");
		Part part = request.getPart("photoURL");

		String filename = part.getSubmittedFileName();
		String fileName1 = "/babyresource/photos/"+filename;//存入数据库的地址
		part.write("D:/BabyBaby/photos/"+filename);
		
		AlbumService albumService = new AlbumService();
		Album album = albumService.getAlbumByAlbumId(Integer.parseInt(albumId));
		
		PhotoService photoService = new PhotoService();
		Photo photo = new Photo();
		
		photo.setAlbum(album);
		photo.setPhotoName(photoName);
		photo.setPhotoDescribe(photoDescribe);
		photo.setPhotoURL(fileName1);
		
		photoService.addPhoto(photo);
		
		request.getRequestDispatcher("/AlbumListServlet?className=全部班级&teacherNumber="+teacherNumber).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
