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
 * 根据模糊查询的名字，返回结果故事儿歌。
 */
@WebServlet("/StorySongPuzzyNameServlet")
public class StorySongPuzzyNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String puzzyName=request.getParameter("puzzyName");
		StorySongService sss = new StorySongService();
		List<StorySong> list=sss.getListByPuzzy(puzzyName);
		Gson gson = new Gson();
		String songs=gson.toJson(list);
		System.out.println(songs);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(songs);
		pWriter.flush();
		pWriter.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
