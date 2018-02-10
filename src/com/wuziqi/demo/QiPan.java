package com.wuziqi.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class QiPan extends JPanel {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��P�Υ�����
	 */
	private int width;
	/**
	 * ��P�Υ�����
	 */
	private int height;
	/**
	 * ���ӤΥ�����
	 */
	private int radius = 12;
	/**
	 * * �����g��
	 */
	public static int WANGGEWIDTH = 15;
	/**
	 * �\������
	 */
	private List<String> blackDimesions = new ArrayList<String>();
	/**
	 * �פ�����
	 */
	private List<String> whilteDimesions = new ArrayList<String>();
	/**
	 * �����Ƅ���
	 */
	private int xuanshou;
	private boolean initFlg = true;
	public QiPan() {
	}
	public QiPan(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public void init() {
		initFlg = true;
		repaint();
	}
	/**
	 * @param dimesions �O������ dimesions
	 */
	public void addBlackDimension(String dimesion) {
		blackDimesions.add(dimesion);
		AllChesses.updateDimensionKeyValue(dimesion);
	}
	/**
	 * @return blackDimesions
	 */
	public List<String> getBlackDimesions() {
		List<String> tempDimesions = new ArrayList<String>();
		tempDimesions.addAll(blackDimesions);
		return tempDimesions;
	}
	/** 
	 * @param dimesions  �O������ dimesions
	 */
	public void addWhiteDimension(String dimesion) {
		whilteDimesions.add(dimesion);
		AllChesses.updateDimensionKeyValue(dimesion);
	}
	/**
	 * @return whilteDimesions *
	 */
	public List<String> getWhilteDimesions() {
		List<String> tempDimesions = new ArrayList<String>();
		tempDimesions.addAll(whilteDimesions);
		return tempDimesions;
	}
	/**
	 * @return xuanshou *
	 */
	public int getXuanshou() {
		return xuanshou;
	}
	/**
	 * @param xuanshou  �O������ xuanshou
	 */
	public void setXuanshou(int xuanshou) {
		this.xuanshou = xuanshou;
	}
	/**
	 * <p>[�� Ҫ] ��P�ν}</p>
	 * <p>[Ԕ ��]</p>
	 * <p>[�� ��]</p>
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 20; i++) {
			g.drawLine(WANGGEWIDTH, WANGGEWIDTH * (i + 1), width, WANGGEWIDTH
					* (i + 1));
			g.drawLine(WANGGEWIDTH * (i + 1), WANGGEWIDTH, WANGGEWIDTH
					* (i + 1), height);
		}
		if (initFlg) {
			AllChesses.init(this.width, this.height);
			blackDimesions.clear();
			whilteDimesions.clear();
			initFlg = false;
		} else {
			paintblack(g);
			paintWhite(g);
		}
	}
	/**
	 * <p>[�� Ҫ] �\�����Ӥν}</p>
	 * <p>[Ԕ ��]</p>
	 * <p>[�� ��]</p>
	 * @param g
	 */
	private void paintblack(Graphics g) {
		for (String dimesion : blackDimesions) {
			String location[] = dimesion.split(",");
			int width = Integer.parseInt(location[0]);
			int height = Integer.parseInt(location[1]);
			int x = width - radius / 2;
			int y = height - radius / 2;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			RadialGradientPaint p = new RadialGradientPaint(new Point2D.Double(
					width, height), 8 / 2.4f, new float[]{0.0f, 1.0f},
					new Color[]{new Color(255, 255, 255, 180),
							new Color(0, 0, 0, 255)});
			g2d.setPaint(p);
			g2d.fillOval(x, y, radius, radius);
		}
	}
	/**
	 * <p>[�� Ҫ] �פ����Ӥν}</p>
	 * <p>[Ԕ ��]</p>
	 * <p>[�� ��]</p>
	 * @param g
	 */
	private void paintWhite(Graphics g) {
		for (String dimesion : whilteDimesions) {
			String location[] = dimesion.split(",");
			int width = Integer.parseInt(location[0]);
			int height = Integer.parseInt(location[1]);
			int x = width - radius / 2;
			int y = height - radius / 2;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			RadialGradientPaint p = new RadialGradientPaint(new Point2D.Double(
					width, height), 30 / 2.4f, new float[]{0.0f, 1.0f},
					new Color[]{Color.white, Color.white});
			g2d.setPaint(p);
			g2d.fillOval(x, y, radius, radius);
		}
	}
}
