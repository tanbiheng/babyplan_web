package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/ReturnParentServlet")
public class ReturnParentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentId = request.getParameter("parentId");
		System.out.println(parentId);
		ParentsService parentsService = new ParentsService();
		Parent parent = parentsService.getParentByParentId(Integer.parseInt(parentId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String parentString = null;
		if(parent!=null){
			parentString = gson.toJson(parent);
		}
		
		PrintWriter writer = response.getWriter();
		writer.println(parentString);
		writer.flush();
		if(writer!=null){
			writer.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
