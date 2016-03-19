package com.gem.babyplan.android.cartoon;

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

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.service.CartoonService;
import com.gem.babyplan.utils.ConstantBabyPlan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CartoonListServlet
 */
@WebServlet("/CartoonListServlet")
public class CartoonListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	//这里面应该得到卡通的全部内容
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		CartoonService cs = new CartoonService();
		int currPage =1;
		String current=request.getParameter("curPage");
		if (current!=null) 
		{	
			currPage=Integer.parseInt(current);
			
		}
		HashMap<Cartoon,List<Station>> hashMap = cs.getAndroidPagedCartoon(currPage,  ConstantBabyPlan.RECODE_SHOW_NUM);
		
		for (Map.Entry<Cartoon, List<Station>> entry: hashMap.entrySet())
		{
			System.out.println(entry.getKey());
			List<Station> list = entry.getValue();
			for (Station station : list) {
				System.out.println(station);
			}
		}
		
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String songs = gson.toJson(hashMap);
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
