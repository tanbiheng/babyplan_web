package com.gem.babyplan.android.storysong;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;
import com.google.gson.Gson;

/**
 * Servlet implementation class StoryListServlet
 */
@WebServlet("/StoryListServlet")
public class StoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据类型，返回去值，由于这个返回的是story 0是儿歌，1是故事
		StorySongService ss = new StorySongService();
		List<StorySong> list = ss.getStorySongByType(1);
		//System.out.println(list);
		Gson gson = new Gson();
		String storys = gson.toJson(list);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(storys);
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
