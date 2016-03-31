package com.gem.babyplan.android.parent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		discussService.delete(Integer.parseInt(dynamicId), null);
		dynamicService.delete(dynamicIds);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
