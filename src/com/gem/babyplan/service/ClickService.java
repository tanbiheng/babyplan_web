package com.gem.babyplan.service;

import com.gem.babyplan.dao.ClickDao;
import com.gem.babyplan.entity.Click;

public class ClickService {
	private ClickDao dao = new ClickDao();
	
	// 插入
	public void save(Click click) {
		dao.save(click);
	}
	
	// 删除,批量删除
	public void delete(int parentId, int dynamicId) {
		dao.delete(parentId, dynamicId);
	}
	
	//得到点赞的总数
	public int getCount() {
		return dao.getCount();
	}
}
