package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ClickDao;
import com.gem.babyplan.entity.Click;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;

public class ClickDaoTest {
	private ClickDao dao = new ClickDao();
	
	//插入
	@Test
	public void saveTest() throws ParseException{
		Click click = new Click();
		Parent parent = new Parent();
		parent.setParentId(1);
		Dynamic dynamic = new Dynamic();
		dynamic.setDynamicId(1);
		click.setParent(parent);
		click.setDynamic(dynamic);
		
		dao.save(click);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		int parentId = 1;
		int dynamicId = 1;
		dao.delete(parentId, dynamicId);
	}
	
	//得到点赞的个数
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}

	// 根据动态id得到点赞
	@Test
	public void getClickByDynamicIdTest(){
		int dynamicId = 1;
		List<Click> list = dao.getClickByDynamicId(dynamicId);
		for (Click click : list) {
			System.out.println(click);
		}
	}
	
	// 根据动态id和家长id 得到点赞
	@Test
	public void getClickByDynamicIdAndParentId(){
		int dynamicId = 1;
		int parentId = 1;
		Click click = dao.getClickByDynamicIdAndParentId(parentId, dynamicId);
		System.out.println(click);
	}
	
	@Test
	public void getDynamicIdsFromClickTest(){
		List<Integer> list = dao.getDynamicIdsFromClick();
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
}
