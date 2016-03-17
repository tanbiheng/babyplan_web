package com.gem.babyplan.android.storysong;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;
import com.gem.babyplan.utils.ConstantBabyPlan;
import com.google.gson.Gson;

/**
 * Servlet implementation class SongPagedListServlet
 */
@WebServlet("/SongPagedListServlet")
public class SongPagedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到所有对象,由于默认的每页展示的页数是3，设置一个变量为当前页数
		StorySongService sss = new StorySongService();
		int currPage =1;
		String current=request.getParameter("curPage");
		if (current!=null) 
		{	
			currPage=Integer.parseInt(current);
			
		}
		//如果为null,说明第一次点击，设置为1，当前的页面已经解决
		//得到总的个数
		int count =sss.getTotalRecoders();
		//判断总共多少页
		int total=0;
		if (count%ConstantBabyPlan.RECODE_SHOW_NUM==0)
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM;
		}else 
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM+1;
		}
		//0是儿歌。
		List<StorySong> list =sss.getPagedStorySongByType(currPage, ConstantBabyPlan.RECODE_SHOW_NUM, 0);
		//System.out.println(list);
		Gson gson = new Gson();
		String songs = gson.toJson(list);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(songs);
		pWriter.flush();
		pWriter.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
