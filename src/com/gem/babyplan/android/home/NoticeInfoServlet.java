package com.gem.babyplan.android.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.ClassesService;
import com.gem.babyplan.service.NoticeService;
import com.gem.babyplan.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class HomeworkInfoServlet
 */
@WebServlet("/NoticeInfoServlet")
public class NoticeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String parentString = request.getParameter("parentString");
		Gson gson = new Gson();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		Student student = parent.getStudent();
		String studentNumber = student.getStudentNumber();
		
		StudentService studentService = new StudentService();
		Student student2 = studentService.getStudentByNum(studentNumber);
		System.out.println("student:"+student2);
		Classes classes=student2.getClasses();
		String classNumber=classes.getClassNumber();
		
		NoticeService noticeService=new NoticeService();
		List<Notice> notices=noticeService.getNoticeByClassNumber(classNumber);
		System.out.println(notices);
		String noticeString=null;
		Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if(notices!=null){
			noticeString=gson2.toJson(notices);
		}
		System.out.println("解析后"+noticeString);
		PrintWriter pWriter=response.getWriter();
		pWriter.print(noticeString);
		pWriter.flush();
		if(pWriter!=null){
			pWriter.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
