package com.gem.babyplan.android.discuss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) throws ParseException {
//		String tmp = "2001-09-02";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
//		try {
//			Date myDate = new Date();
//			Calendar myCalendar = Calendar.getInstance();
//			myCalendar.setTime(myDate);
//			int i = myCalendar.get(Calendar.DAY_OF_WEEK);
//			System.out.println(i);// 星期日i==1，星期六i==7
//			if (i == 1 || i == 7)
//				System.out.println("OK!");
//		} catch (Exception ex) {
//			System.out.println("Err");
//		}
		
//		Calendar myCalendar = Calendar.getInstance();
//		myCalendar.add(Calendar.DAY_OF_YEAR, 10);
//		Date date = myCalendar.getTime();
//		String dateString = sdf.format(date);
//		System.out.println(dateString);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = "2016-03-28";
		Date myDate = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		int i = calendar.get(Calendar.DAY_OF_WEEK);// 星期日i==1，星期六i==7
		String dateString = null;
		switch (i) {
		case 1://星期天
			calendar.add(Calendar.DAY_OF_YEAR, -2);
			Date date1 = calendar.getTime();
			dateString = sdf.format(date1);
			break;
		case 2://星期一
			calendar.add(Calendar.DAY_OF_YEAR, -3);
			Date date2 = calendar.getTime();
			dateString = sdf.format(date2);
			break;
		case 3://星期二
		case 4://星期三
		case 5://星期四
		case 6://星期五
		case 7://星期六
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Date date3 = calendar.getTime();
			dateString = sdf.format(date3);
			break;

		default:
			break;
		}
		
		System.out.println(dateString);
	
	}
}
