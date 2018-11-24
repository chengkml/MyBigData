package com.ck.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class MyDateUtils extends DateUtils{
	
	private static final ThreadLocal<SimpleDateFormat> localDate = new ThreadLocal<>();
	
	private static final ThreadLocal<SimpleDateFormat> localTime = new ThreadLocal<>();
	
	private static final String[] DATE_FORMATS = new String[] {"yyyy-MM","yyyyMM","yyyy/MM","yyyyMMdd","yyyy-MM-dd","yyyy/MM/dd","yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss"};
	
	public static Date parseDate(String date) throws ParseException {
		if(StringUtils.isNotBlank(date)) {
			return DateUtils.parseDate(date, DATE_FORMATS);
		}else {
			return null;
		}
	}
	
	public static String formatDate(Date date) {
		return getDateSdf().format(date);
	}
	
	public static String formatTime(Date date) {
		return getTimeSdf().format(date);
	}
	
	private static SimpleDateFormat getDateSdf() {
		SimpleDateFormat sdf = localDate.get();
		if(null==sdf) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			localDate.set(sdf);
		}
		return sdf;
	}
	
	private static SimpleDateFormat getTimeSdf() {
		SimpleDateFormat sdf = localTime.get();
		if(null==sdf) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			localTime.set(sdf);
		}
		return sdf;
	}
}
