package com.gem.babyplan.web.storySong;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;

/**
 * Servlet implementation class StorySongEditServlet
 */
@WebServlet("/StorySongEditServlet")
//接收从提交编辑过来的数据，并返回到编辑页面，带上数据
public class StorySongEditServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到编辑按钮提交过来的id
		String ssId=request.getParameter("id");
		//强转
		int id =Integer.parseInt(ssId);
		System.out.println(id);
		//调用dao层的得到对象
		StorySongService sss = new StorySongService();
		//把这个数据写会编辑页面
		StorySong storySong=sss.getStorySong(id);
		System.out.println(storySong);
		request.setAttribute("storysong", storySong);
		request.getRequestDispatcher("/page/amusement/ssEdit.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
