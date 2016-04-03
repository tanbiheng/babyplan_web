package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Click;
import com.gem.babyplan.service.ClickService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/GetDynamicClickServlet")
public class GetDynamicClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, List<Click>> dynamic_clicks = new HashMap<Integer, List<Click>>();
		ClickService clickService = new ClickService();
		List<Integer> dynamicIds = clickService.getDynamicIdsFromClick();
		for (Integer integer : dynamicIds) {
			List<Click> clicks = clickService.getClickByDynamicId(integer);
			dynamic_clicks.put(integer, clicks);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dynamic_clicksString = null;
		if(dynamic_clicks!=null&&dynamic_clicks.size()!=0){
			dynamic_clicksString = gson.toJson(dynamic_clicks);
		}
		System.out.println(dynamic_clicksString);
		PrintWriter writer = response.getWriter();
		writer.print(dynamic_clicksString);
		writer.flush();
		if(writer!=null){
			writer.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
