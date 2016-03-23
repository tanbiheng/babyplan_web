package com.gem.babyplan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.gem.babyplan.dao.DiscussDao;
import com.gem.babyplan.entity.Discuss;

public class DiscussService {
	private DiscussDao dao = new DiscussDao();
	private Discuss discuss ;
	List<Discuss> convertList = new ArrayList<Discuss>();
	
	// 插入
	public void save(Discuss discuss) {
		dao.save(discuss);
	}
	
	// 删除,批量删除
	public void delete(Integer dynamicId,Integer discussId) {
		dao.delete(dynamicId, discussId);
	}
	
	// 修改
	public void update(Discuss discuss) {
		dao.update(discuss);
	}
	
	// 根据动态id，得到对应动态评论的map集合
	public TreeMap<Integer, List<Discuss>> getAllSortedDiscuss(Integer dynamicId){
		return dao.getAllSortedDiscuss(dynamicId);
	}
	
	// 返回map转换后的评论list集合--> 可打包成json给安卓端使用
	public List<Discuss> convertMapToList(TreeMap<Integer, List<Discuss>> map)
	{
		if(map!=null){
			getConvertList(map, 0);
			return convertList;
		}
		return null;
	}

	// 把map转换成list
	public void getConvertList(TreeMap<Integer, List<Discuss>> map, Integer discussId) {
		List<Discuss> list = map.get(discussId);
		if (list != null) {
			for (Discuss discuss : list) {
				convertList.add(discuss);
				discussId = discuss.getDiscussId();
				if (discuss.getIsLast() != 0) {
					getConvertList(map, discussId);
				} else {
					continue;
				}
			}
		}
	}
	
	// 根据主键得到评论
	public Discuss getDiscussByDiscussId(Integer discussId) {
		return dao.getDiscussByDiscussId(discussId);
	}
	
	// 根据动态id得到评论
	public List<Discuss> getDiscussByDynamicId(Integer dynamicId) {
		return dao.getDiscussByDynamicId(dynamicId);
	}
}
