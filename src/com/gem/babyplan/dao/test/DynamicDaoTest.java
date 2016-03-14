package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.DynamicDao;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;

public class DynamicDaoTest {
	private DynamicDao dao = new DynamicDao();
	
	//插入
	@Test
	public void saveTest() throws ParseException{
		Dynamic dynamic = new Dynamic();
		Parent parent = new Parent();
		parent.setParentId(1);
		
		dynamic.setParent(parent);
		dynamic.setDynamicText("第er条动态");
		dynamic.setDynamicFile(null);
		
		dao.save(dynamic);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		int[] dynamicId = {2};
		dao.delete(dynamicId);
	}
	
	//查询
	@Test
	public void selectAllTest(){
		List<Dynamic> list = dao.selectAll();
		for (Dynamic dynamic : list) {
			System.out.println(dynamic+","+dynamic.getParent().getParentId());
		}
	}

	// 根据家长id得到动态
	@Test
	public void getDynamicByParentId(){
		Integer[] parentIds = {1,2,5,6};
		List<Dynamic> list = dao.getDynamicByParentId(parentIds);
		for (Dynamic dynamic : list) {
			System.out.println(dynamic+","+dynamic.getParent().getParentId());
		}
	}
	
	//根据动态主键得到动态
	@Test
	public void getDynamicByDynamicIdTest(){
		int dynamicId = 1;
		Dynamic dynamic = dao.getDynamicByDynamicId(dynamicId);
		System.out.println(dynamic);
	}
}
