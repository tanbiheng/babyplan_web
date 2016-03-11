package com.gem.babyplan.web.parent;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.utils.ConstantBabyPlan;

/**
 * Servlet implementation class ParentAddServlet
 */
@WebServlet("/ParentAddServlet")
@MultipartConfig
public class ParentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//家长添加页面，有文件上传，需要多文件支持
		String studentNum=request.getParameter("studentNum");
		String parentName=request.getParameter("parentName");
		String parentTelePhone=request.getParameter("parentTelePhone");
		String parentPwd=request.getParameter("parentPwd");
		String parentSex=request.getParameter("parentSex");
		String parentNickName=request.getParameter("parentNickName");
		String address=request.getParameter("address");
		Part partHeader = request.getPart("parentHeader");
		Part partBack = request.getPart("backgroundPhoto");
		//判断一下
		if (studentNum!=null)
		{
			StudentDao sDao = new StudentDao();
			Student student =sDao.getStudentByNumber(studentNum);
			if (student!=null)
			{
				//得到文件名
				String parentHeader = partHeader.getSubmittedFileName();
				String parentBackground=partBack.getSubmittedFileName();
				Parent parent = new Parent();
				parent.setAddress(address);
				parent.setBackgroundPhoto(ConstantBabyPlan.PARENTS_BACKGROUND_URL+parentBackground);
				parent.setParentHeader(ConstantBabyPlan.PARENTS_HEADER_URL+parentHeader);
				parent.setParentName(parentName);
				parent.setParentNickName(parentNickName);
				parent.setParentPwd(parentPwd);
				parent.setParentSex(parentSex);
				parent.setParentTelePhone(parentTelePhone);
				parent.setStudent(student);
				ParentDao pDao = new ParentDao();
				pDao.addParent(parent);
				//将文件写入硬盘
				partBack.write(ConstantBabyPlan.PARENTS_BACKGROUND_FILE+parentBackground);
				partHeader.write(ConstantBabyPlan.PARENTS_HEADER_FILE+parentHeader);
				//返回家长展示压面
				request.getRequestDispatcher("/ParentsListServlet").forward(request, response);	
			}
		}
		else
		{
			request.setAttribute("error", "网络崩溃啦");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
