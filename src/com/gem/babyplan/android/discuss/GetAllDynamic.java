package com.gem.babyplan.android.discuss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gem.babyplan.dao.DiscussDao;
import com.gem.babyplan.entity.Discuss;
import com.gem.babyplan.entity.Dynamic;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ApplyService;
import com.gem.babyplan.service.DiscussService;
import com.gem.babyplan.service.DynamicService;

public class GetAllDynamic {
	static ApplyService applyService = new ApplyService();
	static DynamicService dynamicService = new DynamicService();
	static DiscussService discussService = new DiscussService();
	
	public static void main(String[] args) {
		int applyParentId = 5;
		
		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);
		
		Integer[] parentIds = new Integer[parents.size()+1];
		
		for (int i = 0; i < parents.size(); i++) {
			Parent parent = parents.get(i);
			parentIds[i] = parent.getParentId();
		}
		parentIds[parents.size()] = applyParentId;
		
		
		List<Discuss> discusses = new ArrayList<Discuss>();
		Map<Integer, List<Discuss>> dynamic_discuss_Map = new TreeMap<Integer, List<Discuss>>();
		
		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds);

		
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
		
//		for(Map.Entry<Integer, List<Discuss>> dynamic_discuss: dynamic_discuss_Map.entrySet()){
//			int dynamicId1 = dynamic_discuss.getKey();
//			System.out.println(dynamicId1);
//			List<Discuss> discusses2 = dynamic_discuss.getValue();
//			for (Discuss discuss : discusses2) {
//				System.out.println(discuss);
//			}
//		}
		
	}
}
