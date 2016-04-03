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
import com.gem.babyplan.service.DynamicService;
import com.gem.babyplan.service.ParentsService;
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
	
	// 根据动态id得到所有的点赞
	public List<Click> getClickByDynamicId(int dynamicId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Click> list = new ArrayList<Click>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from click where dynamicId = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, dynamicId);
			rs = prep.executeQuery();
			Click click = null;
			DynamicService dynamicService = new DynamicService();
			ParentsService parentsService = new ParentsService();
			while(rs.next()){
				click = new Click();
				Parent parent = parentsService.getParentByParentId(rs.getInt("parentId"));
				Dynamic dynamic = dynamicService.getDynamicByDynamicId(rs.getInt("dynamicId"));
				
				click.setParent(parent);
				click.setDynamic(dynamic);
				click.setClickTime(rs.getDate("clickTime"));
				list.add(click);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	// 根据动态id和家长id得到点赞
	public Click getClickByDynamicIdAndParentId(int parentId , int dynamicId){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Click click = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from click where parentId = ? and dynamicId = ?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, parentId);
			prep.setInt(2, dynamicId);
			
			rs = prep.executeQuery();
			
			DynamicService dynamicService = new DynamicService();
			ParentsService parentsService = new ParentsService();
			if(rs.next()){
				click = new Click();
				Parent parent = parentsService.getParentByParentId(rs.getInt("parentId"));
				Dynamic dynamic = dynamicService.getDynamicByDynamicId(rs.getInt("dynamicId"));
				
				click.setParent(parent);
				click.setDynamic(dynamic);
				click.setClickTime(rs.getDate("clickTime"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return click;
	}
	
	// 得到所有动态id
	public List<Integer> getDynamicIdsFromClick(){
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select dynamicId from click group by dynamicId;";
			prep = conn.prepareStatement(sql);
			
			rs = prep.executeQuery();
			
			while(rs.next()){
				list.add(rs.getInt("dynamicId"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
