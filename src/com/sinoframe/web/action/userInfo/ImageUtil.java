/**
 *  陈蕾蕾
 *  验证码类
 */

package com.sinoframe.web.action.userInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageUtil {

	// 在验证码的信息
	//static char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static char[] chars = "1234567890".toCharArray();

	private static final int SIZE = 4;

	private static final int HEIGHT = 36;

	private static final int WIDTH = 100;
	
	private static final int LINESIZE = 30;

	public static Map<String, BufferedImage> createImage() {
		StringBuilder sb = new StringBuilder();
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);  //TYPE_USHORT_GRAY    TYPE_USHORT_565_RGB   TYPE_USHORT_565_RGB
		Graphics graphics = bi.getGraphics();
		// 验证码背景颜色
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		Random rm = new Random();
		drawImage(sb, graphics, rm);
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(sb.toString(), bi);
		return map;
	}

	private static void drawImage(StringBuilder sb, Graphics graphics, Random rm) {
		for (int i = 0; i < SIZE; i++) {
			String c = String.valueOf(chars[rm.nextInt(chars.length)]);
			sb.append(c);
			graphics.setColor(Color.black);
			graphics.setFont(new Font(null,Font.LAYOUT_RIGHT_TO_LEFT|Font.ITALIC,18));
			int x = WIDTH/SIZE;
			int y = HEIGHT - (HEIGHT-20)/2;
			graphics.drawString(c, i*x+2, y);
		}
		
		for(int i=0; i<LINESIZE; i++){
			setColor(graphics, rm);
			//graphics.setClip(30, 30, 10, 10);
			//graphics.drawLine(rm.nextInt(WIDTH), rm.nextInt(HEIGHT), rm.nextInt(WIDTH), rm.nextInt(HEIGHT));
			graphics.fillOval(rm.nextInt(WIDTH), rm.nextInt(HEIGHT), 2, 2);
		}
	}

	private static void setColor(Graphics graphics, Random rm) {
		graphics.setColor(new Color(rm.nextInt(255),rm.nextInt(255),rm.nextInt(255)));
	}
}
