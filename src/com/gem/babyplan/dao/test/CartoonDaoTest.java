package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.CartoonDao;
import com.gem.babyplan.entity.Cartoon;

public class CartoonDaoTest {
	private CartoonDao dao = new CartoonDao();

	// 插入
	@Test
	public void saveTest() {
		Cartoon cartoon = new Cartoon();
		cartoon.setCartoonName("熊出没1");
		cartoon.setcThumbnail("熊出没1缩略图");
		cartoon.setCartoonBrief("熊出没1简介");
		dao.save(cartoon);
	}

	// 删除，批量刪除
	@Test
	public void deleteTest() {
		int[] cartoonId = { 1 };
		dao.delete(cartoonId);
	}

	// 修改
	@Test
	public void updateTest() {
		Cartoon cartoon = new Cartoon();
		cartoon.setCartoonId(2);
		cartoon.setCartoonName("熊出没");
		cartoon.setcThumbnail("熊出没缩略图");
		cartoon.setCartoonBrief("熊出没简介");
		dao.update(cartoon);
	}

	// 查询所有动画片
	@Test
	public void selectAllTest() {
		List<Cartoon> list = dao.selectAll();
		for (Cartoon cartoon : list) {
			System.out.println(cartoon);
		}
	}

	// 动画片名模糊查询
	@Test
	public void getCartoonByCartoonNameTest(){
		String cartoonName = "没";
		List<Cartoon> list = dao.getCartoonByCartoonName(cartoonName);
		for (Cartoon cartoon : list) {
			System.out.println(cartoon);
		}
	}
	//根据动画id查询动画的对象
	@Test
	public void getCartoonByCartoonId ()
	{
		Cartoon cartoon=dao.getCartoonByCartoonId(9);
		System.out.println(cartoon);
		
	}
	
	//分页查询
	@Test
	public void getPagedCartoonTest(){
		int curPage = 2;
		int pageSize = 1;
		List<Cartoon> list = dao.getPagedCartoon(curPage, pageSize);
		for (Cartoon cartoon : list) {
			System.out.println(cartoon);
		}
	}
	
	// 获得所有动画片的个数
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}
}
