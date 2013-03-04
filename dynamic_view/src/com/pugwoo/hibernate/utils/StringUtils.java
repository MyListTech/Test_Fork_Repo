package com.pugwoo.hibernate.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	/**
	 * 将str按照split分割成若刚个子string
	 * leftToRight指示split的方向，maxToken指示最大的分割个数，0表示没有限制
	 */
	public static List<String> split(String str, char split,
			boolean leftToRight, int maxToken) {
		if (str == null)
			return null;

		List<String> list = new ArrayList<String>();
		if (maxToken == 0)
			maxToken = Integer.MAX_VALUE;
		int curToken = 0; // 记录当前token的个数
		int pos; // 记录当前指针位置
		int last; // 記錄最後一個token結束之後的開始位置
		if (leftToRight) { // 从左到右
			pos = 0;
			last = 0;
			while (curToken < maxToken - 1 && pos < str.length()) {
				if (str.charAt(pos) == split) {
					list.add(str.substring(last, pos));
					curToken++;
					last = pos + 1;
				}
				pos++;
			}
			// 處理最後一個
			list.add(str.substring(last, str.length()));
		} else {
			pos = str.length() - 1;
			last = str.length();
			while (curToken < maxToken - 1 && pos >= 0) {
				if (str.charAt(pos) == split) {
					list.add(str.substring(pos + 1, last));
					curToken++;
					last = pos;
				}
				pos--;
			}
			// 處理最後一個
			list.add(str.substring(0, last));
		}
		return list;
	}

	public static List<String> split(String str, char split) {
		return split(str, split, true, 0);
	}

	/**
	 * 将str转换成SQL的String，也即加上了单引号还有转义了单引号
	 */
	public static String escape(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append('\'');
		int last = 0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == '\'') {
				sb.append(str.subSequence(last, i + 1)).append('\'');
				last = i + 1;
			}
		if (last < str.length())
			sb.append(str.subSequence(last, str.length()));
		sb.append('\'');
		return sb.toString();
	}

	/**
	 * 计算字符ch在str中出现的个数
	 */
	public static int count(String str, char ch) {
		int count = 0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == ch)
				count++;
		return count;
	}

	/**
	 * 一个简单的输出排版，还不能动态收缩，注意tabs是每列的宽度，以tab为单位
	 * 一个tab是8个字节
	 */
	public static void println(int tabs, String... strs) {
		if (strs == null || strs.length == 0) {
			System.out.println();
			return;
		}
		if (tabs <= 0)
			tabs = 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]);
			if (i != strs.length - 1) {
				int n = tabs - strs[i].length() / 8;
				for(int j=0; j<n; j++)
					sb.append("\t");
			}
		}
		System.out.println(sb);
	}

	public static void main(String args[]) {
		// List<String> list = split("school.address.name", '.', true, 2);
		// for(int i=0; i<list.size(); i++)
		// System.out.print(list.get(i) + " ");
	}
}
