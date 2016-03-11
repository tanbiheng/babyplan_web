package com.gem.babyplan.web.publicvideo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.service.PublicVideoService;
import com.mysql.jdbc.Connection;


@WebServlet("/PublicVideoAddServlet")
@MultipartConfig
public class PublicVideoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String publicAddress = request.getParameter("publicAddress");
		String publicDescribe = request.getParameter("publicDescribe");
		
//		List<String> fileNames = new ArrayList<String>();
		
//		Connection parts  = (Connection) request.getParts();
//		for (Iterator<Part> iterator = ((Collection<Part>) parts).iterator(); iterator.hasNext();){
//			Part part = iterator.next();  
////			part.getContentType()
//            //从Part的content-disposition中提取上传文件的文件名  
//            String fileName = part.getSubmittedFileName(); 
//            if(fileName!=null){  
//            	
//                fileNames.add(fileName);  
//                part.write("D:/BabyBaby/publicvideos/"+fileName);  
//            }  
//		}
		
		Part part = request.getPart("publicVideoURL");
		String filename = part.getSubmittedFileName();
		System.out.println(filename);
		String publicVideoURL = "/babyresource/publicvideos/"+filename;//存入数据库的地址
		part.write("D:/BabyBaby/publicvideos/"+filename);

		
		Part part2 = request.getPart("publicThumbnail");
		String filename1 = part2.getSubmittedFileName();
		System.out.println(filename1);
	
		String publicThumbnail = "/babyresource/publicvideos/thumbnail/"+filename1;//存入数据库的地址
		part2.write("D:/BabyBaby/publicvideos/thumbnail/"+filename1);
		
		PublicVideoService publicVideoService = new PublicVideoService();
		PublicVideo publicVideo = new PublicVideo();
		
		publicVideo.setPublicAddress(Integer.parseInt(publicAddress));
		publicVideo.setPublicDescribe(publicDescribe);
		publicVideo.setPublicVideoURL(publicVideoURL);
		publicVideo.setPublicThumbnail(publicThumbnail);
		
		publicVideoService.addPublicVideo(publicVideo);
		
		request.getRequestDispatcher("/PublicVideoListServlet").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
