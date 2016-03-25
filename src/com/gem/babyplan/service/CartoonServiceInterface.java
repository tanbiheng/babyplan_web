package com.gem.babyplan.service;

import java.util.HashMap;
import java.util.List;

import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;

public interface CartoonServiceInterface {

	// 插入
	void save(Cartoon cartoon);

	// 删除,批量删除
	void delete(int[] cartoonId);

	// 修改
	void update(Cartoon cartoon);

	// 查询所有动画片
	List<Cartoon> selectAll();

	// 动画片名模糊查询
	HashMap<Cartoon, List<Station>> getCartoonByCartoonName(String cartoonName);

	// 分页查询
	List<Cartoon> getPagedCartoon(int curPage, int pageSize);

	// 获得所有动画片的个数
	int getCount();

	//获得卡通对象
	Cartoon getCartoonById(int id);

	//分页查询所有的卡通，填充数据，传给安卓端
	HashMap<Cartoon, List<Station>> getAndroidPagedCartoon(int curPage, int pageSize);

}