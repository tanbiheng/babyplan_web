package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.entity.Album;

public class AlbumService {
	private AlbumDao dao = new AlbumDao();

	// 插入
	public void save(Album album) {
		dao.save(album);
	}

	// 删除，批量删除
	public void delete(int[] albumId) {
		dao.delete(albumId);
	}

	//修改
	public void update(Album album) {
		dao.update(album);
	}
	
	// 查询所有相册
	public List<Album> selectAll() {
		return dao.selectAll();
	}
	
	//分页查询
	public List<Album> getPagedAlbum(int curPage, int pageSize) {
		return dao.getPagedAlbum(curPage, pageSize);
	}
	
	// 通过班级号得到相册
	public List<Album> getAlbumByClassNumber(String classNumber) {
		return dao.getAlbumByClassNumber(classNumber);
	}
	
	//获得所有相册的个数
	public int getCount() {
		return dao.getCount();
	}
	
	//根据主键相册id获得相册信息
	public Album getAlbumByAlbumId(int albumId) {
		return dao.getAlbumByAlbumId(albumId);
	}
}
