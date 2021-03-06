package com.gem.babyplan.android.mine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ParentsService;

@WebServlet("/ResetPwdServlet")
public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentPhone=request.getParameter("parentPhone");
		String newPwd=request.getParameter("newPwd");
		
		ParentsService parentsService=new ParentsService();
		Parent parent=parentsService.getParentByTelephone(parentPhone);
		
		parent.setParentPwd(newPwd);
		System.out.println(parent);
		parentsService.updateParent(parent);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
