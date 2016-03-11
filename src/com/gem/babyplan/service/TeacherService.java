package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;
//于3月5号修改

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Teacher;

public class TeacherService {
	private TeacherDao dao = new TeacherDao();
	private ClassesDao cDao = new ClassesDao();
	
	public Teacher getTeacherByTeacherNum (String num)
	{
		return dao.getTeacherByTeacherNum(num);
	}
	
	//得到所有教师的信息,由于需要班级的名称，所以需要把班级取出来，储存到教师信息中
	public List<Teacher> getAllTeachers ()
	{
		List<Teacher> list =dao.getAllTeacher();
		List<Teacher> list2 = new ArrayList<>();
		for (Teacher teacher : list) 
		{
			//得到每个教师对象，根据教师的对象的班级号，得到班级的对象，储存到教师里面
			Classes classes = cDao.getClassesByClassNumber(teacher.getClasses().getClassNumber());
			teacher.setClasses(classes);
			list2.add(teacher);
			
		}
		return list2;
	}
	//删除教师
	public void deleteTeacher (String teacherNum)
	{
		dao.deleteParent(new String[]{teacherNum});
	}
	//增加教师
	public void addTeacher(Teacher teacher)
	{
		dao.addTeacher(teacher);
	}
	// 分页查询
			public List<Teacher> getPagedParent(int curPage, int pageSize)
			{
				List<Teacher> list =dao.getPageTeacher(curPage, pageSize);
				List<Teacher> list2 = new ArrayList<>();
				for (Teacher teacher : list) 
				{
					//得到每个教师对象，根据教师的对象的班级号，得到班级的对象，储存到教师里面
					Classes classes = cDao.getClassesByClassNumber(teacher.getClasses().getClassNumber());
					teacher.setClasses(classes);
					list2.add(teacher);
					
				}
				return list2;
			}
			
			
			public int getCount() {
				return dao.getTeacherNumber();
			}
	
}
