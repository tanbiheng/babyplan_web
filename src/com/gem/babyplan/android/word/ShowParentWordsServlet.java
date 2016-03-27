package com.gem.babyplan.android.word;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Word;
import com.gem.babyplan.service.WordService;
import com.gem.babyplan.utils.ConstantBabyPlan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ShowParentWordsServlet
 */
@WebServlet("/ShowParentWordsServlet")
public class ShowParentWordsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//家长的id不可能为空的，不可能为空
		String parentId=request.getParameter("id");
		String curPage = request.getParameter("curPage");
		//默认首页
		if(curPage==null)
		{
			curPage="1";
		}
		WordService ws = new WordService();
		LinkedHashMap<Word, List<Word>> hashMap=ws.getWordHashMap(Integer.parseInt(curPage),ConstantBabyPlan.RECODE_SHOW_NUM, Integer.parseInt(parentId));
		//LinkedHashMap<Word, List<Word>> hashMap=ws.getWordHashMap(3,ConstantBabyPlan.RECODE_SHOW_NUM, 8);
		//有问题
		for (Map.Entry<Word, List<Word>> entry : hashMap.entrySet())
		{
			System.out.println("这是键");
			System.out.println(entry.getKey());
			System.out.println("这是值");
			for (Word word : entry.getValue())
			{
			System.out.println(word);	
			}
		}
		//取值到最后，变成{}返回去。
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String result=gson.toJson(hashMap);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(result);
		pWriter.flush();
		pWriter.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
