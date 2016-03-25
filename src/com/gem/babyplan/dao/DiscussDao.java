package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.exception.DiscussRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class DiscussDao {

	private TreeMap<Integer, List<Discuss>> discusses = new TreeMap<Integer, List<Discuss>>();

	// 插入
	public void save(Discuss discuss) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into discuss(dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast)"
					+ "values(?,?,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setInt(1, discuss.getDynamic().getDynamicId());
			prep.setInt(2, discuss.getParent().getParentId());
			
			if (discuss.getDiscuss() == null) {
				prep.setNull(3, Types.INTEGER);
			} else {
				Integer discussSuperId = discuss.getDiscuss().getDiscussId();
				prep.setInt(3, discussSuperId);
			}
			prep.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			prep.setString(5, discuss.getDiscussText());
			prep.setInt(6, discuss.getIsLast());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(Integer dynamicId,Integer discussId) {
		Connection conn = null;
		PreparedStatement prep = null;
		List<Discuss> list = getDiscussByDiscussSuperId(discussId);

		try {
			conn = DBConnection.getConnection();
			String sql = "delete from discuss where discussId = ? and dynamicId = ?";

			prep = conn.prepareStatement(sql);
			prep.setInt(1, discussId);
			prep.setInt(2, dynamicId);

			if (list.size() == 0 && list != null) {
				prep.executeUpdate();
			} else {
				for (Discuss discuss : list) {
					delete(dynamicId,discuss.getDiscussId());
				}

				prep.executeUpdate();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	public TreeMap<Integer, List<Discuss>> getAllSortedDiscuss(Integer dynamicId) {
		getSortDiscuss(dynamicId, null);
		return discusses;
	}

	// 修改
	public void update(Discuss discuss) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			//update discuss set dynamicId=?,parentId=?,discussSuperId=?,discussPublishTime=?,discussText=?,isLast=? where discussId = ?
			// 2.sql语句
			String sql = "update discuss set dynamicId=?,parentId=?,discussSuperId=?,discussPublishTime=?,discussText=?,isLast=? where discussId = ?";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setInt(1, discuss.getDynamic().getDynamicId());
			prep.setInt(2, discuss.getParent().getParentId());
			
			if (discuss.getDiscuss() == null) {
				prep.setNull(3, Types.INTEGER);
			} else {
				Integer discussSuperId = discuss.getDiscuss().getDiscussId();
				prep.setInt(3, discussSuperId);
			}
			prep.setTimestamp(4, new Timestamp(discuss.getDiscussPublishTime().getTime()));
			prep.setString(5, discuss.getDiscussText());
			prep.setInt(6, discuss.getIsLast());
			prep.setInt(7, discuss.getDiscussId());
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}
	
	// 查询所有评论
	public void getSortDiscuss(Integer dynamicId, Integer discussSuperId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		String sql = null;
		List<Discuss> list = new ArrayList<Discuss>();
		try {
			conn = DBConnection.getConnection();
			if (discussSuperId == null) {
				sql = "select discussId,dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast from discuss where dynamicId = ? and discussSuperId is null order by discussPublishTime asc";
				prep = conn.prepareStatement(sql);
			} else {
				sql = "select discussId,dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast from discuss where dynamicId = ? and discussSuperId = ? order by discussPublishTime asc";
				prep = conn.prepareStatement(sql);
				prep.setInt(2, discussSuperId);
			}
			prep.setInt(1, dynamicId);
			rs = prep.executeQuery();

			Discuss discuss = null;
			Dynamic dynamic = null;
			Parent parent = null;
			Discuss discuss1 = null;
			ParentDao parentDao = new ParentDao();

			while (rs.next()) {
				discuss = new Discuss();
				dynamic = new Dynamic();
				dynamic.setDynamicId(rs.getInt("dynamicId"));
				parent = parentDao.getParentByParentId(rs.getInt("parentId"));
//				parent.setParentId(rs.getInt("parentId"));
				discuss1 = getDiscussByDiscussId(rs.getInt("discussSuperId"));
//				discuss1.setDiscussId(rs.getInt("discussSuperId"));

				discuss.setDiscussId(rs.getInt("discussId"));
				discuss.setDynamic(dynamic);
				discuss.setParent(parent);
				discuss.setDiscuss(discuss1);
				discuss.setDiscussPublishTime(rs.getTimestamp("discussPublishTime"));
				discuss.setDiscussText(rs.getString("discussText"));
				discuss.setIsLast(rs.getInt("isLast"));

				if (discuss.getIsLast() != 0) {
					getSortDiscuss(dynamicId, discuss.getDiscussId());
				}
				list.add(discuss);
			}
			
			if ((discuss.getDiscuss()) !=null && discuss.getDiscuss().getDiscussId() != null) {
				discusses.put(discuss.getDiscuss().getDiscussId(), list);
			} else {
				discusses.put(0, list);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
	}

	// 根据主键得到评论
	public Discuss getDiscussByDiscussId(Integer discussId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Discuss discuss = null;
		try {
			conn = DBConnection.getConnection();

			String sql = "select discussId,dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast from discuss where discussId = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, discussId);
			rs = prep.executeQuery();

			Dynamic dynamic = null;
			Parent parent = null;
			Discuss discuss1 = null;
			ParentDao parentDao = new ParentDao();

			while (rs.next()) {
				discuss = new Discuss();
				dynamic = new Dynamic();
				dynamic.setDynamicId(rs.getInt("dynamicId"));
				parent = parentDao.getParentByParentId(rs.getInt("parentId"));
				parent.setParentId(rs.getInt("parentId"));
				discuss1 = new Discuss();
				discuss1.setDiscussId(rs.getInt("discussSuperId"));

				discuss.setDiscussId(rs.getInt("discussId"));
				discuss.setDynamic(dynamic);
				discuss.setParent(parent);
				discuss.setDiscuss(discuss1);
				discuss.setDiscussPublishTime(rs.getTimestamp("discussPublishTime"));
				discuss.setDiscussText(rs.getString("discussText"));
				discuss.setIsLast(rs.getInt("isLast"));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return discuss;
	}

	// 根据父id得到评论
	public List<Discuss> getDiscussByDiscussSuperId(Integer discussSuperId) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Discuss discuss = null;
		List<Discuss> list = new ArrayList<Discuss>();
		try {
			conn = DBConnection.getConnection();

			String sql = "select discussId,dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast from discuss where discussSuperId = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, discussSuperId);
			rs = prep.executeQuery();

			Dynamic dynamic = null;
			Parent parent = null;
			Discuss discuss1 = null;

			while (rs.next()) {
				discuss = new Discuss();
				dynamic = new Dynamic();
				dynamic.setDynamicId(rs.getInt("dynamicId"));
				parent = new Parent();
				parent.setParentId(rs.getInt("parentId"));
				discuss1 = new Discuss();
				discuss1.setDiscussId(rs.getInt("discussSuperId"));

				discuss.setDiscussId(rs.getInt("discussId"));
				discuss.setDynamic(dynamic);
				discuss.setParent(parent);
				discuss.setDiscuss(discuss1);
				discuss.setDiscussPublishTime(rs.getTimestamp("discussPublishTime"));
				discuss.setDiscussText(rs.getString("discussText"));
				discuss.setIsLast(rs.getInt("isLast"));

				list.add(discuss);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DiscussRuntimeException("评论查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
	
	
	// 根据动态id得到评论
		public List<Discuss> getDiscussByDynamicId(Integer dynamicId) {
			Connection conn = null;
			PreparedStatement prep = null;
			ResultSet rs = null;
			Discuss discuss = null;
			List<Discuss> list = new ArrayList<Discuss>();
			try {
				conn = DBConnection.getConnection();

				String sql = "select discussId,dynamicId,parentId,discussSuperId,discussPublishTime,discussText,isLast from discuss where dynamicId = ?";
				prep = conn.prepareStatement(sql);
				prep.setInt(1, dynamicId);
				rs = prep.executeQuery();

				Dynamic dynamic = null;
				Parent parent = null;
				Discuss discuss1 = null;

				while (rs.next()) {
					discuss = new Discuss();
					dynamic = new Dynamic();
					dynamic.setDynamicId(rs.getInt("dynamicId"));
					parent = new Parent();
					parent.setParentId(rs.getInt("parentId"));
					discuss1 = new Discuss();
					discuss1.setDiscussId(rs.getInt("discussSuperId"));

					discuss.setDiscussId(rs.getInt("discussId"));
					discuss.setDynamic(dynamic);
					discuss.setParent(parent);
					discuss.setDiscuss(discuss1);
					discuss.setDiscussPublishTime(rs.getTimestamp("discussPublishTime"));
					discuss.setDiscussText(rs.getString("discussText"));
					discuss.setIsLast(rs.getInt("isLast"));

					list.add(discuss);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new DiscussRuntimeException("评论查询所有方法出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DiscussRuntimeException("评论查询所有方法出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new DiscussRuntimeException("评论查询所有方法出错");
			} finally {
				DBConnection.release(conn, prep, rs);
			}
			return list;
		}
	
	
}
