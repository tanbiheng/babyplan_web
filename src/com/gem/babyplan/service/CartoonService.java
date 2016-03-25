package com.gem.babyplan.service;

import java.util.HashMap;
import java.util.List;

import com.gem.babyplan.dao.CartoonDao;
import com.gem.babyplan.dao.StationDao;
import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Station;
//我修改的 2016年2月26日
public class CartoonService implements CartoonServiceInterface {
	private CartoonDao dao = new CartoonDao();
	private StationDao sDao = new StationDao();
	
	// 插入
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#save(com.gem.babyplan.entity.Cartoon)
	 */
	@Override
	public void save(Cartoon cartoon) 
	{
		//每添加一条记录，自动在cartoons生成一个文件夹，并统一使用缩略图。
		dao.save(cartoon);
	}
	
	// 删除,批量删除
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#delete(int[])
	 */
	@Override
	public void delete(int[] cartoonId) {
		dao.delete(cartoonId);
	}
	
	// 修改
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#update(com.gem.babyplan.entity.Cartoon)
	 */
	@Override
	public void update(Cartoon cartoon) 
	{
		dao.update(cartoon);
	}
	
	// 查询所有动画片
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#selectAll()
	 */
	@Override
	public List<Cartoon> selectAll()
	{
		return dao.selectAll();
	}
	
	// 动画片名模糊查询
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#getCartoonByCartoonName(java.lang.String)
	 */
	@Override
	public HashMap<Cartoon,List<Station>> getCartoonByCartoonName(String cartoonName) 
	{
		HashMap<Cartoon,List<Station>> hashMap = new HashMap<>();
		List<Cartoon> list = dao.getCartoonByCartoonName(cartoonName);
		for (Cartoon cartoon : list) 
		{
			//根据卡通id得到所有的集数对象
			List<Station> list2=sDao.getAllStationOfCartoon(cartoon.getCartoonId());
			hashMap.put(cartoon, list2);
			
		}
		return hashMap;
		
	}
	
	// 分页查询
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#getPagedCartoon(int, int)
	 */
	@Override
	public List<Cartoon> getPagedCartoon(int curPage, int pageSize) {
		return dao.getPagedCartoon(curPage, pageSize);
	}
	
	// 获得所有动画片的个数
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#getCount()
	 */
	@Override
	public int getCount() {
		return dao.getCount();
	}
	//获得卡通对象
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#getCartoonById(int)
	 */
	@Override
	public Cartoon getCartoonById (int id)
	{
		return dao.getCartoonByCartoonId(id);
	}
	
	//分页查询所有的卡通，填充数据，传给安卓端
	/* (non-Javadoc)
	 * @see com.gem.babyplan.service.CartoonServiceInterface#getAndroidPagedCartoon(int, int)
	 */
	@Override
	public HashMap<Cartoon,List<Station>> getAndroidPagedCartoon(int curPage, int pageSize) 
	{
		HashMap<Cartoon,List<Station>> hashMap = new HashMap<>();
		List<Cartoon> list = dao.getPagedCartoon(curPage, pageSize);
		for (Cartoon cartoon : list) 
		{
			//根据卡通id得到所有的集数对象
			List<Station> list2=sDao.getAllStationOfCartoon(cartoon.getCartoonId());
			hashMap.put(cartoon, list2);
			
		}
		return hashMap;
	}
	
}
