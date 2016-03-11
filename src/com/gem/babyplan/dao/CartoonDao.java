package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.exception.CartoonRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class CartoonDao {
	// 插入
	public void save(Cartoon cartoon) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into cartoon(cartoonName,cThumbnail,cartoonBrief)" + "values(?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, cartoon.getCartoonName());
			prep.setString(2, cartoon.getcThumbnail());
			prep.setString(3, cartoon.getCartoonBrief());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] cartoonId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from cartoon where cartoonId = ?";
			for (int i = 0; i < cartoonId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, cartoonId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Cartoon cartoon) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update cartoon set cartoonName=?,cThumbnail=?,cartoonBrief=? where cartoonId = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, cartoon.getCartoonName());
			prep.setString(2, cartoon.getcThumbnail());
			prep.setString(3, cartoon.getCartoonBrief());
			prep.setInt(4, cartoon.getCartoonId());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通修改方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通修改方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通修改方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有动画片
	public List<Cartoon> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Cartoon> list = new ArrayList<Cartoon>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from cartoon";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Cartoon cartoon = new Cartoon();
				cartoon.setCartoonId(rs.getInt("cartoonId"));
				cartoon.setCartoonName(rs.getString("cartoonName"));
				cartoon.setcThumbnail(rs.getString("cThumbnail"));
				cartoon.setCartoonBrief(rs.getString("cartoonBrief"));
				list.add(cartoon);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 动画片名模糊查询
	public List<Cartoon> getCartoonByCartoonName(String cartoonName) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Cartoon> list = new ArrayList<Cartoon>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from cartoon where cartoonName like ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, "%" + cartoonName + "%");
			rs = prep.executeQuery();
			while (rs.next()) {
				Cartoon cartoon = new Cartoon();
				cartoon.setCartoonId(rs.getInt("cartoonId"));
				cartoon.setCartoonName(rs.getString("cartoonName"));
				cartoon.setcThumbnail(rs.getString("cThumbnail"));
				cartoon.setCartoonBrief(rs.getString("cartoonBrief"));
				list.add(cartoon);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	// 动画片名根据id返回对象
	public Cartoon getCartoonByCartoonId(int cartoonId) 
	{
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Cartoon cartoon=null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from cartoon where cartoonId =?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, cartoonId);
			rs = prep.executeQuery();
			if (rs.next())
			{
				cartoon = new Cartoon();
				cartoon.setCartoonId(rs.getInt("cartoonId"));
				cartoon.setCartoonName(rs.getString("cartoonName"));
				cartoon.setcThumbnail(rs.getString("cThumbnail"));
				cartoon.setCartoonBrief(rs.getString("cartoonBrief"));
				return cartoon;
		  	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通模糊查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return null;
	}

	// 分页查询
	public List<Cartoon> getPagedCartoon(int curPage, int pageSize) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Cartoon> list = new ArrayList<Cartoon>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from Cartoon order by cartoonId limit ?,?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, (curPage - 1) * pageSize);
			prep.setInt(2, pageSize);
			rs = prep.executeQuery();
			while (rs.next()) {
				Cartoon cartoon = new Cartoon();
				cartoon.setCartoonId(rs.getInt("cartoonId"));
				cartoon.setCartoonName(rs.getString("cartoonName"));
				cartoon.setcThumbnail(rs.getString("cThumbnail"));
				cartoon.setCartoonBrief(rs.getString("cartoonBrief"));
				list.add(cartoon);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通分页查询方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通分页查询方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通分页查询方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 获得所有动画片的个数
	public int getCount() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) c from cartoon";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通得到动画个数方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通得到动画个数方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartoonRuntimeException("卡通得到动画个数方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return count;
	}
}
