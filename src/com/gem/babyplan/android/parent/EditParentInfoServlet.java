package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/EditParentInfoServlet")
public class EditParentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentString=request.getParameter("parentString");
		Gson gson=new Gson();
		Type type=new TypeToken<Parent>(){}.getType();
		Parent parent=gson.fromJson(parentString, type);
		System.out.println(parent);
		ParentsService parentsService=new ParentsService();
		parentsService.updateParent(parent);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
