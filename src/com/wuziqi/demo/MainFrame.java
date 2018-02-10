package com.wuziqi.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    /**     
     * 
     */
    private static final long serialVersionUID = 1L;
    public MainFrame() {
        setVisible(true);
        setTitle("五子棋");
        setLocationRelativeTo(null);
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        init();
    }
    private void init() {
        final QiPan qiPan = new QiPan(300, 300);
        qiPan.setBackground(Color.gray);
        qiPan.setPreferredSize(new Dimension(300 + QiPan.WANGGEWIDTH,
                300 + QiPan.WANGGEWIDTH));
        // mouseはclicked場合、処理実行
        qiPan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // mouseのclicked後、X位置
                int xpoints = e.getX() / QiPan.WANGGEWIDTH;
                double xDeviation = e.getX() % QiPan.WANGGEWIDTH;
                // mouseのclicked後、Ｙ位置
                int ypoints = e.getY() / QiPan.WANGGEWIDTH;
                double yDeviation = e.getY() % QiPan.WANGGEWIDTH;
                // 棋子位置以外場合、半分以上場合、位置は次の棋子位置移動します
                if (xDeviation >= 7.5) {
                    xpoints = xpoints + 1;
                }
                if (yDeviation >= 7.5) {
                    ypoints = ypoints + 1;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(QiPan.WANGGEWIDTH * xpoints);
                sb.append(",");
                sb.append(QiPan.WANGGEWIDTH * ypoints);
                String dimesion = sb.toString();
                // 棋盤中に、棋子存在場合、何もない実行します
                if (AllChesses.exitCheck(dimesion)) {
                    return;
                }
                // 黒い選手時、
                if (qiPan.getXuanshou() == 0) {
                    qiPan.addBlackDimension(dimesion);
                    // 白い選手に変更します
                    qiPan.setXuanshou(1);
                    // gameのルルチェック、winner場合、実行終わります
                    qiPan.repaint();
                    boolean result = GameRule.checkWin(dimesion,
                            qiPan.getBlackDimesions());
                    if (result) {
                        JOptionPane.showMessageDialog(null,
                                "YOU ARE THE WINNER", "Congratulation",
                                JOptionPane.INFORMATION_MESSAGE);
                        qiPan.init();
                    } else {
                        dimesion = TheComputer.moveChess(
                                qiPan.getBlackDimesions(),
                                qiPan.getWhilteDimesions());
                        if (AllChesses.exitCheck(dimesion)) {
                            return;
                        }
                        qiPan.setXuanshou(0);
                        qiPan.addWhiteDimension(dimesion);
                        qiPan.repaint();
                        result = GameRule.checkWin(dimesion,
                                qiPan.getWhilteDimesions());
                        if (result) {
                            JOptionPane.showMessageDialog(null,
                                    "YOU ARE LOSED", "FAIL",
                                    JOptionPane.INFORMATION_MESSAGE);
                            qiPan.init();
                        }
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(new JPanel(), new GridBagConstraints(0, 0, 1, 1, 0.1, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                        0, 0, 0, 0), 0, 0));
        add(qiPan, new GridBagConstraints(0, 1, 1, 1, 0.8, 0.8,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        0, 0, 0, 0), 0, 0));
        add(new JPanel(), new GridBagConstraints(0, 2, 1, 1, 0.1, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                        0, 0, 0, 0), 0, 0));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
