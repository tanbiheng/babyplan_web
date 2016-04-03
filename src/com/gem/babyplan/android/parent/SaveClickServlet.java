package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Click;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ClickService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/SaveClickServlet")
public class SaveClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dynamicString = request.getParameter("dynamic");
		String parentString = request.getParameter("parent");
		System.out.println(dynamicString);
		System.out.println(parentString);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Dynamic>(){}.getType();
		Type type1 = new TypeToken<Parent>(){}.getType();
		Dynamic dynamic = gson.fromJson(dynamicString, type);
		Parent parent = gson.fromJson(parentString, type1);
		int dynamicId = dynamic.getDynamicId();
		int parentId = dynamic.getParent().getParentId();
		ClickService clickService = new ClickService();
		Click click = clickService.getClickByDynamicIdAndParentId(parentId, dynamicId);
		if(click==null){
			Click click2 = new Click();
			click2.setDynamic(dynamic);
			click2.setParent(parent);
			clickService.save(click2);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
