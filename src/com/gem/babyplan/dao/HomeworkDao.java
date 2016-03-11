package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Homework;
import com.gem.babyplan.exception.HomeworkRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class HomeworkDao {
	// 插入
	public void save(Homework homework) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into homework(classNumber,hwType,hwTitle,hwExplain,hwPicture,hwPublishTime,hwEndTime,hwStatus)"
					+ "values(?,?,?,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, homework.getClasses().getClassNumber());
			prep.setString(2, homework.getHwType());
			prep.setString(3, homework.getHwTitle());
			prep.setString(4, homework.getHwExplain());
			prep.setString(5, homework.getHwPicture());
			prep.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			prep.setDate(7, new java.sql.Date(homework.getHwEndTime().getTime()));
			prep.setInt(8, homework.getHwStatus());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] hwId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from homework where hwId = ?";
			for (int i = 0; i < hwId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, hwId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Homework homework) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update homework set classNumber=?,hwType=?,hwTitle=?,hwExplain=?,hwPicture=?,hwPublishTime=?,hwEndTime=?,hwStatus=? where hwId = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, homework.getClasses().getClassNumber());
			prep.setString(2, homework.getHwType());
			prep.setString(3, homework.getHwTitle());
			prep.setString(4, homework.getHwExplain());
			prep.setString(5, homework.getHwPicture());
			prep.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			prep.setDate(7, new java.sql.Date(homework.getHwEndTime().getTime()));
			prep.setInt(8, homework.getHwStatus());
			prep.setInt(9, homework.getHwId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有作业
	public List<Homework> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Homework> list = new ArrayList<Homework>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select hwId,classNumber,hwType,hwTitle,hwExplain,hwPicture,hwPublishTime,hwEndTime,hwStatus from homework";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Homework homework = new Homework();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));

				homework.setHwId(rs.getInt("hwId"));
				homework.setClasses(classes);
				homework.setHwType(rs.getString("hwType"));
				homework.setHwTitle(rs.getString("hwTitle"));
				homework.setHwExplain(rs.getString("hwExplain"));
				homework.setHwPicture(rs.getString("hwPicture"));
				homework.setHwPublishTime(rs.getDate("hwPublishTime"));
				homework.setHwEndTime(rs.getDate("hwEndTime"));
				homework.setHwStatus(rs.getInt("hwStatus"));
				list.add(homework);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 根据班级号得到作业
	public List<Homework> getHomeworkByClassNumber(String classNumber) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Homework> list = new ArrayList<Homework>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select hwId,classNumber,hwType,hwTitle,hwExplain,hwPicture,hwPublishTime,hwEndTime,hwStatus from homework where classNumber=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, classNumber);
			rs = prep.executeQuery();
			while (rs.next()) {
				Homework homework = new Homework();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				
				homework.setHwId(rs.getInt("hwId"));
				homework.setClasses(classes);
				homework.setHwType(rs.getString("hwType"));
				homework.setHwTitle(rs.getString("hwTitle"));
				homework.setHwExplain(rs.getString("hwExplain"));
				homework.setHwPicture(rs.getString("hwPicture"));
				homework.setHwPublishTime(rs.getDate("hwPublishTime"));
				homework.setHwEndTime(rs.getDate("hwEndTime"));
				homework.setHwStatus(rs.getInt("hwStatus"));
				list.add(homework);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业根据班级号得到作业方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业根据班级号得到作业方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业根据班级号得到作业方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 根据主键得到作业
	public Homework getHomeworkByHomeworkId(int hwId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Homework homework = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select hwId,classNumber,hwType,hwTitle,hwExplain,hwPicture,hwPublishTime,hwEndTime,hwStatus from homework where hwId=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1,hwId);
			rs = prep.executeQuery();
			while (rs.next()) {
				homework = new Homework();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				
				homework.setHwId(rs.getInt("hwId"));
				homework.setClasses(classes);
				homework.setHwType(rs.getString("hwType"));
				homework.setHwTitle(rs.getString("hwTitle"));
				homework.setHwExplain(rs.getString("hwExplain"));
				homework.setHwPicture(rs.getString("hwPicture"));
				homework.setHwPublishTime(rs.getDate("hwPublishTime"));
				homework.setHwEndTime(rs.getDate("hwEndTime"));
				homework.setHwStatus(rs.getInt("hwStatus"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业修改方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业修改方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业修改方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return homework;
	}

	// 分页查询
	public List<Homework> getPagedHomework(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Homework> list = new ArrayList<Homework>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select hwId,classNumber,hwType,hwTitle,hwExplain,hwPicture,hwPublishTime,hwEndTime,hwStatus from homework order by hwId limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Homework homework = new Homework();
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));

				homework.setHwId(rs.getInt("hwId"));
				homework.setClasses(classes);
				homework.setHwType(rs.getString("hwType"));
				homework.setHwTitle(rs.getString("hwTitle"));
				homework.setHwExplain(rs.getString("hwExplain"));
				homework.setHwPicture(rs.getString("hwPicture"));
				homework.setHwPublishTime(rs.getDate("hwPublishTime"));
				homework.setHwEndTime(rs.getDate("hwEndTime"));
				homework.setHwStatus(rs.getInt("hwStatus"));
				list.add(homework);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业分页查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业分页查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业分页查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 得到所有作业记录的个数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from homework";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业得到所有作业个数方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业得到所有作业个数方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomeworkRuntimeException("作业得到所有作业个数方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return count;
	}
}
