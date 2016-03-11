package com.gem.babyplan.web.photo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.NoticeService;
import com.gem.babyplan.service.PhotoService;


@WebServlet("/PhotoDeleteServlet")
public class PhotoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String albumId = request.getParameter("albumId");
		String teacherNumber = request.getParameter("teacherNumber");
		
		String[] photoIds = request.getParameterValues("chkphoto");
		if(photoIds!=null){
			int[] photoIds1 = new int[photoIds.length]; 
			for (int i = 0; i < photoIds.length; i++) {
				photoIds1[i]=Integer.parseInt(photoIds[i]);
			}
				PhotoService photoService = new PhotoService();
				photoService.deletePhoto(photoIds1);
		}
		
		request.getRequestDispatcher("/PhotoListServlet?teacherNumber="+teacherNumber+"&albumId="+albumId).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
