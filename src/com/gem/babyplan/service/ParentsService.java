package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;

public class ParentsService {
	private ParentDao pDao = new ParentDao();
	private StudentDao sDao = new StudentDao();
	
	private ApplyService applyService = new ApplyService();
	private DynamicService dynamicService = new DynamicService();
	private DiscussService discussService = new DiscussService();
	

	// 展示页面,由于需要宝宝的信息，需要把宝宝找出来，发送出去
	public List<Parent> getAllParents() {
		List<Parent> list = pDao.getAllParent();
		List<Parent> list2 = new ArrayList<>();
		Student student = null;
		for (Parent parent : list) {
			student = sDao.getStudentByNumber(parent.getStudent().getStudentNumber());
			parent.setStudent(student);
			list2.add(parent);
		}
		return list2;
	}

	// 分页查询
	public List<Parent> getPagedParent(int curPage, int pageSize) {
		List<Parent> list = pDao.getPageParent(curPage, pageSize);
		List<Parent> list2 = new ArrayList<>();
		Student student = null;
		for (Parent parent : list) {
			student = sDao.getStudentByNumber(parent.getStudent().getStudentNumber());
			parent.setStudent(student);
			list2.add(parent);
		}
		return list2;
	}

	// 获得所有动画片的个数
	public int getCount() {
		return pDao.getParentNumber();
	}

	// 查找某个家长，根据唯一标识的手机号查询
	public Parent getParentByTelephone(String telephone) {
		return pDao.getParentByTelephone(telephone);
	}
	
	// 根据家长id的到家长及家长好友的所有动态和评论
	public Map<Integer, List<Discuss>> getAboutParentDynamic_DiscussByParentId(int applyParentId){
		Map<Integer, List<Discuss>> dynamic_discuss_Map = new TreeMap<Integer, List<Discuss>>();//动态及对应评论的map
		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);//根据家长id查找到的家长好友
		List<Discuss> discusses = new ArrayList<Discuss>();//根据父id查询到的评论map转换后的评论集合
		Integer[] parentIds = new Integer[parents.size()+1];//家长圈也能显示自己的动态，所以要把家长自己加上去
		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds);//家长加家长所有好友的动态集合
		
		
		for (int i = 0; i < parents.size(); i++) {
			Parent parent = parents.get(i);
			parentIds[i] = parent.getParentId();
		}
		parentIds[parents.size()] = applyParentId;
		
		for (Dynamic dynamic : dynamics) {
			int dynamicId = dynamic.getDynamicId();
//			System.out.println(dynamicId);
			TreeMap<Integer, List<Discuss>> discussesMap = discussService.getAllSortedDiscuss(dynamicId);
			discusses.clear();
			discusses = discussService.convertMapToList(discussesMap);
//			for (Discuss discuss : discusses) {
//				System.out.println(discuss);
//			}
			dynamic_discuss_Map.put(dynamicId, discusses);
		}
		return dynamic_discuss_Map;
	}
	
}
