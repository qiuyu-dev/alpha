package com.mysoft.alpha.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	public static String getParameterUrl(Map<String, String> map, HttpServletRequest request) {
		String requestUrl = null;
		Set<String> set = map.keySet();
		Iterator<String> it  = set.iterator();
		String key = "";
		StringBuffer para = new StringBuffer();
		while(it.hasNext()) {
			key = (String)it.next();
			para.append(key+"=" + map.get(key) + "&");			
		}
		para.deleteCharAt(para.lastIndexOf("&"));
	    requestUrl = request.getRequestURI() +"?"+ para.toString();
	    return requestUrl;
	}

}
