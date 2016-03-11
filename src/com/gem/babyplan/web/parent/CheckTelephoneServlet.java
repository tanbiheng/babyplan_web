package com.gem.babyplan.web.parent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.entity.Parent;

/**
 * Servlet implementation class CheckTelephoneServlet
 */
@WebServlet("/CheckTelephoneServlet")
//检测家长的手机号是否唯一
public class CheckTelephoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String telephone=request.getParameter("num");
		System.out.println(telephone);
		//检测数据库中是否存在
		ParentDao pDao =new ParentDao();
		Parent parent =pDao.getParentByTelephone(telephone);
		if (parent!=null)
		{
			response.getWriter().write("<font color='#990000'>该手机号已经存在，请重新注册</font>");
			
		}else 
		{
			response.getWriter().write("<font color='#003300'>恭喜你，可以使用这个手机号</font>");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
