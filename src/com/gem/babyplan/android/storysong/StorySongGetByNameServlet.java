package com.gem.babyplan.android.storysong;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class StorySongGetByNameServlet
 */
@WebServlet("/StorySongGetByNameServlet")
public class StorySongGetByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//给出指定的故事儿歌，没有，就返回一个默认值，总之要给他们赋值。
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		System.out.println(name);
		StorySongService sss = new StorySongService();
		StorySong ss = sss.getStorySongByName(name);
		System.out.println(ss);
		if(ss==null)
		{
			ss=sss.getStorySongByName("好想你");
		}
		
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String songs = gson.toJson(ss);
		System.out.println(songs);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(songs);
		pWriter.flush();
		pWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
