package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.CourseDao;
import com.gem.babyplan.entity.Course;

public class CourseDaoTest {
	private CourseDao dao = new CourseDao();
	
	//插入
	@Test
	public void saveTest(){
		Course course = new Course();
		course.setCourseName("体育");
		dao.save(course);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		int[] courseId = {4,5};
		dao.delete(courseId);
	}
	
	//修改
	@Test
	public void updateTest(){
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("物理");
		dao.save(course);
	}
	
	//查询
	@Test
	public void selectAllTest(){
		List<Course> list = dao.selectAll();
		for (Course course : list) {
			System.out.println(course);
		}
	}
	
	
	// 根据课程名得到课程
	@Test
	public void getCourseByCourseNameTest(){
		String courseName = "数学";
		Course course = dao.getCourseByCourseName(courseName);
		System.out.println(course);
	}
	
	// 分页查询
	@Test
	public void getPagedCourseTest(){
		int curPage = 5;
		int pageSize = 1;
		List<Course> list = dao.getPagedCourse(curPage, pageSize);
		for (Course course : list) {
			System.out.println(course);
		}
	}
	
	// 获得所有课程的个数
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}
	
	@Test
	public void getCourseByCourseIdTest(){
		int courseId = 3;
		Course course = dao.getCourseByCourseId(courseId);
		System.out.println(course);
	}

}
