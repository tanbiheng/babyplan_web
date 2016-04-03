package com.gem.babyplan.service;

import java.util.List;

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
	
	// 根据动态id得到所有的点赞
	public List<Click> getClickByDynamicId(int dynamicId){
		return dao.getClickByDynamicId(dynamicId);
	}
	
	// 根据动态id和家长id得到点赞
	public Click getClickByDynamicIdAndParentId(int parentId , int dynamicId){
		return dao.getClickByDynamicIdAndParentId(parentId, dynamicId);
	}
	
	// 得到所有动态id
	public List<Integer> getDynamicIdsFromClick(){
		return dao.getDynamicIdsFromClick();
	}
}
