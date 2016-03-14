package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/GetAboutParentDynamicServlet")
public class GetAboutParentDynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String parentId = request.getParameter("parentId");
		ParentsService parentsService = new ParentsService();
		Map<Dynamic, List<Discuss>> dynamic_Discuss = parentsService.getAboutParentDynamic_DiscussByParentId(Integer.parseInt(parentId));
//		List<Dynamic> dynamics = parentsService.getDynamicByParentId(Integer.parseInt(parentId));
		
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		String dynamic_DiscussString = null;
		if(dynamic_Discuss!=null){
			dynamic_DiscussString = gson.toJson(dynamic_Discuss);
		}
		System.out.println(dynamic_DiscussString);
		
		PrintWriter writer = response.getWriter();
		writer.print(dynamic_DiscussString);
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
