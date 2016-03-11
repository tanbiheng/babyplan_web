package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Course;
import com.gem.babyplan.entity.Course_Class;
import com.gem.babyplan.exception.CourseRuntimeException;
import com.gem.babyplan.exception.Course_ClassRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class Course_ClassDao {
	// 插入
	public void save(Course_Class course_class) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into course_class(classNumber,courseId,dayNumber,weekDay)" + "values(?,?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, course_class.getClasses().getClassNumber());
			prep.setInt(2, course_class.getCourse().getCourseId());
			prep.setString(3, course_class.getDayNumber());
			prep.setString(4, course_class.getWeekDay());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表插入方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表插入方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表插入方法出现出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] courseClassId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from course_class where courseClassId=?";
			for (int i = 0; i < courseClassId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, courseClassId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表删除方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表删除方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表删除方法出现出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Course_Class course_class) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update course_class set dayNumber=?,weekDay=?,courseId=?,classNumber = ? where courseClassId=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, course_class.getDayNumber());
			prep.setString(2, course_class.getWeekDay());
			prep.setInt(3, course_class.getCourse().getCourseId());
			prep.setString(4, course_class.getClasses().getClassNumber());
			prep.setInt(5, course_class.getCourseClassId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表修改方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表修改方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表修改方法出现出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有课程
	public List<Course_Class> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Course_Class> list = new ArrayList<Course_Class>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from Course_Class";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Course_Class course_class = new Course_Class();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course_class.setCourseClassId(rs.getInt("courseClassId"));
				course_class.setClasses(classes);
				course_class.setCourse(course);
				course_class.setDayNumber(rs.getString("dayNumber"));
				course_class.setWeekDay(rs.getString("weekDay"));
				list.add(course_class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表查询所有方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表查询所有方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表查询所有方法出现出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 根据班级名查询班级所有课程
	public List<Course_Class> getCourse_ClassByClassNumber(String classNumber) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Course_Class> list = new ArrayList<Course_Class>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from Course_Class where classNumber=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, classNumber);
			rs = prep.executeQuery();
			while (rs.next()) {
				Course_Class course_class = new Course_Class();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course_class.setCourseClassId(rs.getInt("courseClassId"));
				course_class.setClasses(classes);
				course_class.setCourse(course);
				course_class.setDayNumber(rs.getString("dayNumber"));
				course_class.setWeekDay(rs.getString("weekDay"));
				list.add(course_class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级名获得课程方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级名获得课程方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级名获得课程方法出现出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 根据科目表id查询班级所有课程
	public Course_Class getCourse_ClassByCourseClassId(int courseClassId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Course_Class course_class = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from Course_Class where courseClassId=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, courseClassId);
			rs = prep.executeQuery();
			while (rs.next()) {
				course_class = new Course_Class();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course_class.setCourseClassId(rs.getInt("courseClassId"));
				course_class.setClasses(classes);
				course_class.setCourse(course);
				course_class.setDayNumber(rs.getString("dayNumber"));
				course_class.setWeekDay(rs.getString("weekDay"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级id获得课程方法出现出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级id获得课程方法出现出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Course_ClassRuntimeException("科目表根据班级id获得课程方法出现出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return course_class;
	}
	
	// 分页查询
	public List<Course_Class> getPagedCourse_Class(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Course_Class> list = new ArrayList<Course_Class>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from course_class order by courseclassId limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Course_Class course_class = new Course_Class();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course_class.setCourseClassId(rs.getInt("courseClassId"));
				course_class.setClasses(classes);
				course_class.setCourse(course);
				course_class.setDayNumber(rs.getString("dayNumber"));
				course_class.setWeekDay(rs.getString("weekDay"));
				list.add(course_class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("科目表分页查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("科目表分页查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("科目表分页查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 获得所有科目的个数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from course_class";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程获得所有课程个数方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("班级获得所有课程个数方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("班级获得所有课程个数方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return count;
	}
}
