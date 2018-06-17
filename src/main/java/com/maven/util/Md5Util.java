package com.maven.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 加密工具类
 * 
 * @author Li Yongqiang
 *
 */
public class Md5Util {

	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str){
		
		return md5(str);
	}
	
	/**
	 * 获取随机盐值
	 * 
	 * @param length 盐长度
	 * @return 返回一个长度为length的随机盐值
	 */
	public static String getRandomSalt(int length) { // length 字符串长度
		
		return RadomSalt(length);
	}
	
	/**
	 * md5加密算法
	 * 
	 * @param str 要加密的字符串
	 * @return 返回一个32位的MD5加密后的字符串
	 */
	private static String md5(String str) {
		
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}

	/**
	 * 随机盐值实现方法
	 * 
	 * @param length 盐长度
	 * @return 返回一个长度为length的随机盐值
	 */
	private static String RadomSalt(int length){
		
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		
		return sb.toString();
	}

}
