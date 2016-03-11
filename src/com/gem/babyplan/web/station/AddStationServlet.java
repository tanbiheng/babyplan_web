package com.gem.babyplan.web.station;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.service.CartoonService;

/**
 * Servlet implementation class AddStationServlet
 * 这个页面用来向对方展示有多少个动漫片
 */
@WebServlet("/AddStationServlet")
public class AddStationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		CartoonService cService = new CartoonService();
		List<Cartoon> list = cService.selectAll();
		//进行判断
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/amusement/stationAdd.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
