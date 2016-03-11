package com.gem.babyplan.web.privatevideo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Authenticator.RequestorType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.PrivateVideoService;
import com.gem.babyplan.service.TeacherService;


@WebServlet("/PrivateVideoSaveServlet")
@MultipartConfig
public class PrivateVideoSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String privateAddress = request.getParameter("privateAddress");
		String privateDescribe = request.getParameter("privateDescribe");
		
		String teachrNumber = request.getParameter("teacherNumber");
		TeacherService teacherService = new TeacherService();
		Teacher teacher = teacherService.getTeacherByTeacherNum(teachrNumber);
		Classes classes = teacher.getClasses();
		
		Part part1 = request.getPart("privateVideoURL");
		String filename = part1.getSubmittedFileName();
		System.out.println(filename);
		String privateVideoURL = "/babyresource/privatevideos/"+filename;//存入数据库的地址
		part1.write("D:/BabyBaby/privatevideos/"+filename);
		
		Part part2 = request.getPart("privateThumbnail"); 
		String filename1 = part2.getSubmittedFileName();
		System.out.println(filename1);
	
		String privateThumbnail = "/babyresource/privatevideos/thumbnail/"+filename1;//存入数据库的地址
		part2.write("D:/BabyBaby/privatevideos/thumbnail/"+filename1);
		
		PrivateVideoService privateVideoService = new PrivateVideoService();
		PrivateVideo privateVideo = new PrivateVideo();
		privateVideo.setClasses(classes);
		privateVideo.setPrivateAddress(Integer.parseInt(privateAddress));
		privateVideo.setPrivateDescribe(privateDescribe);
		privateVideo.setPrivateVideoURL(privateVideoURL);
		privateVideo.setPrivateThumbnail(privateThumbnail);
		
		privateVideoService.addPrivateVideo(privateVideo);
		request.getRequestDispatcher("/PrivateVideoListServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
