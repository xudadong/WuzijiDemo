package com.wuziqi.demo;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
	/**
	 * <p> [�� Ҫ] Winner�Υ����å� </p>
	 * <p> [Ԕ ��] </p>
	 * *
	 * <p> [�� ��] </p>
	 * @param dimension  �����å�������
	 * @param checkDimesions ��P���Фˤ�����
	 * @return true:win false:�A��
	 */  
	public static boolean checkWin(String lastDimension,
			List<String> winerDimesions) {
		String key[] = lastDimension.split(",");
		int width = Integer.parseInt(key[0]);
		int height = Integer.parseInt(key[1]);
		// Ӌ�㷽������y=x��15*n
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
		// Ӌ�㷽��2:y=15*n+x)
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
		// Ӌ�㷽��3:y=15*n
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
		// Ӌ�㷽��4:x=15n
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
	 * <p>[�� Ҫ] ���ӤΥ����å� </p>
	 * <p> [Ԕ ��]</p>
	 * <p>[�� ��] </p>
	 * @param dimension �����å������� 
	 * @param checkDimesions ��P���Фˤ�����  
	 * param type Ӌ�㷽��(1:y=x��15*n 2:y=15*n+x) 3:y=15*n 4:x=15n 
	 * @return �����å��������������
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
	 * <p>[�� Ҫ] �Μ�������ӥ����å�</p>
	 * <p>[Ԕ ��] </p>
	 * <p>[�� ��] </p>
	 * @param dimension ǰ����   
	 * @param checkDimesions ��P���Фˤ�����     
	 * param type Ӌ�㷽��(1:y=x��15*n 2:y=15*n+x)  3:y=15*n 4:x=15n
	 * param count ��������ӂ���
	  */  
	private static int checkNextDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// Ӌ�㷽���������Ӥ�Ӌ��
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
			// ͬ�������δ�����ȡ�Õr����P���Х����å���������������������ӥꥹ��׷�ӡ��������ӤϬF�����Ӥ˸�{���롢�Μ�������ӥ����å����ޤ�
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkNextDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
	/**
	 * <p> [�� Ҫ] ǰ��������ӥ����å� </p>
	 * <p> [Ԕ ��] </p>
	 * <p> [�� ��] </p>
	 * @param dimension ������ 
	 * @param checkDimesions ��P���Фˤ����� 
	 * param type Ӌ�㷽��(1:y=x��15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
	 * param count ��������ӂ���
	 */
	private static int checkPrevDimension(String dimension,
			List<String> checkDimesions, String type, int count) {
		int x = 0;
		int y = 0;
		String location[] = dimension.split(",");
		int xLocation = Integer.parseInt(location[0]);
		int yLocation = Integer.parseInt(location[1]);
		// // Ӌ�㷽����ǰ���Ӥ�Ӌ��
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
			// ͬ�������δ�����ȡ�Õr����P���Х����å���������������������ӥꥹ��׷�ӡ��������ӤϬF�����Ӥ˸�{���롢ǰ��������ӥ����å����ޤ�
			if (x == xCheckLocation && y == yCheckLocation) {
				count++;
				count = checkPrevDimension(checkDimension, checkDimesions,
						type, count);
			}
		}
		return count;
	}
				
}
