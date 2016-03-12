package com.gem.babyplan.android.discuss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.service.DiscussService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/DiscussListServlet")
public class DiscussListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String dynamicId = request.getParameter("dynamicId");
		DiscussService discussService = new DiscussService();
		TreeMap<Integer, List<Discuss>> map = discussService.getAllSortedDiscuss(Integer.parseInt(dynamicId));
		
		List<Discuss> list = discussService.convertMapToList(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String discussString = null;
		if(list!=null){
			discussString = gson.toJson(list);
		}
		
		System.out.println("服务器响应成功");
		
		PrintWriter writer = response.getWriter();
		writer.print(discussString);
		writer.flush();
		if(writer!=null){
			
			writer.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
