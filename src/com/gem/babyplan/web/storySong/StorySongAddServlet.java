package com.gem.babyplan.web.storySong;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class StorySongAddServlet
 */
@WebServlet("/StorySongAddServlet")
@MultipartConfig
public class StorySongAddServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String ssType =request.getParameter("ssType");
		String ssName=request.getParameter("ssName");
		String ssBrief=request.getParameter("ssBrief");
		System.out.println(ssType);
		
		Part part=request.getPart("ssFile");
		String fileName=part.getSubmittedFileName();
		//新建一个对象，储存数据，调用service层，储存数据
		StorySong ss = new StorySong();
		ss.setSsBrief(ssBrief);
		ss.setSsName(ssName);
		ss.setSsThumbnail("222");
		int type =Integer.parseInt(ssType);
		ss.setSsType(type);
		ss.setSsURL(fileName);
		StorySongService sss = new StorySongService();
		sss.addStorySong(ss);
		
		//写到路径中去,缺点是每次发布，就把原来的给清空了
		//判断是否存在那里
		if (type==0) {
			
			part.write(ConstantBabyPlan.SONG_FILE+fileName);
		}else {
			part.write(ConstantBabyPlan.STORY_FILE+fileName);
			
		}
		//重新连接
		request.getRequestDispatcher("/StorySongListServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
