package com.gem.babyplan.service;

import java.io.File;
import java.util.List;

import com.gem.babyplan.dao.PhotoDao;
import com.gem.babyplan.entity.Photo;

public class PhotoService {
	private PhotoDao dao = new PhotoDao();
	
	// 添加一张相片
	public void addPhoto(Photo photo) {
		dao.addPhoto(photo);
	}
	
	// 批量删除照片，以传入的照片的id数组
	public void deletePhoto(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			int photoId = ids[i];
			Photo photo = dao.getPhotoById(photoId);
			String photoURL = photo.getPhotoURL();//数据库取出的虚拟路径对应的URL
			String photoFileName = photoURL.substring(13);//截取照片文件名	
			String realPath = "D:/BabyBaby/"+photoFileName;
			File file = new File(realPath);
			file.delete();
		}
		dao.deletePhoto(ids);
	}
	
	// 更新一个照片的信息,照片发布时间已经发布，不需要修改,相片id不需要修改
	public void updatePhoto(Photo p) {
		dao.updatePhoto(p);
	}
	
	// 查找某一个照片，按主键id号查询,有则返回照片对象，没有返回null,成员变量只要给外键属性即可
	public Photo getPhotoById(int id) {
		return dao.getPhotoById(id);
	}
	
	// 返回所有照片的对象
	public List<Photo> getAllPhoto() {
		return dao.getAllPhoto();
	}
	
	// 根据相册id的得到照片
	public List<Photo> getPhotoByAlbumId(int albumId) {
		return dao.getPhotoByAlbumId(albumId);
	}
	
	// 实现照片分页查询
	public List<Photo> getPagePhoto(int curPage, int pageSize) {
		return dao.getPagePhoto(curPage, pageSize);
	}
	
	// 统计照片的总数
	public int getPhotoNumber() {
		return dao.getPhotoNumber();
	}
}
