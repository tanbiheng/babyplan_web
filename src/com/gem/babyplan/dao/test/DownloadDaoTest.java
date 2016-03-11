package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.DownloadDao;
import com.gem.babyplan.entity.Download;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.PublicVideo;

public class DownloadDaoTest {
	private DownloadDao dao = new DownloadDao();
	
	//插入
	@Test
	public void saveTest(){
		Download download = new Download();
		
		PublicVideo publicVideo = new PublicVideo();
		publicVideo.setPublicId(1);
		PrivateVideo privateVideo = new PrivateVideo();
		privateVideo.setPrivateId(0);
		
		download.setPublicVideo(publicVideo);
		download.setPrivateVideo(privateVideo);
		dao.save(download);
	}
	
	//删除
	@Test
	public void deleteTest(){
		int[] downloadId = {1};
		dao.delete(downloadId);
	}
	
	//查询
	@Test
	public void selectAllTest(){
		List<Download> list = dao.selectAll();
		for (Download download : list) {
			System.out.println(download);
		}
	}
}
