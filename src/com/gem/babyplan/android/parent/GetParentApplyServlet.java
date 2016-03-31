package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.service.ApplyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/GetParentApplyServlet")
public class GetParentApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentId = request.getParameter("parentId");
		System.out.println(parentId);
		ApplyService applyService = new ApplyService();
		
		List<Apply> applys = applyService.getApplyByBeApplyParentId(Integer.parseInt(parentId));
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String applysString = null;
		if(applys!=null && applys.size()!=0){
			applysString = gson.toJson(applys);
		}
		
		PrintWriter writer = response.getWriter();
		writer.println(applysString);
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
