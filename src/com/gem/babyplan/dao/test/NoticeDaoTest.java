package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.NoticeDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Notice;

public class NoticeDaoTest {
	private NoticeDao dao = new NoticeDao();
	
	//插入
	@Test
	public void saveTest() throws ParseException{
		Notice notice = new Notice();
		Classes classes = new Classes();
		classes.setClassNumber("A03");
		notice.setClasses(classes);
		notice.setNoticeText("第二条班级通知");
		
		dao.save(notice);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		int[] noticeId = {1};
		dao.delete(noticeId);
	}
	
	//修改
	@Test
	public void updateTest() throws ParseException{
		Notice notice = new Notice();
		Classes classes = new Classes();
		classes.setClassNumber("A03");
		
		notice.setNoticeId(1);
		notice.setClasses(classes);
		notice.setNoticeText("第san条班级通知");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		String date = "2016-02-03";
		notice.setNoticeTime(sdf.parse(date));
		dao.update(notice);
	}
	
	//查询
	@Test
	public void selectAllTest(){
		List<Notice> list = dao.selectAll();
		for (Notice notice : list) {
			System.out.println(notice+","+notice.getClasses().getClassNumber());
		}
	}
	
	// 分页查询
	@Test
	public void getPagedNoticeTest(){
		int curPage = 2;
		int pageSize = 1;
		List<Notice> list = dao.getPagedNotice(curPage, pageSize);
		for (Notice notice : list) {
			System.out.println(list+","+notice.getClasses().getClassNumber());
		}
	}
	
	// 得到所有班级通知的个数
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}
	
	// 根据主键的到班级通知
	@Test
	public void getNoticeByNoticeIdTest(){
		int noticeId = 1;
		Notice notice = dao.getNoticeByNoticeId(noticeId);
		System.out.println(notice);
	}
	
	// 根据班级号得到班级通知
	@Test
	public void getNoticeByClassNumberTest(){
		String classNumber = "A03";
		List<Notice> list = dao.getNoticeByClassNumber(classNumber);
		for (Notice notice : list) {
			System.out.println(notice);
		}
	}
}
