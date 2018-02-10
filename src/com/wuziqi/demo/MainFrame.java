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
        setTitle("������");
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
        // mouse��clicked���ϡ��I��g��
        qiPan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // mouse��clicked�ᡢXλ��
                int xpoints = e.getX() / QiPan.WANGGEWIDTH;
                double xDeviation = e.getX() % QiPan.WANGGEWIDTH;
                // mouse��clicked�ᡢ��λ��
                int ypoints = e.getY() / QiPan.WANGGEWIDTH;
                double yDeviation = e.getY() % QiPan.WANGGEWIDTH;
                // ����λ��������ϡ�������ψ��ϡ�λ�äϴΤ�����λ���ƄӤ��ޤ�
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
                // ��P�Фˡ����Ӵ��ڈ��ϡ��Τ�ʤ��g�Ф��ޤ�
                if (AllChesses.exitCheck(dimesion)) {
                    return;
                }
                // �\���x�֕r��
                if (qiPan.getXuanshou() == 0) {
                    qiPan.addBlackDimension(dimesion);
                    // �פ��x�֤ˉ�����ޤ�
                    qiPan.setXuanshou(1);
                    // game�Υ������å���winner���ϡ��g�нK���ޤ�
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
