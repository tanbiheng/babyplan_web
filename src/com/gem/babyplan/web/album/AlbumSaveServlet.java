package com.gem.babyplan.web.album;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.AlbumService;
import com.gem.babyplan.service.TeacherService;


@WebServlet("/AlbumSaveServlet")
@MultipartConfig
public class AlbumSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String albumName = request.getParameter("albumName");
		String albumDescribe = request.getParameter("albumDescribe");
//		String coverPaper = request.getParameter("coverPaper");
		Part part = request.getPart("coverPaper");
		String filename = part.getSubmittedFileName();
		String fileName1 = "/babyresource/photos/"+filename;//存入数据库的地址
		part.write("D:/BabyBaby/photos/"+filename);
		
		TeacherService tservice = new TeacherService();
		Teacher teacher = tservice.getTeacherByTeacherNum(teacherNumber);
		Classes classes = teacher.getClasses();
		
		AlbumService albumService = new AlbumService();
		Album album = new Album();
		album.setClasses(classes);
		album.setAlbumName(albumName);
		album.setCoverPaper(fileName1);
		album.setAlbumDescribe(albumDescribe);
		
		albumService.save(album);
		
		request.getRequestDispatcher("/AlbumListServlet?className=全部班级&teacherNumber="+teacherNumber).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
