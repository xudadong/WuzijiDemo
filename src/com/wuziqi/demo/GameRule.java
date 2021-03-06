package com.wuziqi.demo;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
	/**
	 * <p> [古 勣] Winnerのチェック </p>
	 * <p> [�� ��] </p>
	 * *
	 * <p> [�� 深] </p>
	 * @param dimension  チェック喘薙徨
	 * @param checkDimesions 薙�Pの嶄にの薙徨
	 * @return true:win false:�Aき
	 */  
	public static boolean checkWin(String lastDimension,
			List<String> winerDimesions) {
		String key[] = lastDimension.split(",");
		int width = Integer.parseInt(key[0]);
		int height = Integer.parseInt(key[1]);
		// ��麻圭隈１��y=x＼15*n
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
		// ��麻圭隈2:y=15*n+x)
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
		// ��麻圭隈3:y=15*n
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
		// ��麻圭隈4:x=15n
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
	 * <p>[古 勣] 薙徨のチェック </p>
	 * <p> [�� ��]</p>
	 * <p>[�� 深] </p>
	 * @param dimension チェック喘薙徨 
	 * @param checkDimesions 薙�Pの嶄にの薙徨  
	 * param type ��麻圭隈(1:y=x＼15*n 2:y=15*n+x) 3:y=15*n 4:x=15n 
	 * @return チェック訳周�祭磴瞭縋�
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
	 * <p>[古 勣] 肝�祭磴瞭縋咼船Д奪�</p>
	 * <p>[�� ��] </p>
	 * <p>[�� 深] </p>
	 * @param dimension 念薙徨   
	 * @param checkDimesions 薙�Pの嶄にの薙徨     
	 * param type ��麻圭隈(1:y=x＼15*n 2:y=15*n+x)  3:y=15*n 4:x=15n
	 * param count �祭磴瞭縋���方
	  */  
	private static int checkNextDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// ��麻圭隈、肝薙徨の��麻
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
			// 揖じ圭隈の肝薙徨函誼�r、薙�Pの嶄チェック喘薙徨��茅、�祭磴瞭縋咼螢好盤啓咫∀�茅薙徨は�F壓薙徨に鯉�{する、肝�祭磴瞭縋咼船Д奪�します
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkNextDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
	/**
	 * <p> [古 勣] 念�祭磴瞭縋咼船Д奪� </p>
	 * <p> [�� ��] </p>
	 * <p> [�� 深] </p>
	 * @param dimension 瘁薙徨 
	 * @param checkDimesions 薙�Pの嶄にの薙徨 
	 * param type ��麻圭隈(1:y=x＼15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
	 * param count �祭磴瞭縋���方
	 */
	private static int checkPrevDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// // ��麻圭隈、念薙徨の��麻
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
			// 揖じ圭隈の肝薙徨函誼�r、薙�Pの嶄チェック喘薙徨��茅、�祭磴瞭縋咼螢好盤啓咫∀�茅薙徨は�F壓薙徨に鯉�{する、念�祭磴瞭縋咼船Д奪�します
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkPrevDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
				
}
