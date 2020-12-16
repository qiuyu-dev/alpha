package com.mysoft.alpha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysoft.alpha.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlphaApplicationTests {

	@Test
	public void contextLoads() {
	}

	
	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentYearAndMonth());
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1;
		try {
			dt1 = df1.parse("1900-01-01 00:00:00");
		
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dt1);
        calendar.add(Calendar.DATE, Float.valueOf(44267).intValue()-2);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date dt = calendar.getTime();
        System.out.println(dt);
        System.out.println(df1.format(dt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
