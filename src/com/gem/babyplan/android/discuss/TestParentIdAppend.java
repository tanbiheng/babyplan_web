package com.gem.babyplan.android.discuss;

import java.util.List;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.service.ApplyService;

public class TestParentIdAppend {
	public static void main(String[] args) {
//		Integer[] ParentId = {1,2,3,4};
//		StringBuffer sb = new StringBuffer();
//				
//		for (Integer integer : ParentId) {
//			sb = sb.append(String.valueOf(integer)+",");
// 		}
//		
//		String parentIdString = "("+sb.toString().substring(0, sb.toString().length()-1)+")";
//		
//		System.out.println(parentIdString);
		
		ApplyService service = new ApplyService();
		int applyParentId = 5;
		List<Parent> parents = service.getParentFriendByParentId(applyParentId);
		for (Parent parent : parents) {
			System.out.println(parent);
		}
	}
}
