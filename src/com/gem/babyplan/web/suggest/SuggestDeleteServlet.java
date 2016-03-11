package com.gem.babyplan.web.suggest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.SuggestService;

/**
 * Servlet implementation class SuggestDeleteServlet
 */
@WebServlet("/SuggestDeleteServlet")
public class SuggestDeleteServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		SuggestService service = new SuggestService();
		//批量删除
		String id=request.getParameter("suggest");
		//
		System.out.println(id);
		int suggestId=0;
		if (id!=null)
		{
			suggestId=Integer.parseInt(id);
			
		}
		service.deleteSuggests(new int[]{suggestId});
		//成功后再跳转list页面
		request.getRequestDispatcher("/SuggestListServlet").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
