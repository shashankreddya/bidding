package com.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getCurentDate() {
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateObject = new java.sql.Date(calendar.getTime().getTime());
		return dateObject;
	}

	public static String sqlDateFormat(String fromString) {
		// fromString = "20-06-1908";
		SimpleDateFormat from = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		String mysqlString = null;
		try {
			date = from.parse(fromString);
			mysqlString = to.format(date); // 2014-02-01
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 01/02/2014
		return mysqlString;
	}
	
	public static String normalDateFormat(String fromString) {
		// fromString = "20-06-1908";
		SimpleDateFormat to = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		String mysqlString = null;
		try {
			date = from.parse(fromString);
			mysqlString = to.format(date); // 2014-02-01
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 01/02/2014
		return mysqlString;
	}

	public static boolean beforeDate(String stringDate) {
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date;
		try {
			date = to.parse(sqlDateFormat(stringDate));
			String newdate = to.format(date);
			String newdate1 = to.format(getCurentDate());
			int val = newdate.compareTo(newdate1);
			if (val > 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		DateUtil dd = new DateUtil();
		dd.sqlDateFormat("20/06/1908");
	}
}
