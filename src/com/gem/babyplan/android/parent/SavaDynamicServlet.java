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

import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.DynamicService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/SavaDynamicServlet")
@MultipartConfig
public class SavaDynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dynamicText = request.getParameter("dynamicText");
//		System.out.println(dynamicText);
		String parentString = request.getParameter("parent");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
//		System.out.println(parent);
		
//		application/x-www-form-urlencoded
		
		String contentType = request.getContentType();
		
//		String contentType1 = contentType.split(";")[0];
//		System.out.println(contentType1);
		Dynamic dynamic = new Dynamic();
		
		if(!contentType.startsWith("application")){
			Part part = request.getPart("photo");
//			System.out.println(part);
			String photoName1 = null;
			String photoName = part.getSubmittedFileName();
			photoName1 = "/babyresource/dynamicphotoes/"+photoName;//存入数据库的地址
			part.write("D:/BabyBaby/dynamicphotoes/"+photoName);
			dynamic.setDynamicFile(photoName1);
		}else{
			dynamic.setDynamicFile(null);
		}
		

		dynamic.setDynamicText(dynamicText);
		dynamic.setParent(parent);
		
		
		DynamicService dynamicService = new DynamicService();
		dynamicService.save(dynamic);
		
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
