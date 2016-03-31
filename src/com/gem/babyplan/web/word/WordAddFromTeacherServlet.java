package com.gem.babyplan.web.word;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.entity.Word;
import com.gem.babyplan.service.WordService;

/**
 * Servlet implementation class WordAddFromTeacherServlet
 */
@WebServlet("/WordAddFromTeacherServlet")
public class WordAddFromTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//添加一条记录
		String parentId=request.getParameter("parentId");
		String teacherNum=request.getParameter("teacherNum");
		String wordText=request.getParameter("text");
		String superWordId=request.getParameter("superId");
		System.out.println(parentId+"--"+teacherNum+"--"+wordText+"--"+superWordId);
		if(teacherNum!=null && superWordId!=null && wordText!=null && parentId!=null)
		{
		WordService ws = new WordService();
		Teacher t = new Teacher();
		t.setTeacherNumber(teacherNum);
		Parent p = new Parent();
		p.setParentId(Integer.parseInt(parentId));
		Word superWord = new Word();
		superWord.setWordId(Integer.parseInt(superWordId));
		Word ww = new Word();
		ww.setParent(p);
		ww.setTeacher(t);
		ww.setWord(superWord);
		ww.setWordText(wordText);
		ws.addWord2(ww);
		request.setAttribute("teacherNumber", teacherNum);
		request.getRequestDispatcher("/RefreshWordServlet").forward(request, response);
		}
		else
		{
			request.setAttribute("error", "留言失败了");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
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
