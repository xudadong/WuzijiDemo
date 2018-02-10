package com.wuziqi.demo;

import java.util.HashMap;
import java.util.Map;

public class AllChesses {
	private static Map<String, Boolean> dimensionKeyvalue = new HashMap<String, Boolean>();
	/**
	 * <p> [概 要] 棋子状態更新 </p>
	 * <p> [詳 細]</p>
	 * <p> [備 考]</p>
	 * @param key 棋子の位置
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
	 * <p> [概 要] 棋子状態チェック </p>
	 * <p> [詳 細]</p>
	 * <p> [備 考] </p>
	 * @param true:棋盤の棋子存在、false：棋盤の棋子存在しない
	 */
	public static boolean checkValue(String key) {
		if (dimensionKeyvalue.get(key) != null && dimensionKeyvalue.get(key)) {
			return true;
		}
		return false;
	}
	/**
	 * <p>[概 要] 棋子存在チェック</p>
	 * <p>[詳 細]</p>
	 * <p>[備 考]</p>
	 * @param true:棋子存在、false：棋盤の棋子存在しない
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
	 * <p>[概 要] 棋子状態初期化</p>
	 * <p>[詳 細]</p>
	 * <p>[備 考]</p>
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
