package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.exception.ApplyRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class ApplyDao {
	// 插入
	public void save(Apply apply) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into apply(applyParentId,beApplyParentId,applyText,applyStatus,applyTime)"
					+ "values(?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setInt(1, apply.getApplyParent().getParentId());
			prep.setInt(2, apply.getBeApplyParent().getParentId());
			prep.setString(3, apply.getApplyText());
			prep.setInt(4, apply.getApplyStatus());
			prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] applyId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from apply where applyId = ?";
			for (int i = 0; i < applyId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, applyId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Apply apply) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update apply set applyParentId=?,beApplyParentId=?,applyText=?,applyStatus=?,applyTime=? where applyId = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, apply.getApplyParent().getParentId());
			prep.setInt(2, apply.getBeApplyParent().getParentId());
			prep.setString(3, apply.getApplyText());
			prep.setInt(4, apply.getApplyStatus());
			prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			prep.setInt(6, apply.getApplyId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请修改方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请修改方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请修改方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有申请信息
	public List<Apply> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Apply> list = new ArrayList<Apply>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select applyId,applyParentId,beApplyParentId,applyText,applyStatus,applyTime from apply";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Apply apply = new Apply();
				apply.setApplyId(rs.getInt("applyId"));

				Parent applyParent = new Parent();
				applyParent.setParentId(rs.getInt("applyParentId"));
				Parent beApplyParent = new Parent();
				beApplyParent.setParentId(rs.getInt("beApplyParentId"));

				apply.setApplyParent(applyParent);
				apply.setBeApplyParent(beApplyParent);
				apply.setApplyText(rs.getString("applyText"));
				apply.setApplyStatus(rs.getInt("applyStatus"));
				apply.setApplyTime(rs.getTimestamp("applyTime"));
				list.add(apply);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplyRuntimeException("申请查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

}
