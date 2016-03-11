package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.dao.Course_ClassDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.entity.Course_Class;

public class Course_ClassService {
	private Course_ClassDao dao = new Course_ClassDao();
	
	// 插入
	public void save(Course_Class course_class) {
		dao.save(course_class);
	}
	
	// 删除,批量删除
	public void delete(int[] courseClassId) {
		dao.delete(courseClassId);
	}
	
	// 修改
	public void update(Course_Class course_class) {
		dao.update(course_class);
	}
	
	// 查询所有课程
	public List<Course_Class> selectAll() {
		return dao.selectAll();
	}
	
	// 根据班级id查询班级所有课程
	public List<Course_Class> getCourse_ClassByClassNumber(String classNumber) {
		List<Course_Class> course_ClassList1 = dao.getCourse_ClassByClassNumber(classNumber);
		List<Course_Class> course_ClassList2 = new ArrayList<Course_Class>();
		for (Course_Class course_Class : course_ClassList1) {
			Classes classes = course_Class.getClasses();
			String classNumber1 = classes.getClassNumber();
			ClassesService service = new ClassesService();
			Classes classes1 = service.getClassesByClassNumber(classNumber1);
			CourseService courseService = new CourseService();
			int courseId = course_Class.getCourse().getCourseId();
			Course course = courseService.getCourseByCourseId(courseId);
			course_Class.setCourse(course);
			course_Class.setClasses(classes1);
			
			course_ClassList2.add(course_Class);
		}
		return course_ClassList2;
	}
	
	// 分页查询
	public List<Course_Class> getPagedCourse_Class(int curPage, int pageSize) {
		List<Course_Class> course_ClassList1 = dao.getPagedCourse_Class(curPage, pageSize);
		List<Course_Class> course_ClassList2 = new ArrayList<Course_Class>();
		for (Course_Class course_Class : course_ClassList1) {
			Classes classes = course_Class.getClasses();
			String classNumber = classes.getClassNumber();
			ClassesService service = new ClassesService();
			Classes classes1 = service.getClassesByClassNumber(classNumber);
			CourseService courseService = new CourseService();
			int courseId = course_Class.getCourse().getCourseId();
			Course course = courseService.getCourseByCourseId(courseId);
			course_Class.setCourse(course);
			course_Class.setClasses(classes1);
			
			course_ClassList2.add(course_Class);
		}
		return course_ClassList2;
	}
	
	// 根据科目表id查询班级所有课程
	public Course_Class getCourse_ClassByCourseClassId(int courseClassId) {
//		Course_Class course_Class1 = dao.getCourse_ClassByCourseClassId(courseClassId);
//		Course_Class course_Class2 = new Course_Class();
//
//		Classes classes = course_Class1.getClasses();
//		String classNumber = classes.getClassNumber();
//
//		ClassesService service = new ClassesService();
//		Classes classes1 = service.getClassesByClassNumber(classNumber);
//
//		CourseService courseService = new CourseService();
//		int courseId = course_Class1.getCourse().getCourseId();
//		Course course = courseService.getCourseByCourseId(courseId);
//		course_Class2.setCourse(course);
//		course_Class2.setClasses(classes1);

		return dao.getCourse_ClassByCourseClassId(courseClassId);
	}
	
	// 获得所有科目的个数
	public int getCount() {
		return dao.getCount();
	}
}
