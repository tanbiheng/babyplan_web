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
 * Servlet implementation class StorySongEditEnsureServlet
 */
@WebServlet("/StorySongEditEnsureServlet")
//编辑后提交过来的数据，需要保存
public class StorySongEditEnsureServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String ssId=request.getParameter("id");
		//强制转换成整形
		int id = Integer.parseInt(ssId);
		String ssName =request.getParameter("albumName");
		String ssBrief =request.getParameter("albumDescribe");
		//将数据封装到实体中，调用service的更新方法
		StorySong ss = new StorySong();
		ss.setSsBrief(ssBrief);
		ss.setSsId(id);
		ss.setSsName(ssName);
		StorySongService storySongService =new StorySongService();
		storySongService.updateSs(ss);
		//执行成功后，跳转页面，给个提示？
		request.getRequestDispatcher("/StorySongListServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
