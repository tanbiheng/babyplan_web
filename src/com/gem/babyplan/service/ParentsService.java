package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Teacher;

public class ParentsService {
	private ParentDao pDao = new ParentDao();
	private StudentDao sDao = new StudentDao();
	private TeacherDao tDao = new TeacherDao();
	private ClassesDao cDao = new ClassesDao();

	private ApplyService applyService = new ApplyService();
	private DynamicService dynamicService = new DynamicService();
//	private DiscussService discussService = new DiscussService();

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

	// 修改家长信息
	public void updateParent(Parent p) {
		pDao.updateParent(p);
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

	// 根据家长id得到家长及家长好友的所有动态和评论,分页
	public Map<Dynamic, List<Discuss>> getAboutParentDynamic_DiscussByParentId(int applyParentId,int curPage,int pageSize) {

		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);//根据家长id查找到的家长好友

		Integer[] parentIds = new Integer[parents.size() + 1];//家长圈也能显示自己的动态，所以要把家长自己加上去

		for (int i = 0; i < parents.size(); i++) {
			Parent parent = parents.get(i);
			parentIds[i] = parent.getParentId();
		}
		parentIds[parents.size()] = applyParentId;

		TreeMap<Dynamic, List<Discuss>> dynamic_discuss_Map = new TreeMap<Dynamic, List<Discuss>>();//动态及对应评论的map

		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds,curPage,pageSize);//家长加家长所有好友的动态集合

		for (Dynamic dynamic : dynamics) {
			DiscussService discussService = new DiscussService();
			int dynamicId = dynamic.getDynamicId();
			Dynamic dynamicKey = dynamicService.getDynamicByDynamicId(dynamicId);
			List<Discuss> judeDiscusses = discussService.getDiscussByDynamicId(dynamicId);
			List<Discuss> discusses = new ArrayList<Discuss>();
			if(judeDiscusses!=null&&judeDiscusses.size()!=0){
				TreeMap<Integer, List<Discuss>> discussesMap = discussService.getAllSortedDiscuss(dynamicId);
				discusses.clear();
				List<Discuss> discusses2 = discussService.convertMapToList(discussesMap);//根据父id查询到的评论map转换后的评论集合
				discusses.addAll(discusses2);
			}else{
				discusses=null;
			}

			dynamic_discuss_Map.put(dynamicKey, discusses);
		}

		return dynamic_discuss_Map;
	}

	// 根据某个家长id 得到家长所有的动态和评论
	public Map<Dynamic, List<Discuss>> getPersonDynamic_DiscussByParentId(int applyParentId,int curPage,int pageSize) {

//		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);//根据家长id查找到的家长好友

		Integer[] parentIds = {applyParentId};//家长圈也能显示自己的动态，所以要把家长自己加上去

//		for (int i = 0; i < parents.size(); i++) {
//			Parent parent = parents.get(i);
//			parentIds[i] = parent.getParentId();
//		}
//		parentIds[parents.size()] = applyParentId;

		TreeMap<Dynamic, List<Discuss>> dynamic_discuss_Map = new TreeMap<Dynamic, List<Discuss>>();//动态及对应评论的map

		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds,curPage,pageSize);//家长加家长所有好友的动态集合

		for (Dynamic dynamic : dynamics) {
			DiscussService discussService = new DiscussService();
			int dynamicId = dynamic.getDynamicId();
			Dynamic dynamicKey = dynamicService.getDynamicByDynamicId(dynamicId);
			List<Discuss> judeDiscusses = discussService.getDiscussByDynamicId(dynamicId);
			List<Discuss> discusses = new ArrayList<Discuss>();
			if(judeDiscusses!=null&&judeDiscusses.size()!=0){
				TreeMap<Integer, List<Discuss>> discussesMap = discussService.getAllSortedDiscuss(dynamicId);
				discusses.clear();
				List<Discuss> discusses2 = discussService.convertMapToList(discussesMap);//根据父id查询到的评论map转换后的评论集合
				discusses.addAll(discusses2);
			}else{
				discusses=null;
			}

			dynamic_discuss_Map.put(dynamicKey, discusses);
		}

		return dynamic_discuss_Map;
	}
	
	// 根据家长id  得到所有家长及家长好友的动态
	public List<Dynamic> getDynamicByParentId(int applyParentId,int curPage,int pageSize) {
		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);

		Integer[] parentIds = new Integer[parents.size() + 1];

		for (int i = 0; i < parents.size(); i++) {
			Parent parent = parents.get(i);
			parentIds[i] = parent.getParentId();
		}
		parentIds[parents.size()] = applyParentId;

		Dynamic dynamicKey = null;

		List<Discuss> discusses = new ArrayList<Discuss>();
		Map<Dynamic, List<Discuss>> dynamic_discuss_Map = new TreeMap<Dynamic, List<Discuss>>();

		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds,curPage,pageSize);
		
		return dynamics;
	}
   //跟据家长id获得对应教师的姓名即可，其他不需要
	public String getTeacherNumByParentId (int parentId)
	{
		Parent parent=pDao.getParentByParentId(parentId);
		Student student =sDao.getStudentByNumber(parent.getStudent().getStudentNumber());
		//Classes classes =cDao.getClassesByClassNumber(student.getClasses().getClassNumber());
		Teacher teacher =tDao.getTeacherByClassesNum(student.getClasses().getClassNumber());
		
		
	return teacher.getTeacherNumber();	
	}
}
