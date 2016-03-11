package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.Course_ClassDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.entity.Course_Class;

public class Course_ClassDaoTest {
	private Course_ClassDao dao = new Course_ClassDao();
	
	//插入
	@Test
	public void saveTest(){
		Classes classes = new Classes();
		classes.setClassNumber("A02");
		Course course = new Course();
		course.setCourseId(2);
		Course_Class course_class = new Course_Class();
		course_class.setClasses(classes);
		course_class.setCourse(course);
		course_class.setDayNumber("第三节");
		course_class.setWeekDay("星期二");
		dao.save(course_class);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		int[] courseClassId = {3};
		dao.delete(courseClassId);
	}
	
	//修改
	@Test
	public void updateTest(){
		Classes classes = new Classes();
		classes.setClassNumber("A03");
		Course course = new Course();
		course.setCourseId(7);
		Course_Class course_class = new Course_Class();
		course_class.setCourseClassId(1);
		course_class.setClasses(classes);
		course_class.setCourse(course);
		course_class.setDayNumber("第yi节");
		course_class.setWeekDay("星期天");
		dao.update(course_class);
		System.out.println(course_class);
	}
	
	//查询
	@Test
	public void selectAllTest(){
		List<Course_Class> list = dao.selectAll();
		for (Course_Class course_Class : list) {
			System.out.println(course_Class.getCourse().getCourseId()+","+
					course_Class.getClasses().getClassNumber());
		}
	}
	
	// 根据班级id查询班级所有课程
	@Test
	public void getCourse_ClassByClassNumberTest(){
		String classNumber = "A03";
		List<Course_Class> list = dao.getCourse_ClassByClassNumber(classNumber);
		for (Course_Class course_Class : list) {
			System.out.println(course_Class.getCourse().getCourseId()+","+
					course_Class.getClasses().getClassNumber());
		}
	}
	
	// 根据科目表id查询班级所有课程
	@Test
	public void getCourse_ClassByCourseClassIdTest() {
		int courseClassId = 5;
		Course_Class course_Class = dao.getCourse_ClassByCourseClassId(courseClassId);
		System.out.println(course_Class);
	}
	
	//	分页查询
	@Test
	public void getPagedCourse_ClassTest(){
		int curPage = 2;
		int pageSize = 1;
		List<Course_Class> list = dao.getPagedCourse_Class(curPage, pageSize);
		for (Course_Class course_Class : list) {
			System.out.println(course_Class);
		}
	}
	
	// 得到科目表记录的个数
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}
	
}
