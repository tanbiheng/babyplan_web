/**@Author 炳华儿
   创建时间：2016年2月8日 下午9:17:12
 * 
 */
package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.Parent_DownloadDao;
import com.gem.babyplan.dao.PrivateVideoDao;
import com.gem.babyplan.dao.PublicVideoDao;
import com.gem.babyplan.entity.Download;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Parent_Download;
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.entity.PublicVideo;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月8日 下午9:17:12 
* @parameter   
* @return 
*/
/**
 * @author Administrator
 *
 */
public class Parent_DownLoadTest 
{
	Parent_DownloadDao pdl = new Parent_DownloadDao();
	//添加一条记录
	@Test
	public void testAdd ()
	{
		Parent p = new ParentDao().getParentByParentId(6);
		Download d = new Download();
		d.setDownloadId(3);
		//PrivateVideo pVideo = new PrivateVideoDao().getVideoById(3);
		PublicVideo pVideo2 = new PublicVideoDao().getPublicVideoById(1);
		Parent_Download pDownload = new Parent_Download();
		//d.setPrivateVideo(pVideo);
		d.setPublicVideo(pVideo2);
		pDownload.setDownload(d);
		pDownload.setParent(p);
		pdl.add(pDownload);
		
	}
	//测试删除一个家长的多条记录
	@Test
	public void testDelete()
	{
		int [] ids ={2,3};
		pdl.deleteStation(6, ids);
		
	}
	//查找某个家长下载的所有视频
	@Test
	public void testGetAllByPId ()
	{
		
		List<Parent_Download> list =pdl.getDownByParent(6);
		for (Parent_Download parent_Download : list) {
			System.out.println(parent_Download);
			
		}
	}
	
	//根据家长的id和downloadid确定这一集是否已经下载
	@Test
	public void testGetAllByPIdAndDid ()
	{
		Parent_Download pd =pdl.getByParent(6, 4);
		System.out.println(pd);
		
	}
	

}
