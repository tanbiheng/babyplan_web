package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/ChangeParentHeaderServlet")
@MultipartConfig
public class ChangeParentHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentString = request.getParameter("parent");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		
		
		Part part = request.getPart("header");
		String photoName1 = null;
		String photoName = part.getSubmittedFileName();
		photoName1 = "/babyresource/parents/parentHeader/"+photoName;//存入数据库的地址
		part.write("D:/BabyBaby/parents/parentHeader/"+photoName);
		
		parent.setParentHeader(photoName1);
		ParentsService parentsService = new ParentsService();
		parentsService.updateParent(parent);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
