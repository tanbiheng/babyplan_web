package com.gem.babyplan.service;

import java.io.File;
import java.util.List;

import com.gem.babyplan.dao.HomeworkDao;
import com.gem.babyplan.entity.Homework;

public class HomeworkService {
	private HomeworkDao dao = new HomeworkDao();

	// 插入
	public void save(Homework homework) {
		String hwType = homework.getHwType();
		switch (hwType) {
		case "1":
			hwType = "/babyresource/homework/homeworktype/hwtype1.png";
			break;
		case "2":
			hwType = "/babyresource/homework/homeworktype/hwtype2.png";
			break;
		case "3":
			hwType = "/babyresource/homework/homeworktype/hwtype3.png";
			break;
		default:
			break;
		}
		homework.setHwType(hwType);
		dao.save(homework);
	}

	// 删除,批量删除
	public void delete(int[] hwIds) {
		for (int i = 0; i < hwIds.length; i++) {
			int hwId = hwIds[i];
			Homework  homework = dao.getHomeworkByHomeworkId(hwId);
			String hwPicture = homework.getHwPicture();//数据库取出的虚拟路径对应的URL
			String pictureFileName = hwPicture.substring(22);//截取照片文件名	
			String realPath = "D:/BabyBaby/homework/"+pictureFileName;
			File file = new File(realPath);
			file.delete();
		}
		dao.delete(hwIds);
	}

	// 修改
	public void update(Homework homework) {
		String hwType = homework.getHwType();
		switch (hwType) {
		case "1":
			hwType = "/babyresource/homework/homeworktype/hwtype1.png";
			break;
		case "2":
			hwType = "/babyresource/homework/homeworktype/hwtype2.png";
			break;
		case "3":
			hwType = "/babyresource/homework/homeworktype/hwtype3.png";
			break;
		default:
			break;
		}
		homework.setHwType(hwType);
		dao.update(homework);
	}

	// 查询所有班级
	public List<Homework> selectAll() {
		return dao.selectAll();
	}

	// 根据班级号得到作业
	public List<Homework> getHomeworkByClassNumber(String classNumber) {
		return dao.getHomeworkByClassNumber(classNumber);
	}
	
	// 根据主键得到作业
	public Homework getHomeworkByHomeworkId(int hwId) {
		return dao.getHomeworkByHomeworkId(hwId);
	}
	
	// 分页查询
	public List<Homework> getPagedHomework(int curPage, int pageSize) {
		return dao.getPagedHomework(curPage, pageSize);
	}

	// 得到所有作业记录的个数
	public int getCount() {
		return dao.getCount();
	}
}
