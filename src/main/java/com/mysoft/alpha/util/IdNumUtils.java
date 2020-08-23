package com.mysoft.alpha.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class IdNumUtils {

	public static String getSex(String idNum) throws Exception {
		String sex = "";
		if (StringUtils.isEmpty(idNum) || idNum.length() != 18) {
			throw new IllegalArgumentException("The idNum must not null and lenth is 18");
		}
		if (Integer.valueOf(idNum.substring(16, 17)) % 2 == 0) {
			sex = "女";
		} else {
			sex = "男";
		}
		return sex;
	}

	/**
	 * 
	 * @param idNum 18位身份证号
	 * @return 年龄
	 * @throws Exception
	 */
	public static int getAge(String idNum) throws Exception {
		int ret = 0;
		if (StringUtils.isEmpty(idNum) || idNum.length() != 18) {
			throw new IllegalArgumentException("The idNum must not null and lenth is 18");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dt = null;
		dt = df.parse(idNum.substring(6, 14));
		ret = getAge(dt);
		return ret;
	}

	public static int getAge(Date birthDay) throws Exception {
		if (birthDay == null) {
			throw new IllegalArgumentException("The birthDay must not null!");
		}
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减1
			}
		}
		return age;
	}

	/**
	 * 
	 * @param idNum 18位身份证号
	 * @return 所在省份
	 * @throws Exception
	 */
	public static String getProvince(String idNum) throws Exception {
		if (StringUtils.isEmpty(idNum) || idNum.length() != 18) {
			throw new IllegalArgumentException("The idNum must not null and lenth is 18");
		}
		String[] a = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
				"42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81",
				"82" };

		String[] b = { "北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", " 江苏省", "浙江省", "安徽省", "福建省",
				" 江西省", "山东省", " 河南省", "湖北省", " 湖南省", "广东省", " 广西壮族自治区", "海南省", "重庆市", "四川省", "贵州省", "云南省", " 西藏自治区",
				"陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "台湾省", "香港特别行政区", "澳门特别行政区" }; // 将省份全部放进数组b;
		String pos = (idNum.substring(0, 2)); // id.substring(0, 2)获取身份证的前两位；
		int i;
		for (i = 0; i < a.length; i++) {
			if (pos.equals(a[i])) {
				break;
			}
		}
		return b[i]; // 获取b数组中的省份信息且输出省份;
	}

}
