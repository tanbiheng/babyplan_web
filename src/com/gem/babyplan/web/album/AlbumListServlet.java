package com.gem.babyplan.web.album;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.service.AlbumService;
import com.gem.babyplan.service.ClassesService;


@WebServlet("/AlbumListServlet")
public class AlbumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacherNumber = request.getParameter("teacherNumber");
		String className = request.getParameter("className");
		
		System.out.println(className);
		
		ClassesService cservice = new ClassesService();
		List<Classes> classes = cservice.selectAll();
		Classes classes1 = cservice.getClassByClassName(className);
		String classNumber1 = classes1.getClassNumber();
		
		AlbumService albumService = new AlbumService();
		int count = albumService.getCount();
		
		int records = albumService.getCount();
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

		List<Album> albums = null;
		
		if(className==null||className.equals("全部班级")){
			albums = albumService.getPagedAlbum(curPage, pageSize);
			//notice = nservice.getPagedNotice(curPage, pageSize);
		}else{
			albums = albumService.getAlbumByClassNumber(classNumber1);
		}
		
		
		System.out.println(albums);
		request.setAttribute("teacherNumber", teacherNumber);
		System.out.println(teacherNumber);
		
		request.setAttribute("count", count);
		request.setAttribute("className", className);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("records", records);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("classes", classes);
		request.setAttribute("albums", albums);
		request.getRequestDispatcher("/page/classes/albumList.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
