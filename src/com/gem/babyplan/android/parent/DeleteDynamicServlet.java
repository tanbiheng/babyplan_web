package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.service.DiscussService;
import com.gem.babyplan.service.DynamicService;


@WebServlet("/DeleteDynamicServlet")
public class DeleteDynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dynamicId = request.getParameter("dynamicId");
		int[] dynamicIds = {Integer.parseInt(dynamicId)};
		DynamicService dynamicService = new DynamicService();
		DiscussService discussService = new DiscussService();
		List<Discuss> noSuperDiscussesList = discussService.getNoSuperIdDiscussByDynamicId(Integer.parseInt(dynamicId));
		for (Discuss discuss : noSuperDiscussesList) {
			int discussId = discuss.getDiscussId();
			discussService.delete(Integer.parseInt(dynamicId), discussId);
		}
		dynamicService.delete(dynamicIds);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
