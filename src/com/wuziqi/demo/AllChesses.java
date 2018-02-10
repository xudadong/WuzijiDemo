package com.wuziqi.demo;

import java.util.HashMap;
import java.util.Map;

public class AllChesses {
	private static Map<String, Boolean> dimensionKeyvalue = new HashMap<String, Boolean>();
	/**
	 * <p> [�� Ҫ] ����״�B���� </p>
	 * <p> [Ԕ ��]</p>
	 * <p> [�� ��]</p>
	 * @param key ���Ӥ�λ��
	 */
	public static void updateDimensionKeyValue(String key) {
		if (dimensionKeyvalue.get(key) == null) {
			return;
		}
		if (!checkValue(key)) {
			dimensionKeyvalue.put(key, true);
		}
	}
	/**
	 * <p> [�� Ҫ] ����״�B�����å� </p>
	 * <p> [Ԕ ��]</p>
	 * <p> [�� ��] </p>
	 * @param true:��P�����Ӵ��ڡ�false����P�����Ӵ��ڤ��ʤ�
	 */
	public static boolean checkValue(String key) {
		if (dimensionKeyvalue.get(key) != null && dimensionKeyvalue.get(key)) {
			return true;
		}
		return false;
	}
	/**
	 * <p>[�� Ҫ] ���Ӵ��ڥ����å�</p>
	 * <p>[Ԕ ��]</p>
	 * <p>[�� ��]</p>
	 * @param true:���Ӵ��ڡ�false����P�����Ӵ��ڤ��ʤ�
	 */
	public static boolean exitCheck(String key) {
		if (dimensionKeyvalue.get(key) == null) {
			return true;
		}
		if (checkValue(key)) {
			return true;
		}
		return false;
	}
	/**
	 * *
	 * <p>[�� Ҫ] ����״�B���ڻ�</p>
	 * <p>[Ԕ ��]</p>
	 * <p>[�� ��]</p>
	 */
	public static void init(int width, int height) {
		for (int i = 1; i <= width / QiPan.WANGGEWIDTH; i++) {
			for (int j = 1; j <= height / QiPan.WANGGEWIDTH; j++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i * 15);
				sb.append(",");
				sb.append(j * 15);
				initDimension(sb.toString());
			}
		}
	}
	private static void initDimension(String key) {
		dimensionKeyvalue.put(key, false);
	}
}
