package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;

public class ParentsService {
	private ParentDao pDao = new ParentDao();
	StudentDao sDao = new StudentDao();

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
}
