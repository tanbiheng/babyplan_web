package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.CourseDao;
import com.gem.babyplan.entity.Course;

public class CourseService {
	private CourseDao dao = new CourseDao();
	
	// 插入
	public void save(Course course) {
		dao.save(course);
	}
	
	// 删除,批量删除
	public void delete(int[] courseId) {
		dao.delete(courseId);
	}
	
	// 修改
	public void update(Course course) {
		dao.update(course);
	}
	
	// 查询所有课程
	public List<Course> selectAll() {
		return dao.selectAll();
	}
	
	// 分页查询
	public List<Course> getPagedCourse(int curPage, int pageSize) {
		return dao.getPagedCourse(curPage, pageSize);
	}
	
	// 获得所有课程的个数
	public int getCount() {
		return dao.getCount();
	}
	
	// 根据课程主键得到课程
	public Course getCourseByCourseId(int courseId){
		return dao.getCourseByCourseId(courseId);
	}
	
	// 根据课程名的到课程
	public Course getCourseByCourseName(String courseName) {
		return dao.getCourseByCourseName(courseName);
	}
}
