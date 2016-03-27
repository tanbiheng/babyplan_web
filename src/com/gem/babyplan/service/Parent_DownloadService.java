package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.Parent_DownloadDao;
import com.gem.babyplan.entity.Parent_Download;

public class Parent_DownloadService {

	private Parent_DownloadDao pdDao=new Parent_DownloadDao();
	
	//查找某个家长下载的所有视频
	public List<Parent_Download> getDownByParent(int parentId){
		return pdDao.getDownByParent(parentId);
	}
	
	//根据家长的id和downloadid确定这一集是否已经下载，返回对象，无则返回null
	public Parent_Download getByParent(int parentId,int downloadId)
	{
		return pdDao.getByParent(parentId, downloadId);
	}
}
