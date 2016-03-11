package com.gem.babyplan.web.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.service.StudentService;
import com.gem.babyplan.service.TeacherService;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class StudentListServlet
 * 学生展示页面
 */
@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		StudentService service = new StudentService();
		int currPage =1;
		String current=request.getParameter("curPage");
		if (current!=null) 
		{	
			currPage=Integer.parseInt(current);
			
		}
		//如果为null,说明第一次点击，设置为1，当前的页面已经解决
		//得到总的个数
		int count =service.getCount();
		//判断总共多少页
		int total=0;
		if (count%ConstantBabyPlan.RECODE_SHOW_NUM==0)
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM;
		}else 
		{
			total=count/ConstantBabyPlan.RECODE_SHOW_NUM+1;
		}
		List<Student> list =service.getPagedStudent(currPage, ConstantBabyPlan.RECODE_SHOW_NUM);
		//System.out.println(list);
	    request.setAttribute("count", count);
	    request.setAttribute("pages",total);
	    request.setAttribute("page",currPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/page/user/studentList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
