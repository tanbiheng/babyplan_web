package com.gem.babyplan.web.parent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.StudentService;

/**
 * Servlet implementation class CheckBabyUniqueServlet
 */
@WebServlet("/CheckBabyUniqueServlet")
public class CheckBabyUniqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//检测宝贝的学号是否存在以及是否被使用
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentNumber=request.getParameter("num");
		//检测数据库中是否存在
				StudentService service = new StudentService();
				Student student =service.getStudentByNum(studentNumber);
				if (student==null)
				{
					response.getWriter().write("<font color='#990000'>这个学生不存在，请重新输入正确的学生学号！</font>");
					
				}else {
					//有了学号，判断学号是否被占用
					ParentDao pDao = new ParentDao();
					String sex= pDao.getParentByParentId(studentNumber);
					if (sex!=null)
					{
						
						response.getWriter().write("<font color='#0000cc'>该学号的宝宝已经有家长对应了，请重新设置</font>");
					}else
					{
						response.getWriter().write("<font color='#003300'>恭喜你，可以使用这个学号</font>");
						
					}
					
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
