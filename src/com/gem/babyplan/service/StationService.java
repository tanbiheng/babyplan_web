package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.StationDao;
import com.gem.babyplan.entity.Station;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月28日 下午9:33:10 
* @parameter   
* @return 
*/
//于3月2日修改
public class StationService 
{
	StationDao sDao = new StationDao(); 
	
	//每一集数的增删改查
	//添加到数据库
	public void addStation (Station station)
	{
		sDao.addStation(station);
	}
	
	
	
	//根据给的卡通id得到所有集数
	public List<Station> getStationByCartoonId (int id)
	{
		
		
		
		return sDao.getAllStationOfCartoon(id);
		
	}
	//返回所有的集数
	public int getStationNum ()
	{
		
		return sDao.getStationNumber();
	}
	//返回分页查询
	public List<Station> getPagedStations(int currentPage,int pageSize)
	{
		return sDao.getPageStation(currentPage, pageSize);
		
	}

}
