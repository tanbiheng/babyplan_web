package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.dao.ApplyDao;
import com.gem.babyplan.entity.Apply;
import com.gem.babyplan.entity.Parent;

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
	
	// 根据申请家长id得到申请家长的所有好友
	public List<Parent> getParentFriendByParentId(int applyParentId){
		List<Parent> parents = new ArrayList<Parent>();
		List<Apply> applies = dao.getApplyByParentId(applyParentId);
		for (Apply apply : applies) {
			Parent parent = apply.getBeApplyParent();
			parents.add(parent);
		}
		return parents;
	}
	
	// 根据家长id 查出所有家长的申请记录
	public List<Apply> getApplyByBeApplyParentId(int applyParentId){
		return dao.getApplyByBeApplyParentId(applyParentId);
	}
}
