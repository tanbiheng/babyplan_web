package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Course;
import com.gem.babyplan.exception.CourseRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class CourseDao {
	// 插入
	public void save(Course course) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into course(courseName)" + "values(?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, course.getCourseName());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] courseId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from course where courseId = ?";
			for (int i = 0; i < courseId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, courseId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Course course) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update course set courseName=? where courseId = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, course.getCourseName());
			prep.setInt(2, course.getCourseId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程修改方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程修改方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程修改方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有课程
	public List<Course> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from course order by courseId";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				list.add(course);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 根据课程主键得到课程
	public Course getCourseByCourseId(int courseId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from course where courseId=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, courseId);
			rs = prep.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据主键得到课程方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据主键得到课程方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据主键得到课程方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return course;
	}
	
	// 根据课程名的到课程
	public Course getCourseByCourseName(String courseName) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Course course = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from course where courseName=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, courseName);
			rs = prep.executeQuery();
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据课程名得到课程方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据课程名得到课程方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程根据课程名得到课程方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return course;
	}
	
	// 分页查询
	public List<Course> getPagedCourse(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from course order by courseId limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				list.add(course);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程分页查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程分页查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CourseRuntimeException("课程分页查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 获得所有课程的个数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from course";
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
