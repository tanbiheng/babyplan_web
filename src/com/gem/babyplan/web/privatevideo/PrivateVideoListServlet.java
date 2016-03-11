package com.gem.babyplan.web.privatevideo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.service.PrivateVideoService;


@WebServlet("/PrivateVideoListServlet")
public class PrivateVideoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
//		String className = request.getParameter("className");
		
		PrivateVideoService privateVideoService = new PrivateVideoService() ;
		
		int records = privateVideoService.getPrivatVideoNumber();
		int pageSize = 1;
		int totalPage = 0;
		if((records%pageSize)==0){
			totalPage = records/pageSize;
		}else{
			totalPage = records/pageSize+1;
		}
		String tpage = request.getParameter("curPage");
		String goPage = request.getParameter("goPage");
		System.out.println("goPage="+goPage);
		int curPage = 1;
		if((goPage!=null)&&(!goPage.equals(""))){
			curPage = Integer.parseInt(goPage);
		}
		
		if(tpage!=null){
			curPage = Integer.parseInt(tpage);
		}
		
		List<PrivateVideo> privateVideos = privateVideoService.getPagePrivateVideo(curPage, pageSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("records", records);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("teacherNumber", teacherNumber);
		request.setAttribute("privateVideos", privateVideos);
		request.getRequestDispatcher("/page/video/privateList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
