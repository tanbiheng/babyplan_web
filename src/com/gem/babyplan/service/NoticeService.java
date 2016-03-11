package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.dao.NoticeDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	
	// 插入
	public void save(Notice notice) {
		dao.save(notice);
	}
	
	// 删除,批量删除
	public void delete(int[] noticeId) {
		dao.delete(noticeId);
	}
	
	// 修改
	public void update(Notice notice) {
		dao.update(notice);
	}
	
	// 查询所有通知
	public List<Notice> selectAll() {
		List<Notice> noticeList1 = dao.selectAll();
		List<Notice> noticeList2 = new ArrayList<Notice>();
		for (Notice notice : noticeList1) {
			Classes classes = notice.getClasses();
			String classNumber = classes.getClassNumber();
			ClassesService service = new ClassesService();
			Classes classes1 = service.getClassesByClassNumber(classNumber);
			notice.setClasses(classes1);
			noticeList2.add(notice);
		}
		return noticeList2;
	}
	
	// 根据班级号获得班级通知
	public List<Notice> getNoticeByClassNumber(String classNumber) {
		List<Notice> noticeList1 = dao.getNoticeByClassNumber(classNumber);
		List<Notice> noticeList2 = new ArrayList<Notice>();
		for (Notice notice : noticeList1) {
			Classes classes = notice.getClasses();
			String classNumber1 = classes.getClassNumber();
			ClassesService service = new ClassesService();
			Classes classes1 = service.getClassesByClassNumber(classNumber1);
			notice.setClasses(classes1);
			noticeList2.add(notice);
		}
		return noticeList2;
	}
	// 分页查询
	public List<Notice> getPagedNotice(int curPage, int pageSize) {
		List<Notice> noticeList1 = dao.getPagedNotice(curPage, pageSize);
		List<Notice> noticeList2 = new ArrayList<Notice>();
		for (Notice notice : noticeList1) {
			Classes classes = notice.getClasses();
			String classNumber1 = classes.getClassNumber();
			ClassesService service = new ClassesService();
			Classes classes1 = service.getClassesByClassNumber(classNumber1);
			notice.setClasses(classes1);
			noticeList2.add(notice);
		}
		return noticeList2;
	}
	
	// 获得所有班级通知的个数
	public int getCount() {
		return dao.getCount();
	}
	
	// 根据主键获得班级通知
	public Notice getNoticeByNoticeId(int noticeId){
		return dao.getNoticeByNoticeId(noticeId);
	}
	
}
