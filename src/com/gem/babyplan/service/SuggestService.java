package com.gem.babyplan.service;

import java.util.List;

import com.gem.babyplan.dao.SuggestDao;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.entity.Suggest;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年3月5日 下午7:46:43 
* @parameter   
* @return 
*/
public class SuggestService 
{
	SuggestDao sDao = new SuggestDao();
	//评论表的service层，实现家长的建议
	//增加一个建议
	public void addSuggest(Suggest suggest)
	{
		sDao.addSuggest(suggest);
	}
	//批量删除建议
	public void deleteSuggests(int[] ids)
	{
		sDao.deleteSuggest(ids);
	}
	//返回通知的集合
	public List<Suggest> getSuggestList()
	{
		
		return sDao.getAllSuggest();
	}
	//返回所有的集数
		public int getSuggestNum ()
		{
			
			return sDao.getSuggestNumber();
		}
		//返回分页查询
		public List<Suggest> getPagedSuggests(int currentPage,int pageSize)
		{
			return sDao.getPageSuggest(currentPage, pageSize);
			
		}
	

}
