package com.gem.babyplan.web.publicvideo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.service.PublicVideoService;


@WebServlet(name = "PublicVideoDeleteServlet", urlPatterns = { "/PublicVideoDeleteServlet" })
public class PublicDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String[] publicIds = request.getParameterValues("publicId");
		int[] publicIds1 = new int[publicIds.length];
		if(publicIds!=null){
			for (int i = 0; i < publicIds.length; i++) {
				publicIds1[i] = Integer.parseInt(publicIds[i]);
			}
		}
		PublicVideoService publicVideoService = new PublicVideoService();
		publicVideoService.deletePublicVideo(publicIds1);
		
		request.getRequestDispatcher("/PublicVideoListServlet").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
