package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Click;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.exception.ClickRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class ClickDao {
	// 插入
	public void save(Click click) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into click(parentId,dynamicId,clickTime)" + "values(?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setInt(1, click.getParent().getParentId());
			prep.setInt(2, click.getDynamic().getDynamicId());
			prep.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int parentId, int dynamicId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from click where parentId=? and dynamicId=?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, parentId);
			prep.setInt(2, dynamicId);
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}
	
	//得到点赞的总数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from click";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞得到点赞个数方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞得到点赞个数方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClickRuntimeException("点赞得到点赞个数方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return count;
	}

}
