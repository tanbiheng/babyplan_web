package com.gem.babyplan.web.storySong;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;

/**
 * Servlet implementation class StorySongSerachServlet
 */
@WebServlet("/StorySongSerachServlet")
//将模糊查询关键字提交过来
public class StorySongSerachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String serachName=request.getParameter("name");
		//调用模糊查询，得到list,这里的list命名要和之前的返回list一样
		StorySongService service = new StorySongService();
		List<StorySong> list=service.getListByPuzzy(serachName);
		//先判断返回有没有数据
		if (list.size()==0)
		{
			request.setAttribute("emptyData", "没有符合这样要求的数据");
			
		}else 
		{
			request.setAttribute("mlist", list);
		}
		request.getRequestDispatcher("/page/amusement/storySongList.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
