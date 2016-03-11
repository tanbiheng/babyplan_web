package com.gem.babyplan.web.station;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.service.CartoonService;
import com.gem.babyplan.service.StationService;

/**
 * Servlet implementation class StationListServlet
 * 根据传过来的动漫id,查找相应里面的动漫的集数，反馈给需要展示集数的页面
 */
@WebServlet("/StationListServlet")
public class StationListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String cartoonId=request.getParameter("id");
		
		int id = Integer.parseInt(cartoonId);
		//根据cartoonid找到相应的集数
		StationService ss = new StationService();
		List<Station> list =ss.getStationByCartoonId(id);
		//由于需要动漫的名称，因此需要把动漫对象也找出来
		CartoonService cs = new CartoonService();
		Cartoon cartoon =cs.getCartoonById(id);
		System.out.println(cartoon);
		//最后跳转的是每一集数的显示页面
	   //判断一下
		if (list.size()==0)
		{
			//如果为零，友情提醒一下
			request.setAttribute("empty", "该部动漫还没有添加集数，请添加");
		}else
		{
			request.setAttribute("list", list);
			request.setAttribute("cartoon", cartoon);
		}
		request.getRequestDispatcher("/page/amusement/stationList.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
