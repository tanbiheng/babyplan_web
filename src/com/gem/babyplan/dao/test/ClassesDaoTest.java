package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.entity.Classes;

public class ClassesDaoTest {
	//插入
	@Test
	public void saveTest(){
		ClassesDao dao = new ClassesDao();
		Classes classes = new Classes();
		classes.setClassNumber("A03");
		classes.setClassName("三班");
		dao.save(classes);
	}
	
	//刪除，批量刪除
	@Test
	public void deleteTest(){
		ClassesDao dao = new ClassesDao();
		String[] classNumber = {"A01"};
		dao.delete(classNumber);
	}
	
	//修改
	@Test
	public void updateTest(){
		ClassesDao dao = new ClassesDao();
		Classes classes = new Classes();
		classes.setClassNumber("A01");
		classes.setClassName("一班");
		dao.update(classes);
	}
	
	//查询所有班级
	@Test
	public void selectAllTest(){
		ClassesDao dao = new ClassesDao();
		List<Classes> list = dao.selectAll();
		for (Classes classes : list) {
			System.out.println(classes);
		}
	}
	
	//根据主键班级号得到班级信息
	@Test
	public void getClassesByClassIdTest(){
		ClassesDao dao = new ClassesDao();
		String classNumber = "A01";
		Classes classes = dao.getClassesByClassNumber(classNumber);
		System.out.println(classes);
	}
	
	//根据主键班级号得到班级信息
	@Test
	public void getClassesByClassNameTest(){
		ClassesDao dao = new ClassesDao();
		String className = "三班";
		Classes classes = dao.getClassByClassName(className);
		System.out.println(classes);
	}
			
}
