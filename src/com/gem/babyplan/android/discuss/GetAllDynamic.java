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

	public static void main(String[] args) {

		ApplyService applyService = new ApplyService();
		DynamicService dynamicService = new DynamicService();

		int applyParentId = 5;

		List<Parent> parents = applyService.getParentFriendByParentId(applyParentId);

		Integer[] parentIds = new Integer[parents.size() + 1];

		for (int i = 0; i < parents.size(); i++) {
			Parent parent = parents.get(i);
			parentIds[i] = parent.getParentId();
		}
		parentIds[parents.size()] = applyParentId;

		// Dynamic dynamicKey = null;

		// List<Discuss> discusses = new ArrayList<Discuss>();
		// TreeMap<Dynamic, List<Discuss>> dynamic_discuss_Map = new
		// TreeMap<Dynamic, List<Discuss>>();
		TreeMap<Dynamic, List<Discuss>> dynamic_discuss_Map = new TreeMap<Dynamic, List<Discuss>>();

		List<Dynamic> dynamics = dynamicService.getDynamicByParentId(parentIds);

		for (Dynamic dynamic : dynamics) {
			DiscussService discussService = new DiscussService();
			int dynamicId = dynamic.getDynamicId();
			Dynamic dynamicKey = dynamicService.getDynamicByDynamicId(dynamicId);
			// System.out.println("-------------------------------------------------");
			// System.out.println(dynamicKey);
			// System.out.println("-------------------------------------------------");
			TreeMap<Integer, List<Discuss>> discussesMap = discussService.getAllSortedDiscuss(dynamicId);
			//
			List<Discuss> discusses = new ArrayList<Discuss>();
			discusses.clear();
			List<Discuss> discusses2 = discussService.convertMapToList(discussesMap);

			discusses.addAll(discusses2);
			// for (Discuss discuss : discusses) {
			// System.out.println(discuss);
			// }

			dynamic_discuss_Map.put(dynamicKey, discusses);
		}

		for (Map.Entry<Dynamic, List<Discuss>> dynamic_discuss : dynamic_discuss_Map.entrySet()) {
			Dynamic dynamicId1 = dynamic_discuss.getKey();
			 System.out.println("-------------------------------------------------");
			System.out.println(dynamicId1);
			 System.out.println("-------------------------------------------------");
			List<Discuss> discusses2 = dynamic_discuss.getValue();
			for (Discuss discuss : discusses2) {
				System.out.println(discuss);
			}
		}

	}
}
