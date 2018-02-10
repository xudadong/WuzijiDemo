package com.wuziqi.demo;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
	/**
	 * <p> [概 要] Winnerのチェック </p>
	 * <p> [詳 細] </p>
	 * *
	 * <p> [備 考] </p>
	 * @param dimension  チェック用棋子
	 * @param checkDimesions 棋盤の中にの棋子
	 * @return true:win false:続き
	 */  
	public static boolean checkWin(String lastDimension,
			List<String> winerDimesions) {
		String key[] = lastDimension.split(",");
		int width = Integer.parseInt(key[0]);
		int height = Integer.parseInt(key[1]);
		// 計算方法１：y=x±15*n
		int n = (height - width) / 15;
		List<String> dimesions = new ArrayList<String>();
		for (String dimenSion : winerDimesions) {
			String location[] = dimenSion.split(",");
			int xLocation = Integer.parseInt(location[0]);
			int yLocation = Integer.parseInt(location[1]);
			double y = xLocation + 15 * n;
			if (y == yLocation && y != height) {
				dimesions.add(dimenSion);
			}
		}
		if (checkDimension(lastDimension, dimesions, "1")) {
			return true;
		}
		// 計算方法2:y=15*n+x)
		n = (width + height) / 15;
		dimesions = new ArrayList<String>();
		for (String dimenSion : winerDimesions) {
			String location[] = dimenSion.split(",");
			int xLocation = Integer.parseInt(location[0]);
			int yLocation = Integer.parseInt(location[1]);
			double y = 15 * n - xLocation;
			if (y == yLocation && y != height) {
				dimesions.add(dimenSion);
			}
			if (checkDimension(lastDimension, dimesions, "2")) {
				return true;
			}

		}
		// 計算方法3:y=15*n
		dimesions = new ArrayList<String>();
		for (String dimenSion : winerDimesions) {
			String location[] = dimenSion.split(",");
			int xLocation = Integer.parseInt(location[0]);
			int yLocation = Integer.parseInt(location[1]);
			if (width == xLocation && height != yLocation) {
				dimesions.add(dimenSion);
			}
			if (checkDimension(lastDimension, dimesions, "3")) {
				return true;
			}
		}
		// 計算方法4:x=15n
		dimesions = new ArrayList<String>();
		for (String dimenSion : winerDimesions) {
			String location[] = dimenSion.split(",");
			int xLocation = Integer.parseInt(location[0]);
			int yLocation = Integer.parseInt(location[1]);
			if (width != xLocation && height == yLocation) {
				dimesions.add(dimenSion);
			}
		}
		if (checkDimension(lastDimension, dimesions, "4")) {
			return true;
		}
		return false;
	}
	/**
	 * <p>[概 要] 棋子のチェック </p>
	 * <p> [詳 細]</p>
	 * <p>[備 考] </p>
	 * @param dimension チェック用棋子 
	 * @param checkDimesions 棋盤の中にの棋子  
	 * param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n 
	 * @return チェック条件満足の棋子
	 * */
	private static boolean checkDimension(String dimension,
			List<String> checkDimesions, String type) {
		int nextCount = checkNextDimension(dimension, checkDimesions, type, 0);
		int prevCount = checkPrevDimension(dimension, checkDimesions, type, 0);
		if ((nextCount + prevCount) >= 4) {
			return true;
		}
		return false;
	} 
	/**     
	 * <p>[概 要] 次満足の棋子チェック</p>
	 * <p>[詳 細] </p>
	 * <p>[備 考] </p>
	 * @param dimension 前棋子   
	 * @param checkDimesions 棋盤の中にの棋子     
	 * param type 計算方法(1:y=x±15*n 2:y=15*n+x)  3:y=15*n 4:x=15n
	 * param count 満足の棋子個数
	  */  
	private static int checkNextDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// 計算方法、次棋子の計算
		if (type.equals("1")) {
			x = xLocation + 15;
			y = yLocation + 15;
		} else if (type.equals("2")) {
			x = xLocation + 15;
			y = yLocation - 15;
		} else if (type.equals("3")) {
			x = xLocation;
			y = yLocation + 15;
		} else if (type.equals("4")) {
			x = xLocation + 15;
			y = yLocation;
		} else {
			return 0;
		}
		for (String checkDimension : checkDimesions) {
			String checkLocation[] = checkDimension.split(",");
			int xCheckLocation = Integer.parseInt(checkLocation[0]);
			int yCheckLocation = Integer.parseInt(checkLocation[1]);
			// 同じ方法の次棋子取得時、棋盤の中チェック用棋子削除、満足の棋子リスト追加、削除棋子は現在棋子に格納する、次満足の棋子チェックします
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkNextDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
	/**
	 * <p> [概 要] 前満足の棋子チェック </p>
	 * <p> [詳 細] </p>
	 * <p> [備 考] </p>
	 * @param dimension 後棋子 
	 * @param checkDimesions 棋盤の中にの棋子 
	 * param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
	 * param count 満足の棋子個数
	 */
	private static int checkPrevDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// // 計算方法、前棋子の計算
		if (type.equals("1")) {
			x = xLocation - 15;
			y = yLocation - 15;
		} else if (type.equals("2")) {
			x = xLocation - 15;
			y = yLocation + 15;
		} else if (type.equals("3")) {
			x = xLocation;
			y = yLocation - 15;
		} else if (type.equals("4")) {
			x = xLocation - 15;
			y = yLocation;
		} else {
			return 0;
		}
		for (String checkDimension : checkDimesions) {
			String checkLocation[] = checkDimension.split(",");
			int xCheckLocation = Integer.parseInt(checkLocation[0]);
			int yCheckLocation = Integer.parseInt(checkLocation[1]);
			// 同じ方法の次棋子取得時、棋盤の中チェック用棋子削除、満足の棋子リスト追加、削除棋子は現在棋子に格納する、前満足の棋子チェックします
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkPrevDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
				
}
