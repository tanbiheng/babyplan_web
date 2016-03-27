package com.gem.babyplan.android.mine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.google.gson.Gson;


@WebServlet("/EditPwdServlet")
public class EditPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ParentTelePhone = request.getParameter("ParentTelePhone");
		String ParentPwd = request.getParameter("pwdNow");
		String newPwd = request.getParameter("newPwd");
		System.out.println(ParentPwd);
		ParentDao dao = new ParentDao();
		Parent  p =  dao.getParentByTelephone(ParentTelePhone);
		String pwd = p.getParentPwd();
		System.out.println(pwd);
		PrintWriter pw = response.getWriter();
		if(pwd.equals(ParentPwd)){	
			String address = p.getAddress();
			String photoUrl = p.getBackgroundPhoto();
			String parentHeaderUrl = p.getParentHeader();
			String parentName = p.getParentName();
			String parentNickName = p.getParentNickName();
			String parentSex = p.getParentSex();
			String parentTelePhone = p.getParentTelePhone();
			Student student = p.getStudent();
			p.setAddress(address);
			p.setBackgroundPhoto(photoUrl);
			p.setParentHeader(parentHeaderUrl);
			p.setParentName(parentName);
			p.setParentNickName(parentNickName);
			p.setParentPwd(newPwd);
			p.setParentSex(parentSex);
			p.setParentSex(parentSex);
			p.setParentTelePhone(parentTelePhone);
			p.setStudent(student);
			dao.updateParent(p);
			pw.write("true");
		}
		pw.flush();
		pw.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
