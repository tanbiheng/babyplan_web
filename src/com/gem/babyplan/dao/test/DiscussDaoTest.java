package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.gem.babyplan.dao.DiscussDao;
import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;

public class DiscussDaoTest {
	private DiscussDao dao = new DiscussDao();

	// 插入
	@Test
	public void saveTest() throws ParseException {
		Discuss discuss = new Discuss();

		Dynamic dynamic = new Dynamic();
		dynamic.setDynamicId(1);
		Parent parent = new Parent();
		parent.setParentId(1);
		Discuss discuss1 = new Discuss();
		discuss1.setDiscussId(0);

		discuss.setDiscussId(1);
		discuss.setDynamic(dynamic);
		discuss.setParent(parent);
		discuss.setDiscuss(discuss1);

		discuss.setDiscussText("评论内容1");
		discuss.setIsLast(1);

		dao.save(discuss);
	}

//	Discuss discuss ;
	
	// 刪除，批量刪除
	@Test
	public void deleteTest() {
		int discussId = 1;
		int dynamicId = 1;
		dao.delete(dynamicId,discussId);
	}
	
	
	// 修改
	@Test
	public void updateTest() throws ParseException {
		Discuss discuss = new Discuss();

		Dynamic dynamic = new Dynamic();
		dynamic.setDynamicId(4);
		Parent parent = new Parent();
		parent.setParentId(2);
		Discuss discuss1 = new Discuss();
		discuss1.setDiscussId(null);

		discuss.setDiscussId(30);
		discuss.setDynamic(dynamic);
		discuss.setParent(parent);
		discuss.setDiscuss(discuss1);

		discuss.setDiscussText("动态4的一级评论2--");
		discuss.setIsLast(0);

		dao.update(discuss);
	}
	
	// 查询所有评论
	@Test
	public void selectAllTest() {
		int dynamicId = 1;
		TreeMap<Integer, List<Discuss>> map = dao.getAllSortedDiscuss(dynamicId);
		
		for(Map.Entry<Integer, List<Discuss>> entry :  map.entrySet()){
			int father_id = entry.getKey();
			System.out.println(father_id);
			List<Discuss> discuss = entry.getValue();
			for (Discuss discuss2 : discuss) {
				System.out.println(discuss2);
			}
		}
	}
	
	@Test
	public void getDiscussByDiscussId(){
		int discussId = 1;
		Discuss discuss = dao.getDiscussByDiscussId(discussId);
		System.out.println(discuss);
	}
	
	@Test
	public void getDiscussByDiscussSuperId(){
		int discussSuperId = 1;
		List<Discuss> list = dao.getDiscussByDiscussSuperId(discussSuperId);
		for (Discuss discuss : list) {
			System.out.println(discuss);
		}
	}
	
	@Test
	public void getDiscussByDynamicIdTest(){
		int dynamicId = 5;
		List<Discuss> list = dao.getDiscussByDynamicId(dynamicId);
		System.out.println(list);
//		for (Discuss discuss : list) {
//			System.out.println(discuss);
//		}
	}
}
