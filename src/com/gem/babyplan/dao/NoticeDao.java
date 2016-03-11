package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;
import com.gem.babyplan.exception.NoticeRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class NoticeDao {
	// 插入
	public void save(Notice notice) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into notice(classNumber,noticeText,noticeTime)" + "values(?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, notice.getClasses().getClassNumber());
			prep.setString(2, notice.getNoticeText());
			prep.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] noticeId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from notice where noticeId = ?";
			for (int i = 0; i < noticeId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, noticeId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}
	
	// 修改
	public void update(Notice notice) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update notice set classNumber=?,noticeText=?,noticeTime=? where noticeId = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, notice.getClasses().getClassNumber());
			prep.setString(2, notice.getNoticeText());
			prep.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			prep.setInt(4, notice.getNoticeId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知修改方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知修改方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知修改方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有通知
	public List<Notice> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select noticeId,classNumber,noticeText,noticeTime from notice";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeId(rs.getInt("noticeId"));
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				notice.setClasses(classes);
				notice.setNoticeText(rs.getString("noticeText"));
				notice.setNoticeTime(rs.getDate("noticeTime"));
				list.add(notice);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	// 根据主键获得班级通知
	public Notice getNoticeByNoticeId(int noticeId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Notice notice = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from notice where noticeId=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, noticeId);
			rs = prep.executeQuery();
			if (rs.next()) {
				notice = new Notice();
				notice.setNoticeId(rs.getInt("noticeId"));
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				notice.setClasses(classes);
				notice.setNoticeText(rs.getString("noticeText"));
				notice.setNoticeTime(rs.getDate("noticeTime"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据主键获得通知方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据主键获得通知方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据主键获得通知方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		
		return notice;
	}
	
	// 根据班级号获得班级通知
	public List<Notice> getNoticeByClassNumber(String classNumber) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from notice where classNumber=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, classNumber);
			rs = prep.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeId(rs.getInt("noticeId"));
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				notice.setClasses(classes);
				notice.setNoticeText(rs.getString("noticeText"));
				notice.setNoticeTime(rs.getDate("noticeTime"));
				list.add(notice);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据班级号获得通知方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据班级号获得通知方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知根据班级号获得通知方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 分页查询
	public List<Notice> getPagedNotice(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select noticeId,classNumber,noticeText,noticeTime from notice order by NoticeId limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeId(rs.getInt("noticeId"));
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				notice.setClasses(classes);
				notice.setNoticeText(rs.getString("noticeText"));
				notice.setNoticeTime(rs.getDate("noticeTime"));
				list.add(notice);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知分页查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知分页查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知分页查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 获得所有班级通知的个数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from notice";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知获得通知个数方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知获得通知个数方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoticeRuntimeException("班级通知获得通知个数方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return count;
	}
}
