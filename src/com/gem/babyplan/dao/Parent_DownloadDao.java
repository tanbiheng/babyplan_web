package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Download;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Parent_Download;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.exception.ParentDownRunTimeException;
import com.gem.babyplan.exception.StationDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月8日 下午8:03:49 
* @parameter   
* @return 
*/
public class Parent_DownloadDao 
{

	//用户下载列表，多对多，增删改查
	public void add (Parent_Download pd)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into parent_download(parentId,downloadId,downloadTime) values(?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, pd.getParent().getParentId());
			pStatement.setInt(2, pd.getDownload().getDownloadId());
			pStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	
	//一次删除一个用户对应的所有的视频列表
	public void deleteStation (int parentId,int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from parent_download where parentId=? and downloadId=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, parentId);
			for (int id : ids)
			{
				pStatement.setInt(2, id);
				pStatement.executeUpdate();
				/*if (i>0) 
				{
					System.out.println("删除成功");
				}*/
			}
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	
	//不需要更新

	//查找某个家长下载的所有视频
	public List<Parent_Download> getDownByParent(int parentId)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Parent_Download> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select parentId,downloadId,downloadTime from parent_download where parentId=? order by downloadTime desc";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, parentId);
			rSet=pStatement.executeQuery();
			Parent_Download pd = null;
			list =new ArrayList<>();
			Parent p = null;
			Download d = null;
			while(rSet.next())
			{
				pd= new Parent_Download();
				p = new Parent();
				p.setParentId(rSet.getInt("parentId"));
				pd.setParent(p);
				d=new Download();
				d.setDownloadId(rSet.getInt("downloadId"));
				pd.setDownload(d);
				pd.setDownloadTime(rSet.getTimestamp("downloadTime"));
			
				list.add(pd);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		
	
		return list;
		
	}
	
	//根据家长的id和downloadid确定这一集是否已经下载，返回对象，无则返回null
	public Parent_Download getByParent(int parentId,int downloadId)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		Parent_Download pd = null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select parentId,downloadId,downloadTime from parent_download where parentId=? and downloadId=? ";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, parentId);
			pStatement.setInt(2, downloadId);
			rSet=pStatement.executeQuery();
			
	
		
			if(rSet.next())
			{
				pd= new Parent_Download();
				Parent p = new Parent();
				p.setParentId(rSet.getInt("parentId"));
				pd.setParent(p);
				Download d =new Download();
				d.setDownloadId(rSet.getInt("downloadId"));
				pd.setDownload(d);
				pd.setDownloadTime(rSet.getTimestamp("downloadTime"));	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}

		return pd;
		
	}
	
		
	
	//查找所有家长下载的所有视频的记录
	public List<Parent_Download> getAll()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Parent_Download> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select parentId,downloadId,downloadTime from parent_download order by downloadTime desc";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			Parent_Download pd = null;
			list =new ArrayList<>();
			Parent p = null;
			Download d = null;
			while(rSet.next())
			{
				pd= new Parent_Download();
				p = new Parent();
				p.setParentId(rSet.getInt("parentId"));
				pd.setParent(p);
				d=new Download();
				d.setDownloadId(rSet.getInt("downloadId"));
				pd.setDownload(d);
				pd.setDownloadTime(rSet.getTimestamp("downloadTime"));
			
				list.add(pd);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentDownRunTimeException("用户下载列表dao层出错！");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		
	
		return list;
		
	}
	
}