package com.gem.babyplan.web.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.service.ClassesService;

/**
 * Servlet implementation class ClassOfStudentServlet
 */
@WebServlet("/ClassOfStudentServlet")
public class ClassOfStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//实现功能是添加学生，找它，得到所有班级，放在单选框中
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ClassesService cService = new ClassesService();
		List<Classes> list = cService.selectAll();
		request.setAttribute("list", list);
		System.out.println(list);
		request.getRequestDispatcher("/page/user/studentAdd.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
