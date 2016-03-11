package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gem.babyplan.dao.DiscussDao;
import com.gem.babyplan.entity.Discuss;

public class DiscussService {
	private DiscussDao dao = new DiscussDao();
	
	// 插入
	public void save(Discuss discuss) {
		dao.save(discuss);
	}
	
	
	Discuss discuss ;
	// 删除,批量删除
	public void delete(Integer discussId) {
		List<Integer> discusses = new ArrayList<Integer>();
		discuss = dao.getDiscussByDiscussId(discussId);
		while(discuss!=null){
			discusses.add(discussId);
			discussId = discuss.getDiscuss().getDiscussId();
			discuss = dao.getDiscussByDiscussId(discussId);
		}
		System.out.println(discusses);
		for (int i = (discusses.size()-1); i >= 0; i--) {
			dao.delete(i);
		}
	}
	
	public HashMap<Integer, List<Discuss>> getAllSortedDiscuss(Integer dynamicId){
		return dao.getAllSortedDiscuss(dynamicId);
	}
	
}
