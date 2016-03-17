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
import com.google.gson.Gson;


@WebServlet("/GetParentFriendsServlet")
public class GetParentFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parentId = request.getParameter("parentId");
		
		ApplyService applyService = new ApplyService();
		List<Parent> parents = applyService.getParentFriendByParentId(Integer.parseInt(parentId));
		Gson gson = new Gson();
		String parentsString = null;
		if(parents!=null){
			parentsString = gson.toJson(parents);
		}
		
		PrintWriter writer = response.getWriter();
		writer.print(parentsString);
		
		if(writer!=null){
			writer.close();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
