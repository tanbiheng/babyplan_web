package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.PrivateVideoDao;
import com.gem.babyplan.entity.PrivateVideo;

public class PrivateVideoService {
	private PrivateVideoDao dao = new PrivateVideoDao();
	
	public void addPrivateVideo(PrivateVideo pv) {
		dao.addPrivateVideo(pv);
	}
	
	// 批量删除班级视频，以传入的视频的id数组为准
	public void deletePrivateVideo(int[] ids) {
		dao.deletePrivateVideo(ids);
	}
	
	// 更新一个班级视频的信息,发布时间已经发布，不需要修改,id不需要修改
	public void updatePrivateVideo(PrivateVideo pVideo) {
		dao.updatePrivateVideo(pVideo);
	}
	
	// 查找某一个视频，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public PrivateVideo getVideoById(int id) {
		return dao.getVideoById(id);
	}
	
	// 返回所有班级的视频，按照班级号排名
	public List<PrivateVideo> getAllPrivateVideo() {
		return dao.getAllPrivateVideo();
	}
	
	// 根据班级号和日期得到所有班级视频视频
	public List<PrivateVideo> getPrivateVideoByClassNumberAndTime(String classNumber, String date) {
		return dao.getPrivateVideoByClassNumberAndTime(classNumber, date);
	}
	
	// 实现班级视频分页查询，按班级号
	public List<PrivateVideo> getPagePrivateVideo(int curPage, int pageSize) {
		return dao.getPagePrivateVideo(curPage, pageSize);
	}
	
	// 统计班级视频的总数
	public int getPrivatVideoNumber() {
		return dao.getPrivatVideoNumber();
	}
}
