/////////////////////////////////////////////////////////////////////////////
//    Copyright ownership belongs to haodafeng, shall not be reproduced ,  //
// copied, or used in other ways without permission. Otherwise haodafeng     //
// will have the right to pursue legal responsibilities.                   //
//                                                                         //
//    Copyright &copy; 2008 haodafeng. All rights reserved.                //
/////////////////////////////////////////////////////////////////////////////
/**
  * 项目名称：个人财务管理
  * 文件名：GateRegeditAction.java
  * 作者：@郝大锋
  * 公司名称：hao.com
  * 创建时间：2008-12-26
  * 功能描述: 注册Action
  * 版   本: V 1.0
  */
package com.sinoframe.common.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.sinoframe.web.action.BaseAction;

public class Image extends BaseAction{

	private static final long serialVersionUID = 949955909015001618L;
	
	private ByteArrayInputStream inputStream;	
    
    //随机产生一个颜色
    public Color createsRandomColor() {
        int r = (new Double(Math.random() * 256)).intValue();
        int g = (new Double(Math.random() * 256)).intValue();
        int b = (new Double(Math.random() * 256)).intValue();
        return new Color(r, g, b);
    }
    
    //给定范围获得随机颜色
	public Color getRandColor(int fc,int bc){

		//随机生成颜色
		Random random = new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
    
    //生成一个内存图片，将四个随机数写在图片上
    @SuppressWarnings("unchecked")
	public BufferedImage createImage() {
        //生成随机类
        Random random = new Random();
        int width = 60;
        int height = 22;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // 获取图形上下文
        Graphics g = image.getGraphics();
        
        // 设定背景色
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
        
        //画边框
        //g.setColor(Color.black);
        //g.drawRect(0, 0, width - 1, height - 1);
        
        // 将认证码显示到图象中
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++){
	      int x = random.nextInt(width);
	      int y = random.nextInt(height);
	      int xl = random.nextInt(12);
	      int yl = random.nextInt(12);
	      g.drawLine(x,y,x+xl,y+yl);
        }
        
        //使用随机颜色
        String sRand="";
        for (int i=0;i<4;i++){
        	String rand=String.valueOf(random.nextInt(10));
        	sRand+=rand;
        	
        	//将认证码显示到图象中
        	g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
        	g.drawString(rand,13*i+6,16);
        }
        
        //将产生的字符串写入session，供校验时使用
        setSession("validateCode", sRand);
        //System.out.println(sRand);
        
        // 图象生效
        g.dispose();
        
        return image;
    }
    
    //将图片的以字节形式写到InputStream里
    @SuppressWarnings("unchecked")
	public ByteArrayInputStream createInputStream() throws Exception {
    	
        //获取随机字符串        
        BufferedImage image = this.createImage();
               
        //this.getSession().put("validateCode", str);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
        ImageIO.write(image, "JPEG", imageOut);
        imageOut.close();
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        output.close();
        return input;
    }
    
    public String execute() throws Exception {
        setInputStream(createInputStream());
        return SUCCESS;
    }
    
    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }
    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }
}