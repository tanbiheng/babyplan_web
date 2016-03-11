package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.DownloadDao;
import com.gem.babyplan.entity.Download;

public class DownloadService {
	private DownloadDao dao = new DownloadDao();
	
	// 插入
	public void save(Download download) {
		dao.save(download);
	}
	
	// 删除,批量删除
	public void delete(int[] downloadId) {
		dao.delete(downloadId);
	}
	
	// 查询所有下载
	public List<Download> selectAll() {
		return dao.selectAll();
	}
}
