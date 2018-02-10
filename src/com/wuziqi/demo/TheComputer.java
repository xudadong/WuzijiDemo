package com.wuziqi.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>[概 要] パソコン。</p>
 * <p>[詳 細]</p>
 * <p> [備 考]</p>
 * <p>[環 境] OracleJDK 8</p>
 * <p> [V / R] R30-1・1.0</p>
 * <p>Copyright(c) NTT COMWARE 2017</p>
 */
public class TheComputer {
    /**
     * <p>[概 要] 棋子移動</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param otherdimesions 他の棋子リスト
     * @param dimesions 自分の棋子リスト
     * @param 移動の棋子
     */
    public static String moveChess(List<String> otherdimesions,
            List<String> dimesions) {
        String dimesionResult = "";
        // 選手の棋子チェック
        String[] checkOtherResult = checkChess(otherdimesions);
        // パソコンの棋子チェック
        String[] checkResult = checkChess(dimesions);
        // 優先の棋子に設定
        if (checkOtherResult != null && checkResult == null) {
            dimesionResult = checkOtherResult[1];
        } else if (checkOtherResult == null && checkResult != null) {
            dimesionResult = checkResult[1];
        } else if (checkOtherResult != null && checkResult != null) {
            if (Integer.parseInt(checkOtherResult[0]) >= Integer
                    .parseInt(checkResult[0])) {
                dimesionResult = checkOtherResult[1];
            } else {
                dimesionResult = checkResult[1];
            }
        }
        // 優先の棋子ない場合、棋子の設定
        if ("".equals(dimesionResult)) {
            dimesionResult = getRandomDimensin();
        }
        return dimesionResult;
    }
    private static String[] checkChess(List<String> dimesions) {
        Map<Integer, List<String>> priorityMap = new HashMap<Integer, List<String>>();
        List<String> type1Lst = new ArrayList<String>();
        List<String> type2Lst = new ArrayList<String>();
        List<String> type3Lst = new ArrayList<String>();
        List<String> type4Lst = new ArrayList<String>();
        if (dimesions == null || dimesions.size() == 0) {
            return null;
        }
        for (String dimenSion : dimesions) {
            if (!type1Lst.contains(dimenSion)) {
                type1Lst = checkDimension(dimenSion, dimesions, "1");
            }
            if (!type2Lst.contains(dimenSion)) {
                type2Lst = checkDimension(dimenSion, dimesions, "2");
            }
            if (!type3Lst.contains(dimenSion)) {
                type3Lst = checkDimension(dimenSion, dimesions, "3");
            }
            if (!type4Lst.contains(dimenSion)) {
                type4Lst = checkDimension(dimenSion, dimesions, "4");
            }
            // 優先の棋子計算実行
            priorityTypeLists(type1Lst, type2Lst, type3Lst, type4Lst,
                    priorityMap);
        }
        String[] resultpriority = new String[2];
        for (int i = 4; i > 0; i--) {
            if (priorityMap.get(i) != null) {
                if (priorityMap.get(i).size() != 0) {
                    resultpriority[0] = String.valueOf(i);
                    int index = (int) (Math.random() * (priorityMap.get(i)
                            .size() - 1));
                    resultpriority[1] = priorityMap.get(i).get(index);
                    break;
                }
            }
        }
        return resultpriority;
    }
    /**
     * <p>[概 要] 優先の棋子計算</p>
     * <p>[詳 細]</p>
     * <p> [備 考]</p>
     * @param type1Lst 計算方法1の棋子リスト 
     * @param type2Lst 計算方法2の棋子リスト 
     * @param type3Lst 計算方法3の棋子リスト 
     * @param type4Lst 計算方法4の棋子リスト 
     * @param priorityMap 計算結果Map
     */
    private static void priorityTypeLists(List<String> type1Lst,
            List<String> type2Lst, List<String> type3Lst,
            List<String> type4Lst, Map<Integer, List<String>> priorityMap) {
        // 棋盤の棋子が4個場合、最優先
        if (type1Lst != null && type1Lst.size() >= 4) {
            List<String> firstLst = getcheckDimensin(type4Lst, "1");
            if (firstLst != null && firstLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(4) == null
                        || priorityMap.get(4).size() == 1) {
                    priorityMap.put(4, firstLst);
                }
            }
        }
        // 棋盤の棋子が4個場合、最優先
        if (type2Lst != null && type2Lst.size() >= 4) {
            List<String> firstLst = getcheckDimensin(type2Lst, "2");
            if (firstLst != null && firstLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(4) == null
                        || priorityMap.get(4).size() == 1) {
                    priorityMap.put(4, firstLst);
                }
            }
        }
        // 棋盤の棋子が4個場合、最優先
        if (type3Lst != null && type3Lst.size() >= 4) {
            List<String> firstLst = getcheckDimensin(type3Lst, "3");
            if (firstLst != null && firstLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(4) == null
                        || priorityMap.get(4).size() == 1) {
                    priorityMap.put(4, firstLst);
                }
            }
        }
        // 棋盤の棋子が4個場合、最優先
        if (type4Lst != null && type4Lst.size() >= 4) {
            List<String> firstLst = getcheckDimensin(type4Lst, "4");
            if (firstLst != null && firstLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(4) == null
                        || priorityMap.get(4).size() == 1) {
                    priorityMap.put(4, firstLst);
                }
            }
        }
        // 棋盤の棋子が3個場合、次優先、棋盤中に2個できれば、2個優先より
        if (type1Lst != null && type1Lst.size() >= 3) {
            List<String> secondLst = getcheckDimensin(type1Lst, "1");
            if (secondLst != null && secondLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(3) == null
                        || priorityMap.get(3).size() == 1) {
                    priorityMap.put(3, secondLst);
                }
            }
        }
        // 棋盤の棋子が3個場合、次優先、棋盤中に2個できれば、2個優先より
        if (type2Lst != null && type2Lst.size() >= 3) {
            List<String> secondLst = getcheckDimensin(type2Lst, "2");
            if (secondLst != null && secondLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(3) == null
                        || priorityMap.get(3).size() == 1) {
                    priorityMap.put(3, secondLst);
                }
            }
        }
        // 棋盤の棋子が3個場合、次優先、棋盤中に2個できれば、2個優先より
        if (type3Lst != null && type3Lst.size() >= 3) {
            List<String> secondLst = getcheckDimensin(type3Lst, "3");
            if (secondLst != null && secondLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(3) == null
                        || priorityMap.get(3).size() == 1) {
                    priorityMap.put(3, secondLst);
                }
            }
        }
        // 棋盤の棋子が3個場合、次優先、棋盤中に2個できれば、2個優先より
        if (type4Lst != null && type4Lst.size() >= 3) {
            List<String> secondLst = getcheckDimensin(type4Lst, "4");
            if (secondLst != null && secondLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(3) == null
                        || priorityMap.get(3).size() == 1) {
                    priorityMap.put(3, secondLst);
                }
            }
        }
        // 棋盤の棋子が2個場合、再優先、棋盤中に2個できれば、2個優先より
        if (type1Lst != null && type1Lst.size() >= 2) {
            List<String> threeLst = getcheckDimensin(type1Lst, "1");
            if (threeLst != null && threeLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(2) == null
                        || priorityMap.get(2).size() == 1) {
                    priorityMap.put(2, threeLst);
                }
            }
        }
        // 棋盤の棋子が2個場合、再優先、棋盤中に2個できれば、2個優先より
        if (type2Lst != null && type2Lst.size() >= 2) {
            List<String> threeLst = getcheckDimensin(type2Lst, "2");
            if (threeLst != null && threeLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(2) == null
                        || priorityMap.get(2).size() == 1) {
                    priorityMap.put(2, threeLst);
                }
            }
        }
        // 棋盤の棋子が2個場合、再優先、棋盤中に2個できれば、2個優先より
        if (type3Lst != null && type3Lst.size() >= 2) {
            List<String> threeLst = getcheckDimensin(type3Lst, "3");
            if (threeLst != null && threeLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(2) == null
                        || priorityMap.get(2).size() == 1) {
                    priorityMap.put(2, threeLst);
                }
            }
        }
        // 棋盤の棋子が2個場合、再優先、棋盤中に2個できれば、2個優先より
        if (type4Lst != null && type4Lst.size() >= 2) {
            List<String> threeLst = getcheckDimensin(type4Lst, "4");
            if (threeLst != null && threeLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(2) == null
                        || priorityMap.get(2).size() == 1) {
                    priorityMap.put(2, threeLst);
                }
            }
        }
        // 棋盤の棋子が1個場合、優先しない
        if (type1Lst != null && type1Lst.size() >= 1) {
            List<String> lastLst = getcheckDimensin(type1Lst, "1");
            if (lastLst != null && lastLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(1) == null
                        || priorityMap.get(1).size() == 1) {
                    priorityMap.put(1, lastLst);
                }
            }
        }
        // 棋盤の棋子が1個場合、優先しない
        if (type2Lst != null && type2Lst.size() >= 1) {
            List<String> lastLst = getcheckDimensin(type2Lst, "2");
            if (lastLst != null && lastLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(1) == null
                        || priorityMap.get(1).size() == 1) {
                    priorityMap.put(1, lastLst);

                }
            }
        }
        // 棋盤の棋子が1個場合、優先しない
        if (type3Lst != null && type3Lst.size() >= 1) {
            List<String> lastLst = getcheckDimensin(type3Lst, "3");
            if (lastLst != null && lastLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(1) == null
                        || priorityMap.get(1).size() == 1) {
                    priorityMap.put(1, lastLst);
                }
            }
        }
        // 棋盤の棋子が1個場合、優先しない
        if (type4Lst != null && type4Lst.size() >= 1) {
            List<String> lastLst = getcheckDimensin(type4Lst, "4");
            if (lastLst != null && lastLst.size() > 0) {
                // 前回の計算結果リストが1件場合、最新計算結果はMapに格納する、1件以上の結果は優先より
                if (priorityMap.get(1) == null
                        || priorityMap.get(1).size() == 1) {
                    priorityMap.put(1, lastLst);
                }
            }
        }
    }
    /**
     * <p>[概 要] move棋子取得する</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param typeLst 計算方法の棋子リスト *
     * @param type 計算方法 * @return move用棋子
     */
    private static List<String> getcheckDimensin(List<String> typeLst,
            String type) {
        List<String> dimensins = new ArrayList<String>();
        for (String key : typeLst) {
            // 次有効の棋子
            String prevKey = getPrevDimension(key, type);
            if (!"".equals(prevKey)) {
                if (!dimensins.contains(prevKey)) {
                    dimensins.add(prevKey);
                }
            }
            // // 前有効の棋子
            String nextKey = getNextDimension(key, type);
            if (!"".equals(nextKey)) {
                if (!dimensins.contains(nextKey)) {
                    dimensins.add(nextKey);
                }
            }
        }
        return dimensins;
    }
    /**
     * <p>[概 要] random棋子取得</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @return (15,15)→(300,300)の棋子
     */
    private static String getRandomDimensin() {
        int x = (int) (Math.random() * 285) + 15;

        int y = (int) (Math.random() * 285) + 15;
        int xpoints = x / QiPan.WANGGEWIDTH;
        double xDeviation = x % QiPan.WANGGEWIDTH;
        int ypoints = y / QiPan.WANGGEWIDTH;
        double yDeviation = y % QiPan.WANGGEWIDTH;
        if (xDeviation >= 7.5) {
            xpoints = xpoints + 1;
        }
        if (yDeviation >= 7.5) {
            ypoints = ypoints + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(xpoints * QiPan.WANGGEWIDTH);
        sb.append(",");
        sb.append(ypoints * QiPan.WANGGEWIDTH);
        while (true) {
            if (!AllChesses.checkValue(sb.toString())) {
                break;
            }
        }
        return sb.toString();
    }
    /**
     * <p>[概 要] 棋子のチェック</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param dimension チェック用棋子 *
     * @param checkDimesions 棋盤の中にの棋子 * 
     * @param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n *
     * @return チェック条件満足の棋子
     */
    private static List<String> checkDimension(String dimension,
            List<String> dimesions, String type) {
        List<String> resultDimesions = new ArrayList<String>();
        List<String> checkNextDimesions = new ArrayList<String>();
        checkNextDimesions.addAll(dimesions);
        resultDimesions.add(dimension);
        // 次満足の棋子チェック
        checkNextDimension(dimension, checkNextDimesions.iterator(), type,
                resultDimesions);
        List<String> checkPrevDimesions = new ArrayList<String>();
        checkPrevDimesions.addAll(dimesions);
        // 前満足の棋子チェック
        checkPrevDimension(dimension, checkPrevDimesions.iterator(), type,
                resultDimesions);
        return resultDimesions;
    }
    /**
     * <p>[概 要] 次満足の棋子チェック</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param dimension 画面情報DTO
     * @param checkDimesions 棋盤の中にの棋子 
     * @param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
     * @param resultDimesions 満足の棋子リスト
     */
    private static void checkNextDimension(String dimension,
            Iterator<String> checkDimesions, String type,
            List<String> resultDimesions) {
        int x = 0;
        int y = 0;
        String checkKey[] = dimension.split(",");
        int checkWidth = Integer.parseInt(checkKey[0]);
        int checkWheight = Integer.parseInt(checkKey[1]);
        // 計算方法、次棋子の計算
        if (type.equals("1")) {
            x = checkWidth + 15;
            y = checkWheight + 15;
        } else if (type.equals("2")) {
            x = checkWidth + 15;
            y = checkWheight - 15;
        } else if (type.equals("3")) {
            x = checkWidth;
            y = checkWheight + 15;
        } else if (type.equals("4")) {
            x = checkWidth + 15;
            y = checkWheight;
        } else {
            return;
        }
        if (checkDimesions.hasNext()) {
            String checkDimension = checkDimesions.next();
            String key[] = checkDimension.split(",");
            int width = Integer.parseInt(key[0]);
            int height = Integer.parseInt(key[1]);
            // 同じ方法の次棋子取得時、棋盤の中チェック用棋子削除、満足の棋子リスト追加、削除棋子は現在棋子に格納する、次満足の棋子チェックします
            if (x == width && y == height) {
                resultDimesions.add(checkDimension);
                checkDimesions.remove();
                checkNextDimension(checkDimension, checkDimesions, type,
                        resultDimesions);
            } else {
                checkDimesions.remove();
                checkNextDimension(dimension, checkDimesions, type,
                        resultDimesions);
            }
        }
    }
    /**
     * <p>[概 要] 次有効の棋子取得</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param dimension 棋盤の中にの棋子
     * @param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
     * @param 次有効の棋子
     */
    private static String getNextDimension(String dimension, String type) {
        int x = 0;
        int y = 0;
        String checkKey[] = dimension.split(",");
        int checkWidth = Integer.parseInt(checkKey[0]);
        int checkWheight = Integer.parseInt(checkKey[1]);
        if (type.equals("1")) {
            x = checkWidth + 15;
            y = checkWheight + 15;
        } else if (type.equals("2")) {
            x = checkWidth + 15;
            y = checkWheight - 15;
        } else if (type.equals("3")) {
            x = checkWidth;
            y = checkWheight + 15;
        } else if (type.equals("4")) {
            x = checkWidth + 15;
            y = checkWheight;
        } else {
            return "";
        }
        StringBuilder nextDimension = new StringBuilder();
        nextDimension.append(x);
        nextDimension.append(",");
        nextDimension.append(y);
        if (AllChesses.checkValue(nextDimension.toString())) {
            return "";
        }
        return nextDimension.toString();
    }
    /**
     * <p>[概 要] 前有効の棋子取得</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param dimension 棋盤の中にの棋子 *
     * @param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
     * @param 前有効の棋子
     */
    private static String getPrevDimension(String dimension, String type) {
        int x = 0;
        int y = 0;
        String checkKey[] = dimension.split(",");
        int checkWidth = Integer.parseInt(checkKey[0]);
        int checkWheight = Integer.parseInt(checkKey[1]);
        if (type.equals("1")) {
            x = checkWidth - 15;
            y = checkWheight - 15;
        } else if (type.equals("2")) {
            x = checkWidth - 15;
            y = checkWheight + 15;
        } else if (type.equals("3")) {
            x = checkWidth;
            y = checkWheight - 15;
        } else if (type.equals("4")) {
            x = checkWidth - 15;
            y = checkWheight;
        } else {
            return "";
        }
        StringBuilder prevDimension = new StringBuilder();
        prevDimension.append(x);
        prevDimension.append(",");
        prevDimension.append(y);
        if (AllChesses.checkValue(prevDimension.toString())) {
            return "";
        }
        return prevDimension.toString();
    }
    /**
     * <p>[概 要] 前満足の棋子チェック</p>
     * <p>[詳 細]</p>
     * <p>[備 考]</p>
     * @param dimension 画面情報DTO
     * @param checkDimesions 棋盤の中にの棋子 
     * @param type 計算方法(1:y=x±15*n 2:y=15*n+x) 3:y=15*n 4:x=15n
     * @param resultDimesions 満足の棋子リスト
     */
    private static void checkPrevDimension(String dimension,
            Iterator<String> checkDimesions, String type,
            List<String> resultDimesions) {
        int x = 0;
        int y = 0;

        String checkKey[] = dimension.split(",");
        int checkWidth = Integer.parseInt(checkKey[0]);
        int checkWheight = Integer.parseInt(checkKey[1]);
        // 計算方法、前棋子の計算
        if (type.equals("1")) {
            x = checkWidth - 15;
            y = checkWheight - 15;
        } else if (type.equals("2")) {
            x = checkWidth - 15;
            y = checkWheight + 15;
        } else if (type.equals("3")) {
            x = checkWidth;
            y = checkWheight - 15;
        } else if (type.equals("4")) {
            x = checkWidth - 15;
            y = checkWheight;
        } else {
            return;
        }
        if (checkDimesions.hasNext()) {
            String checkDimension = checkDimesions.next();
            String key[] = checkDimension.split(",");
            int width = Integer.parseInt(key[0]);
            int height = Integer.parseInt(key[1]);
            // 同じ方法の次棋子取得時、棋盤の中チェック用棋子削除、満足の棋子リスト追加、削除棋子は現在棋子に格納する、前満足の棋子チェックします
            if (x == width && y == height) {
                resultDimesions.add(checkDimension);
                checkDimesions.remove();
                checkPrevDimension(checkDimension, checkDimesions, type,
                        resultDimesions);
            } else {
                checkDimesions.remove();
                checkPrevDimension(dimension, checkDimesions, type,
                        resultDimesions);
            }
        }
    }
}