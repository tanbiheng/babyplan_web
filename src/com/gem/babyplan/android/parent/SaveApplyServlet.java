package com.gem.babyplan.android.parent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ApplyService;
import com.gem.babyplan.service.ParentsService;


@WebServlet("/SaveApplyServlet")
public class SaveApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String applyParentId = request.getParameter("applyParentId");
		String beApplyParentId = request.getParameter("beApplyParentId");
		String applyText = request.getParameter("applyText");
		
		System.out.println("提交的申请");
		
		ParentsService parentsService = new ParentsService();
		Parent applyParent = parentsService.getParentByParentId(Integer.parseInt(applyParentId));
		Parent beApplyParent = parentsService.getParentByParentId(Integer.parseInt(beApplyParentId));
		
		
		Apply apply = new Apply();
		apply.setApplyParent(applyParent);
		apply.setBeApplyParent(beApplyParent);
		apply.setApplyText(applyText);
		apply.setApplyStatus(0);
		
		ApplyService applyService = new ApplyService();
		applyService.save(apply);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
