package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.HomeworkDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Homework;

public class HomeworkDaoTest {
	private HomeworkDao dao = new HomeworkDao();

	// 插入
	@Test
	public void saveTest() throws ParseException {
		Homework homework = new Homework();
		Classes classes = new Classes();
		classes.setClassNumber("A03");

		homework.setClasses(classes);
		homework.setHwType("作业类型图片地址");
		homework.setHwTitle("第二个作业标题");
		homework.setHwExplain("第二个作业说明");
		homework.setHwPicture("作业图片地址");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2016-02-03";

		homework.setHwEndTime(sdf.parse(date));
		homework.setHwStatus(0);
		dao.save(homework);
	}

	// 刪除，批量刪除
	@Test
	public void deleteTest() {
		int[] hwId = { 1, 2 };
		dao.delete(hwId);
	}

	// 修改
	@Test
	public void updateTest() throws ParseException {
		Homework homework = new Homework();
		Classes classes = new Classes();
		classes.setClassNumber("A03");

		homework.setHwId(1);
		homework.setClasses(classes);
		homework.setHwType("作业类型图片地址");
		homework.setHwTitle("第二个作业标题");
		homework.setHwExplain("第二个作业说明");
		homework.setHwPicture("作业图片地址");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2016-02-03";

		homework.setHwEndTime(sdf.parse(date));
		homework.setHwStatus(0);
		dao.update(homework);
		
	}

	// 查询
	@Test
	public void selectAllTest() {
		List<Homework> list = dao.selectAll();
		for (Homework homework : list) {
			System.out.println(homework + "," + homework.getClasses().getClassNumber());
		}
	}
	
	// 根据班级号得到作业
	@Test
	public void getHomeworkByClassNumber() {
		String classNumber = "A03";
		List<Homework> list = dao.getHomeworkByClassNumber(classNumber);
		for (Homework homework : list) {
			System.out.println(homework);
		}
	}
	
	
	// 根据主键得到作业
	@Test
	public void getHomeworkByHomeworkId() {
		int hwId = 1;
		Homework homework =dao.getHomeworkByHomeworkId(hwId);
		System.out.println(homework);
	}

	// 分页查询
	@Test
	public void getPagedHomeworkTest() {
		int curPage = 2;
		int pageSize = 1;
		List<Homework> list = dao.getPagedHomework(curPage, pageSize);
		for (Homework homework : list) {
			System.out.println(homework + "," + homework.getClasses().getClassNumber());
		}
	}

	// 得到所有作业记录的个数
	@Test
	public void getCountTest() {
		System.out.println(dao.getCount());
	}
}
