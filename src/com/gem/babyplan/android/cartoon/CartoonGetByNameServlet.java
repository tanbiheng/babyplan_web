package com.gem.babyplan.android.cartoon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.service.CartoonService;
import com.gem.babyplan.service.CartoonServiceInterface;
import com.gem.babyplan.service.StorySongService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CartoonGetByNameServlet")
public class CartoonGetByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//根据指定的动画片名称，返回所有数据。
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		System.out.println(name);
		CartoonServiceInterface csi = new CartoonService();
		HashMap<Cartoon, List<Station>> hashMap =csi.getAndroidNameCartoon(name);
		//返回json数据
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String songs = gson.toJson(hashMap);
		System.out.println(songs);
		PrintWriter pWriter =response.getWriter();
		pWriter.write(songs);
		pWriter.flush();
		pWriter.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
