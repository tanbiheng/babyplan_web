package com.gem.babyplan.web.station;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.service.CartoonService;
import com.gem.babyplan.service.StationService;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class AddStationOfCartoonServlet
 * 这个页面为添加到某一部动画片里的某一集的页面
 */
@WebServlet("/AddStationOfCartoonServlet")
@MultipartConfig
public class AddStationOfCartoonServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartoonId = request.getParameter("loadLoaction");
		String whichStation=request.getParameter("whichStation");
		String ssBrief=request.getParameter("ssBrief");
		//String stationFile = request.getParameter("loadFile");
		//需要做的是按照卡通片的路径，存放这部动画片。先找到该动漫片
		CartoonService cService = new CartoonService();
		Cartoon cartoon =cService.getCartoonById(Integer.parseInt(cartoonId));
		//得到的上传文件
		Part part =request.getPart("loadFile");
		//得到拼接路径的字符串(写入硬盘的路径)
		String fileStr =ConstantBabyPlan.CARTOON_FILE+"/"+cartoon.getCartoonName()+"/"+part.getSubmittedFileName();
		System.out.println(fileStr);
		//写入硬盘
		part.write(fileStr);
		//拼接写入数据库的字符串
		String stationUrl =ConstantBabyPlan.CARTOON_URL+cartoon.getCartoonName()+"/"+part.getSubmittedFileName();
		//存进数据库
		Station station = new Station();
		station.setCatroon(cartoon);
		station.setStationBrief(ssBrief);
		station.setStationURL(stationUrl);
		station.setWhichStation(whichStation);
		StationService ss=new StationService();
		ss.addStation(station);
		
		System.out.println(stationUrl);
		//最后别忘了返回展示页面,进入list的页面的时候，应该带着id，要不不知道要展示那部动漫的集数
		request.setAttribute("id", cartoonId);
		request.getRequestDispatcher("/StationListServlet2").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
