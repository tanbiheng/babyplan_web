package com.gem.babyplan.web.word;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Word;
import com.gem.babyplan.service.WordService;

/**
 * 用于新增一条留言后，展示的页面
 */
@WebServlet("/RefreshWordServlet")
public class RefreshWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		WordService ws = new WordService();
		String teacherNumber=(String) request.getAttribute("teacherNumber");
		//得到教师的num
				if(teacherNumber==null)
				{
					request.setAttribute("error", "插入留言失败了");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}else
				{
					LinkedHashMap<Word, List<Word>> hashMap = new LinkedHashMap<>();
					//得到教师信息之后，调用数据库，取得所有家长与之发起的会话。
					List<Parent> list=ws.getParentByTeacherNum(teacherNumber);
					for (Parent parent : list)
					{
						LinkedHashMap<Word, List<Word>> hashMap2 = new LinkedHashMap<>();
						hashMap2 = ws.getWordHashMap(parent.getParentId());
						//将hashMap存进去
						hashMap.putAll(hashMap2);
						System.out.println(parent);
					}
					
					//得到这个大的hashMap，返回给要显示的jsp
					request.setAttribute("hashMap", hashMap);
					request.getRequestDispatcher("/page/word/wordList.jsp").forward(request, response);
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
