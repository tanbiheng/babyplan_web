package com.gem.babyplan.android.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.service.PrivateVideoService;
import com.gem.babyplan.service.PublicVideoService;
import com.gem.babyplan.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@WebServlet("/BabyVideoServlet")
public class BabyVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentString = request.getParameter("parent");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type type = new TypeToken<Parent>(){}.getType();
		Parent parent = gson.fromJson(parentString, type);
		System.out.println(parent);
		// 通过家长找到学生
		Student student = parent.getStudent();
		String studentNumber = student.getStudentNumber();
		// 由于家长对象里面的学生只有学生号 其他信息为空  所以必须先通过学生的学号找到这个学生
		StudentService studentService = new StudentService();
		Student student2 = studentService.getStudentByNum(studentNumber);
		//通过学生找到学生所在班级
		Classes classes = student2.getClasses();
		String classNumber = classes.getClassNumber();
		System.out.println(classNumber);
		
		// 只有星期一和星期五有视频  星期六星期天星期一看到的视频都是星期五的  所以需要对得到的当前日期进行处理
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		int i = calendar.get(Calendar.DAY_OF_WEEK);// 星期日i==1，星期六i==7
		String dateString = null;
		switch (i) {
		case 1://星期天
			calendar.add(Calendar.DAY_OF_YEAR, -2);
			Date date1 = calendar.getTime();
			dateString = sdf.format(date1);
			break;
		case 2://星期一
			calendar.add(Calendar.DAY_OF_YEAR, -3);
			Date date2 = calendar.getTime();
			dateString = sdf.format(date2);
			break;
		case 3://星期二
		case 4://星期三
		case 5://星期四
		case 6://星期五
		case 7://星期六
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Date date3 = calendar.getTime();
			dateString = sdf.format(date3);
			break;
		default:
			break;
		}
		System.out.println(dateString);
		
		PrivateVideoService privateVideoService = new PrivateVideoService();
		
		
		List<PrivateVideo> privateList = privateVideoService.getPrivateVideoByClassNumberAndTime(classNumber, dateString);
		System.out.println(privateList);
		
		Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String privateListString = null;
		if(!privateList.isEmpty()){
			privateListString = gson2.toJson(privateList);
		}
		
		PrintWriter writer = response.getWriter();
		writer.print(privateListString);
		writer.flush();
		if(writer!=null){
			writer.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
