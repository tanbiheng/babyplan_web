package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.entity.Classes;

public class ClassesService {
	private ClassesDao dao = new ClassesDao();

	// 插入
	public void save(Classes classes) {
		dao.save(classes);
	}

	// 刪除，批量刪除
	public void delete(String[] classNumber) {
		dao.delete(classNumber);
	}

	// 修改
	public void update(Classes classes) {
		dao.update(classes);
	}

	// 查询所有班级
	public List<Classes> selectAll() {
		return dao.selectAll();
	}

	// 根据主键班级号得到班级信息
	public Classes getClassesByClassNumber(String classNumber) {
		return dao.getClassesByClassNumber(classNumber);
	}
	
	// 根据班级名称得到班级
	public Classes getClassByClassName(String className){
		return dao.getClassByClassName(className);
	}
}
