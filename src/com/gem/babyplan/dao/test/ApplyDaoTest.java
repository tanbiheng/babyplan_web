package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ApplyDao;
import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.entity.Parent;

public class ApplyDaoTest {
	private ApplyDao dao = new ApplyDao();

	// 插入
	@Test
	public void saveTest() throws ParseException {
		Apply apply = new Apply();
		Parent applyParent = new Parent();
		applyParent.setParentId(1);
		Parent beApplyParent = new Parent();
		beApplyParent.setParentId(2);
		apply.setApplyParent(applyParent);
		apply.setBeApplyParent(beApplyParent);
		apply.setApplyText("申请内容1");
		apply.setApplyStatus(2);
		dao.save(apply);
	}

	// 刪除，批量刪除
	@Test
	public void deleteTest() {
		int[] applyId = {4};
		dao.delete(applyId);
	}

	// 修改
	@Test
	public void updateTest() throws ParseException {
		Apply apply = new Apply();
		Parent applyParent = new Parent();
		applyParent.setParentId(1);
		Parent beApplyParent = new Parent();
		beApplyParent.setParentId(2);
		apply.setApplyId(5);
		apply.setApplyParent(applyParent);
		apply.setBeApplyParent(beApplyParent);
		apply.setApplyText("申请内容");
		apply.setApplyStatus(2);
		dao.update(apply);
	}

	// 查询所有申请信息
	@Test
	public void selectAllTest() {
		List<Apply> list = dao.selectAll();
		for (Apply apply : list) {
			System.out.println(apply+","+apply.getBeApplyParent().getParentId()+","+apply.getApplyParent().getParentId());
		}
	}
	
	// 根据家长id 查出所有家长好友
	@Test
	public void getApplyByParentIdTest(){
		int applyParentId = 5;
		List<Apply> list = dao.getApplyByParentId(applyParentId);
		for (Apply apply : list) {
			System.out.println(apply+","+apply.getBeApplyParent().getParentId()+","+apply.getApplyParent().getParentId());
		}
	}
	
	@Test
	// 根据申请家长id 查出所有家长的申请记录
	public void getApplyByBeApplyParentId(){
		int beApplyParentId = 5;
		List<Apply> list = dao.getApplyByBeApplyParentId(beApplyParentId);
		for (Apply apply : list) {
			System.out.println(apply);
		}
	}
	
}
