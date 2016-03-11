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


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentTelePhone = request.getParameter("parentTelePhone");
		String parentPwd = request.getParameter("parentPwd");
		
		ParentsService service = new ParentsService();
		
		Parent parent = service.getParentByTelephone(parentTelePhone);
		System.out.println(parent);
		Gson gson = new Gson();
		String result = null;
		
		if(parent!=null&&parentPwd.equals(parent.getParentPwd())){
			result =  gson.toJson(parent);
		}
		
		PrintWriter printWriter = response.getWriter();
		printWriter.print(result);
		printWriter.flush();
		if(printWriter!=null){
			printWriter.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
