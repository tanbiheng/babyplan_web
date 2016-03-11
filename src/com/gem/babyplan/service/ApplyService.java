package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.ApplyDao;
import com.gem.babyplan.entity.Apply;

public class ApplyService {
	private ApplyDao dao = new ApplyDao();

	// 插入
	public void save(Apply apply) {
		dao.save(apply);
	}
	
	// 删除,批量删除
	public void delete(int[] applyId) {
		dao.delete(applyId);
	}
	
	// 修改
	public void update(Apply apply) {
		dao.update(apply);
	}
	
	// 查询所有申请信息
	public List<Apply> selectAll() {
		return dao.selectAll();
	}
	
}
