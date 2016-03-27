package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/ChangeParentBackground")
@MultipartConfig
public class ChangeParentBackground extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentString = request.getParameter("parent");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		
		
		Part part = request.getPart("photo");
		String photoName1 = null;
		String photoName = part.getSubmittedFileName();
		photoName1 = "/babyresource/parents/parentBackground/"+photoName;//存入数据库的地址
		part.write("D:/BabyBaby/parents/parentBackground/"+photoName);
		
		parent.setBackgroundPhoto(photoName1);
		ParentsService parentsService = new ParentsService();
		parentsService.updateParent(parent);
		
		String result = "发表成功";
		Gson gson2 = new Gson();
		String gsonString = gson2.toJson(result);
		PrintWriter writer = response.getWriter();
		writer.print(gsonString);
		writer.flush();
		if(writer!=null){
			writer.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
