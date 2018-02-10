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
	 * 棋盤のサイズ
	 */
	private int width;
	/**
	 * 棋盤のサイズ
	 */
	private int height;
	/**
	 * 棋子のサイズ
	 */
	private int radius = 12;
	/**
	 * * 棋子間中
	 */
	public static int WANGGEWIDTH = 15;
	/**
	 * 黒い棋子
	 */
	private List<String> blackDimesions = new ArrayList<String>();
	/**
	 * 白い棋子
	 */
	private List<String> whilteDimesions = new ArrayList<String>();
	/**
	 * 棋子移動者
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
	 * @param dimesions 設定する dimesions
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
	 * @param dimesions  設定する dimesions
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
	 * @param xuanshou  設定する xuanshou
	 */
	public void setXuanshou(int xuanshou) {
		this.xuanshou = xuanshou;
	}
	/**
	 * <p>[概 要] 棋盤の絵</p>
	 * <p>[詳 細]</p>
	 * <p>[備 考]</p>
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
	 * <p>[概 要] 黒い棋子の絵</p>
	 * <p>[詳 細]</p>
	 * <p>[備 考]</p>
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
	 * <p>[概 要] 白い棋子の絵</p>
	 * <p>[詳 細]</p>
	 * <p>[備 考]</p>
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
