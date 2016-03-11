package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	//修改
	@Test
	public void getCountTest(){
		System.out.println(dao.getCount());
	}

}
