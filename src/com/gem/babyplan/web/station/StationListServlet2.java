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
 * Servlet implementation class StationListServlet2
 */
@WebServlet("/StationListServlet2")
public class StationListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//注意，这个页面是每当添加某一部动画片的集数，成功后，跳转的当前动画片的集数展示，和之前有点区别
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
				String cartoonId=(String) request.getAttribute("id");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
