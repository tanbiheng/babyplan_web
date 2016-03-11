package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;

public class AlbumDaoTest {
	//插入
	@Test
	public void saveTest() throws ParseException{
		AlbumDao dao = new AlbumDao();
		Album album = new Album();
		Classes classes = new Classes();
		classes.setClassNumber("A02");
		album.setAlbumName("erbanxiangce");
		album.setClasses(classes);
		album.setAlbumDescribe("二班相册");
		album.setCoverPaper("二班相册封面URL1");
		dao.save(album);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		AlbumDao dao = new AlbumDao();
		int[] albumId = {1};
		dao.delete(albumId);
	}
	
	//修改
	@Test
	public void updateTest() throws ParseException{
		AlbumDao dao = new AlbumDao();
		Album album = new Album();
		Classes classes = new Classes();
		classes.setClassNumber("A03");
		album.setAlbumId(4);
		album.setAlbumName("二班相册123");
		album.setClasses(classes);
		album.setAlbumDescribe("二班相册");
		album.setCoverPaper("二班相册封面URL");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2016-02-03";
		album.setCreateTime(sdf.parse(date));
		dao.update(album);
	}
	
	//查询所有相册
	@Test
	public void selectAllTest(){
		AlbumDao dao = new AlbumDao();
		List<Album> list = dao.selectAll();
		for (Album album : list) {
			System.out.println(album+","+album.getClasses().getClassNumber());
		}
	}
	
	//分页查询
	@Test 
	public void getPagedAlbumTest(){
		AlbumDao dao = new AlbumDao();
		int curPage = 2;
		int pageSize = 1;
		List<Album> list = dao.getPagedAlbum(curPage, pageSize);
		for (Album album : list) {
			System.out.println(album+","+album.getClasses().getClassNumber());
		}
	}
	
	// 通过班级号得到相册
	@Test
	public void getAlbumByClassNumber() {
		String classNumber = "A03";
		AlbumDao dao = new AlbumDao();
		List<Album> list = dao.getAlbumByClassNumber(classNumber);
		for (Album album : list) {
			System.out.println(album);
		}
	}
	
	//获得所有相册的个数
	@Test
	public void getCountTest(){
		AlbumDao dao = new AlbumDao();
		System.out.println(dao.getCount());
	}
	
	//根据主键相册id获得相册信息
	@Test
	public void getAlbumByAlbumIdTest(){
		AlbumDao dao = new AlbumDao();
		int albumId = 2;
		Album album = dao.getAlbumByAlbumId(albumId);
		System.out.println(album+","+album.getClasses().getClassNumber());
	}
}
