package com.manulife.top5website.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String formatDateTime(Date d) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");		 
		String formatDateTime = df.format(d);
		return formatDateTime;
	}
}
