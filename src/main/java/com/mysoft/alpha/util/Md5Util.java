package com.mysoft.alpha.util;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	 /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    
	/**
	 * @param text明文
	 * @param key密钥
	 * @return 密文
	 */
	// 带秘钥加密
	public static String md5(String text, String key) throws Exception {
		// 加密后的字符串
		String md5str = DigestUtils.md5Hex(text + key);
		System.out.println("MD5加密后的字符串为:" + md5str);
		return md5str;
	}

	// 不带秘钥加密
	public static String md52(String text) throws Exception {
		// 加密后的字符串
		String md5str = DigestUtils.md5Hex(text);
		System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());
		return md5str;
	}

	/**
	 * MD5验证方法
	 * 
	 * @param text明文
	 * @param key密钥
	 * @param md5密文
	 */
	// 根据传入的密钥进行验证
	public static boolean verify(String text, String key, String md5) throws Exception {
		String md5str = md5(text, key);
		if (md5str.equalsIgnoreCase(md5)) {
			System.out.println("MD5验证通过");
			return true;
		}
		return false;
	}

    // 测试主函数
    public static void main(String args[]) throws Exception{
        String s = "oIb_o5ZW3MLDfB5pLO27y5sYswAg";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
		md52(s);
		md5(s, "mykey");
		verify(s, "mykey","9d097d6d946ca5289183e84de389e904");
    }


}
