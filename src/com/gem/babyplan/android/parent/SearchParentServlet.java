package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ApplyService;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/SearchParentServlet")
public class SearchParentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchText = request.getParameter("searchText");
		String parentId	= request.getParameter("parentId");
		
		ParentsService parentsService = new ParentsService();
		List<Parent> parents = parentsService.getParentByTelePhone_Name(searchText);
		
		ApplyService applyService = new ApplyService();
		List<Parent> parents2 = applyService.getParentFriendByParentId(Integer.parseInt(parentId));
		
		Gson gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String resultParentString = null;
		String parentFriendString = null;
		if(parents!=null){
			resultParentString = gson.toJson(parents);
			parentFriendString = gson.toJson(parents2);
		}
		String result_friendString = resultParentString+"&&&&"+parentFriendString;
		PrintWriter writer = response.getWriter();
		writer.println(result_friendString);
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
