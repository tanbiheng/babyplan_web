package com.gem.babyplan.android.cartoon;

import java.util.List;

import com.gem.babyplan.dao.WordDao;
import com.gem.babyplan.entity.Word;

public class Aa {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
     WordDao wDao = new WordDao();
     List<Word> list=wDao.getWordsBySuperId(1);
     for (Word word : list) {
		System.out.println(word);
	}
     
     List<Word> list2 = wDao.getPageParentThemeWord(1, 3, 1);
    /* for (Word word : list2) 
     {
 		System.out.println(word);
 	}*/
	}

}
