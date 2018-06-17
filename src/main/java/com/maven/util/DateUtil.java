package com.maven.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Li Yongqiang
 *
 */
public class DateUtil {

	
	public static String getDateTimeString(Date date){
		
		return getString(date);
	}
	
	private static String getString(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		
		return str;
	}
}
