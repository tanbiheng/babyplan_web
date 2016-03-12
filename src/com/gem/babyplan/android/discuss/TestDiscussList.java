package com.gem.babyplan.android.discuss;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.gem.babyplan.dao.DiscussDao;
import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.service.DiscussService;

public class TestDiscussList {

	private static DiscussService service = new DiscussService();

	public static List<Discuss> convertMapToList(TreeMap<Integer, List<Discuss>> map) {

		getConvertList(map, 0);
		return convertList;
	}

	static List<Discuss> convertList = new ArrayList<Discuss>();
	static DiscussService discussService = new DiscussService();

	public static void getConvertList(TreeMap<Integer, List<Discuss>> map, Integer discussId) {

		List<Discuss> list = map.get(discussId);
		if (list != null) {
			for (Discuss discuss : list) {
				convertList.add(discuss);
				discussId = discuss.getDiscussId();
				if (discuss.getIsLast() != 0) {
					getConvertList(map, discussId);
				} else {
					break;
				}
			}
		}
	}

	
	
	public static void main(String[] args) {
		int dynamicId = 1;
		TreeMap<Integer, List<Discuss>> map = service.getAllSortedDiscuss(dynamicId);

		List<Discuss> discusses = convertMapToList(map);
		for (Discuss discuss : discusses) {
			System.out.println(discuss);
		}
	}
}
