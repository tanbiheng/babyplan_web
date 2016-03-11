package com.gem.babyplan.web.storySong;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.StorySongDao;
import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.StorySongService;

/**
 * Servlet implementation class StorySongDeleteServlet
 */
//批量删除文件的servlet
@WebServlet("/StorySongDeleteServlet")
public class StorySongDeleteServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String  checkValues []=request.getParameterValues("chkss");
		//StorySongDao sDao = new StorySongDao();
		//将得到的数据转换成整形的数组,长度为转换过来字符串的长度
		int deleIds [] = new int[checkValues.length];
		for (int i = 0; i < checkValues.length; i++)
		{
			deleIds[i] =Integer.parseInt(checkValues[i]);
			//测试能否得到相应文件的记录
		//	StorySong s =sDao.getStorySongById(deleIds[i]);
		//	File file = new File(s.getSsURL());
		//	System.out.println(file.exists());
		//	System.out.println(deleIds[i]);
		}
		StorySongService sss = new StorySongService();
		sss.delBath(deleIds);
		
		//System.out.println("----------------");
		//File file = new File("1.txt");
		//System.out.println(file.exists());
		//调用数据库底层的方法，对要删除的每一个对象进行遍历，需要删除的，连他的在硬盘的内容也删除
		
		//最后调回list页面，也就是找list的servlet
		request.getRequestDispatcher("/StorySongListServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
