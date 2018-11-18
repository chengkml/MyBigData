package com.ck.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class MyDateUtils extends DateUtils{
	
	public static final String[] DATE_FORMATS = new String[] {"yyyy-MM","yyyyMM","yyyy/MM","yyyyMMdd","yyyy-MM-dd","yyyy/MM/dd","yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss"};
	
	public static Date parseDate(String date) throws ParseException {
		if(StringUtils.isNotBlank(date)) {
			return DateUtils.parseDate(date, DATE_FORMATS);
		}else {
			return null;
		}
	}
}
