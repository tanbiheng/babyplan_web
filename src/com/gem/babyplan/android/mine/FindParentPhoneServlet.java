package com.gem.babyplan.android.mine;

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

@WebServlet("/FindParentPhoneServlet")
public class FindParentPhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentPhone=request.getParameter("parentPhone");
		ParentsService parentsService=new ParentsService();
		Parent parent = parentsService.getParentByTelephone(parentPhone);
		System.out.println(parent);
		Gson gson=new Gson();
		String result=null;
		if(parent!=null){
			result=gson.toJson(parent);
		}
		PrintWriter pWriter=response.getWriter();
		pWriter.println(result);
		pWriter.flush();
		if(pWriter!=null){
			pWriter.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
