package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.DynamicDao;
import com.gem.babyplan.entity.Dynamic;

public class DynamicService {
	private DynamicDao dao = new DynamicDao();
	
	// 插入
	public void save(Dynamic dynamic) {
		dao.save(dynamic);
	}
	
	// 删除,批量删除
	public void delete(int[] dynamicId) {
		dao.delete(dynamicId);
	}
	
	// 查询所有班级
	public List<Dynamic> selectAll() {
		return dao.selectAll();
	}
}
