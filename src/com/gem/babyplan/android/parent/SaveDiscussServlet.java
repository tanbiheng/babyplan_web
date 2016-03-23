package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.DiscussService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/SaveDiscussServlet")
public class SaveDiscussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentString = request.getParameter("parent");
		String discussText = request.getParameter("discussText");
		
		System.out.println(discussText);
		
		String superDiscuss = request.getParameter("superDiscuss");
		String dynamicString = request.getParameter("dynamic");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		System.out.println(parent);
		
		Type type1 = new TypeToken<Discuss>(){}.getType();
		Discuss discuss = gson.fromJson(superDiscuss, type1);
		System.out.println(discuss);
		
		Type type2 = new TypeToken<Dynamic>(){}.getType();
		Dynamic dynamic = gson.fromJson(dynamicString, type2);
		System.out.println(dynamic);
		
		discuss.setIsLast(1);
		DiscussService discussService = new DiscussService();
		discussService.update(discuss);
		
		Discuss discuss2 = new Discuss();
		discuss2.setDiscussText(discussText);
		discuss2.setParent(parent);
		discuss2.setIsLast(0);
		discuss2.setDiscuss(discuss);
		discuss2.setDynamic(dynamic);
		
		discussService.save(discuss2);
		
		String result = "发表成功";
		Gson gson2 = new Gson();
		String gsonString = gson2.toJson(result);
		PrintWriter writer = response.getWriter();
		writer.print(gsonString);
		if(writer!=null){
			writer.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
