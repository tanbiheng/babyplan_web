package com.gem.babyplan.web.suggest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Suggest;
import com.gem.babyplan.service.SuggestService;

/**
 * Servlet implementation class SuggestAddServlet
 */
@WebServlet("/SuggestAddServlet")
public class SuggestAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//这是家长提出建议的servlet，针对的是安卓端，安卓端命名为suggestText,得到内容
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suggestText=request.getParameter("suggestText");
		if (suggestText!=null)
		{
			SuggestService service = new SuggestService();
			Suggest suggest = new Suggest();
			suggest.setSuggestText(suggestText);
			service.addSuggest(suggest);
		}
		//返回给安卓端
		PrintWriter pWriter=response.getWriter();
		pWriter.write("非常感谢您的建议和留言，不足之处，我们会第一时间修改，欢迎监督和留言！！");
		pWriter.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
