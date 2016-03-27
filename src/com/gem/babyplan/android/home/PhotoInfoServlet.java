package com.gem.babyplan.android.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.service.PhotoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/PhotoInfoServlet")
public class PhotoInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String albumString = request.getParameter("albumString");
		Gson gson=new Gson();
		Type type=new TypeToken<Album>(){}.getType();
		Album album=gson.fromJson(albumString , type);
		
		int  albumId=album.getAlbumId();
		PhotoService photoService=new PhotoService();
		List<Photo> photos=photoService.getPhotoByAlbumId(albumId);
		System.out.println(photos);
		Gson gson2=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String result=null;
		if(photos!=null){
			result=gson2.toJson(photos);
		}
		PrintWriter pWriter=response.getWriter();
		pWriter.println(result);
		pWriter.flush();
		if(pWriter!=null){
			pWriter.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
