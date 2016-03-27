package com.gem.babyplan.android.parent;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class AddWordFromAndroidServlet
 */
@WebServlet("/AddWordFromAndroidServlet")
public class AddWordFromAndroidServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到从安卓端传过来的数据
		String teacherNum=request.getParameter("teacherNum");
		String superId=request.getParameter("superId");
		String wordId=request.getParameter("wordId");
		String parentId=request.getParameter("parentId");
		String text =request.getParameter("text");
		System.out.println(teacherNum+"--"+superId+"--"+wordId+"-"+parentId);
		PrintWriter pw = response.getWriter();
		//进行判断，如果supr,word都是零，说明是发起的会话，只要两个数据即可，否则，就是针对某个留言的回复,这在服务层去实现吧，这里就不管了
		if(teacherNum!=null && superId!=null && wordId!=null && parentId!=null)
		{
			WordService ws = new WordService();
			Word word = new Word();
			Parent parent = new Parent();
			Teacher teacher = new Teacher();
			Word wordSuper = new Word();
			parent.setParentId(Integer.parseInt(parentId));
			teacher.setTeacherNumber(teacherNum);
			wordSuper.setWordId(Integer.parseInt(superId));
			word.setWordId(Integer.parseInt(wordId));
			word.setParent(parent);
			word.setTeacher(teacher);
			word.setWord(wordSuper);
			word.setWordText(text);
			ws.addWord(word);
			if(wordId.equals("0") && wordSuper.equals("0"))
			{
				pw.write("留言成功");
				pw.flush();
				pw.close();
				
			}else
			{
				pw.write("回复成功");
				pw.flush();
				pw.close();
			}
			
		}else
		{
			pw.write("数据有误");
			pw.flush();
			pw.close();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
