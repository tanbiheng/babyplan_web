package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Download;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.exception.DownloadRuntimeException;
import com.gem.babyplan.utils.DBConnection;

public class DownloadDao {
	// 插入
	public void save(Download download) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into download(publicId,privateId)" + "values(?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			int publicId = download.getPublicVideo().getPublicId();
			int privateId = download.getPrivateVideo().getPrivateId();
			if(publicId == 0){
				prep.setNull(1, Types.INTEGER);
			}else{
				prep.setInt(1, publicId);
			}
			if(privateId == 0){
				prep.setNull(2, Types.INTEGER);
			}else{
				prep.setInt(2, privateId);
			}
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载插入方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载插入方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载插入方法出错");
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

	// 删除,批量删除
	public void delete(int[] downloadId) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from download where downloadId = ?";
			for (int i = 0; i < downloadId.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setInt(1, downloadId[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载删除方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载删除方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载删除方法出错");
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有下载
	public List<Download> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Download> list = new ArrayList<Download>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select downloadId,publicId,privateId from download";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Download download = new Download();
				
				PublicVideo publicVideo = new PublicVideo();
				publicVideo.setPublicId(rs.getInt("publicId"));
				PrivateVideo privateVideo = new PrivateVideo();
				privateVideo.setPrivateId(rs.getInt("privateId"));
				
				download.setDownloadId(rs.getInt("downloadId"));
				download.setPublicVideo(publicVideo);
				download.setPrivateVideo(privateVideo);
				list.add(download);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载查询所有方法出错");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载查询所有方法出错");
		} catch (IOException e) {
			e.printStackTrace();
			throw new DownloadRuntimeException("下载查询所有方法出错");
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}
}
