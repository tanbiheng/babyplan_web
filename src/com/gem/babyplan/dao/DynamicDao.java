package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.exception.DynamicRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class DynamicDao {
	// 插入
	public void save(Dynamic dynamic) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into dynamic(parentId,dynamicText,dynamicFile,dynamicPublishTime)" + "values(?,?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setInt(1, dynamic.getParent().getParentId());
			prep.setString(2, dynamic.getDynamicText());
			prep.setString(3, dynamic.getDynamicFile());
			prep.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] dynamicId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from dynamic where dynamicId = ?";
			for (int i = 0; i < dynamicId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, dynamicId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有动态
	public List<Dynamic> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Dynamic> list = new ArrayList<Dynamic>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select dynamicId,parentId,dynamicText,dynamicFile,dynamicPublishTime from dynamic";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Dynamic dynamic = new Dynamic();
				Parent parent = new Parent();
				parent.setParentId(rs.getInt("parentId"));
				
				dynamic.setDynamicId(rs.getInt("dynamicId"));
				dynamic.setParent(parent);
				dynamic.setDynamicText(rs.getString("dynamicText"));
				dynamic.setDynamicFile(rs.getString("dynamicFile"));
				dynamic.setDynamicPublishTime(rs.getTimestamp("dynamicPublishTime"));
				list.add(dynamic);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DynamicRuntimeException("动态查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
}
