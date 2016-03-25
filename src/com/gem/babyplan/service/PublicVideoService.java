package com.gem.babyplan.service;

import java.io.File;
import java.util.List;

import com.gem.babyplan.dao.PublicVideoDao;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.PublicVideo;

public class PublicVideoService {
	private PublicVideoDao dao = new PublicVideoDao();
	
	public void addPublicVideo(PublicVideo pv) {
		dao.addPublicVideo(pv);
	}
	
	// 批量删除公共视频表，以传入的主键的id数组
	public void deletePublicVideo(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			int publicId = ids[i];
			PublicVideo publicVideo = dao.getPublicVideoById(publicId);
					
			String publicURL = publicVideo.getPublicVideoURL();//数据库取出的虚拟路径对应的URL
			String videoFileName = publicURL.substring(13);//截取照片文件名	
			String realPath = "D:/BabyBaby/"+videoFileName;
			File file = new File(realPath);
			file.delete();
			
			String publicThumbnail = publicVideo.getPublicThumbnail();
			String thumbnailFileName = publicThumbnail.substring(36);
			String realPath1 = "D:/BabyBaby/"+ thumbnailFileName;
			File file2 = new File(thumbnailFileName);
			file2.delete();
		}
		
		dao.deletePublicVideo(ids);
	}
	
	// 更新一个公共视频的信息,id不需要修改,发布时间不能修改
	public void updatePublicVideo(PublicVideo pv) {
		dao.updatePublicVideo(pv);
	}
	
	// 查找某一个公共视频，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public PublicVideo getPublicVideoById(int id) {
		return dao.getPublicVideoById(id);
	}
	
	// 返回所有公共视频的对象。
	public List<PublicVideo> getAllPublicVideo() {
		return dao.getAllPublicVideo();
	}
	
	// 根据日期得到所有公共视频
	public List<PublicVideo> getPublicVideoByTime(String date) {
		return dao.getPublicVideoByTime(date);
	}
	
	// 实现公共视频的分页查询
	public List<PublicVideo> getPagePublicVideo(int curPage, int pageSize) {
		return dao.getPagePublicVideo(curPage, pageSize);
	}
	
	// 统计班级视频的总数
	public int getPublicVideoNumber() {
		return dao.getPublicVideoNumber();
	}
	
}
