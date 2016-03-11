package com.gem.babyplan.web.cartoon;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.service.CartoonService;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class CartoonAddServlet
 * 增加一部动画片的部名
 */
@WebServlet("/CartoonAddServlet")
@MultipartConfig
public class CartoonAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("albumName");
		String describe=request.getParameter("albumDescribe");
		Part part=request.getPart("pic");
		//只能在这里面写，首先新建一个文件夹，名字命名为这部名称
		File file = new File(ConstantBabyPlan.CARTOON_FILE);
		File file2 = new File(file, name);
		file2.mkdir();
		//写缩略图进去
		String fileName = part.getSubmittedFileName();
		String path =file2.toString()+"\\"+fileName;
		part.write(path);
		Cartoon cartoon = new Cartoon();
		cartoon.setCartoonName(name);
		cartoon.setCartoonBrief(describe);
		//需要重新命名缩略图的url路径
		//我草，重新玩啊
		/*String path2 = path.substring(11);
		path2=path2.replaceAll("\\\\", "/");
		String urlPath ="/babyresource"+path2;*/
		String urlPath=ConstantBabyPlan.CARTOON_URL+name+"/"+fileName;
		cartoon.setcThumbnail(urlPath);
		CartoonService cs = new CartoonService();
		cs.save(cartoon);
		//操作完后，跳转到动画片页面的list展示页面
		request.getRequestDispatcher("/CarToonListServlet").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
