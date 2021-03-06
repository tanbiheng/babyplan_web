package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ApplyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/ChangeApplyStatus")
public class ChangeApplyStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String applyString = request.getParameter("apply");
		System.out.println(applyString);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Apply>(){}.getType();
		Apply apply = gson.fromJson(applyString, type);
		ApplyService service = new ApplyService();
		service.update(apply);
		
		Parent applyParent = apply.getApplyParent();
		Parent beApplyParent = apply.getBeApplyParent();
		Apply apply2 = new Apply();
		apply2.setApplyParent(beApplyParent);
		apply2.setBeApplyParent(applyParent);
		apply2.setApplyStatus(2);
		service.save(apply2);
		
		PrintWriter writer = response.getWriter();
		writer.println("接受");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
