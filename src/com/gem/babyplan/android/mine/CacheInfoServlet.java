package com.gem.babyplan.android.mine;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Parent_Download;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.Parent_DownloadService;
import com.gem.babyplan.service.ParentsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/CacheInfoServlet")
public class CacheInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentString=request.getParameter("parentString");
		Gson gson=new Gson();
		Type type=new TypeToken<Parent>(){}.getType();
		Parent parent=gson.fromJson(parentString, type);
		int parentId=parent.getParentId();
		Parent_DownloadService pdService=new Parent_DownloadService();
		List<Parent_Download> lists=pdService.getDownByParent(parentId);

		System.out.println(lists);
		
		Gson gson2=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String result=null;
		if(lists!=null){
			result=gson2.toJson(lists);
		}
		PrintWriter pWriter=response.getWriter();
		pWriter.println(result);
		if(pWriter!=null){
			pWriter.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
