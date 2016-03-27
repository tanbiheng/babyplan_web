package com.gem.babyplan.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.dao.WordDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.entity.Word;

//留言表的服务层
public class WordService 
{
	private WordDao wDao = new WordDao();
	private ParentDao pDao = new ParentDao();
	private TeacherDao tDao = new TeacherDao();
	private ClassesDao cDao = new ClassesDao();
	//根据家长的id,以及分页的参数，查找所有主题
	public LinkedHashMap<Word, List<Word>> getWordHashMap (int curPage,int pageSize,int parentId)
	{
		LinkedHashMap<Word, List<Word>> hashMap = new LinkedHashMap<>();
		//我其实还想把每一个主题对应的子类都给找出来啊。
		List<Word> list = wDao.getPageParentThemeWord(curPage, pageSize, parentId);
		for (Word word : list) 
		{
			//这点之前要做很多工作，得到教师和家长的信息。
			Parent parent = pDao.getParentByParentId(word.getParent().getParentId());
			//需要都把值带过去么？不需要吧，数据冗余了这都！！，先想到这一步吧。
			Teacher teacher = tDao.getTeacherByTeacherNum(word.getTeacher().getTeacherNumber());
			word.setParent(parent);
			word.setTeacher(teacher);
			//这一步很重要，不能使用成员变量，否则每次都是引用那个同一个list，引起不必要的麻烦
			WordDao wDao = new WordDao();
			List<Word> list2=wDao.getWordsBySuperId(word.getWordId());
			//对每一个得到的值再进行迭代，重新赋值
			for (Word word2 : list2) 
			{
			Parent parent1 = pDao.getParentByParentId(word2.getParent().getParentId());
			Teacher teacher1 = tDao.getTeacherByTeacherNum(word2.getTeacher().getTeacherNumber());
			word2.setParent(parent1);
			word2.setTeacher(teacher1);	
			}
			hashMap.put(word, list2);
			
		}
		return hashMap;
		
	}
	
	public void addWord(Word word)
	{
		//对word里面的内容进行判断,说明是发起会话
		if(word.getWordId()==0 && word.getWord().getWordId()==0)
		{
			word.setWord(null);
		}
		//执行添加
		wDao.addWord(word);
		
	}

}
