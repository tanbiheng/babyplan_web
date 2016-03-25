package com.gem.babyplan.android.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.google.gson.Gson;


@WebServlet("/AlbumGetList")
public class AlbumGetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String parentTelePhone = request.getParameter("parentTelePhone");
		System.out.println(parentTelePhone);
		ParentDao parentDao = new ParentDao();
		StudentDao studentDao = new StudentDao();
		AlbumDao albumDao = new AlbumDao();
		Parent parent = parentDao.getParentByTelephone(parentTelePhone);
		//Student student  = parent.getStudent();
		Student student=studentDao.getStudentByNumber(parent.getStudent().getStudentNumber());
		//System.out.println(student.toString());
		ClassesDao classesDao = new ClassesDao();
		Classes classes = classesDao.getClassesByClassNumber(student.getClasses().getClassNumber());
		String className = student.getClasses().getClassNumber();
		System.out.println(className);
		List<Album> list = albumDao.getAlbumByClassNumber(className);
		Gson gson = new Gson();
		String jsondata = gson.toJson(list);
		System.out.println(jsondata);
		PrintWriter pw =response.getWriter();
		//System.out.println(jsondata);
		pw.write(jsondata);
		pw.flush();
		pw.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
