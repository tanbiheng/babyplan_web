package com.gem.babyplan.web.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.TeacherService;
import com.gem.babyplan.utils.ConstantBabyPlan;

@WebServlet("/TeacherListServlet")
//教师端的展示列表
public class TeacherListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回所有教师信息
		TeacherService tService = new TeacherService();
		int currPage =1;
		String current=request.getParameter("curPage");
		if (current!=null) 
		{	
			currPage=Integer.parseInt(current);
			
		}
		//如果为null,说明第一次点击，设置为1，当前的页面已经解决
		//得到总的个数
		int count =tService.getCount();
		//判断总共多少页
		int total=0;
		if (count%ConstantBabyPlan.RECODE_SHOW_NUM==0)
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM;
		}else 
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM+1;
		}
		List<Teacher> list = tService.getPagedParent(currPage, ConstantBabyPlan.RECODE_SHOW_NUM);
		//System.out.println(list);
	    request.setAttribute("count", count);
	    request.setAttribute("pages",total);
	    request.setAttribute("page",currPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/user/teacherList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
