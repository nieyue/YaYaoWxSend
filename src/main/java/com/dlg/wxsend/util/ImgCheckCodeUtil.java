package com.dlg.wxsend.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * 图片验证码
 * 生成简单的验证码：字母加数字
 * 1、通过BufferedImage绘制图片，把并随机生成的字母、数字绘制到图片上
 * 		通过ImageIO.write()写到resp的输出流中，在页面上直接在img 标签 的src想显示 
 * 2、把生成的随机验证码保存到session中
 * 3、比较用户输入的form表单中的验证码、和session中保存的验证码是否一致
 */
public class ImgCheckCodeUtil {
	// 验证码字符集  
    private static final char[] chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
    // 字符数量  
    private static final int SIZE = 4;  
    // 干扰线数量  
    private static final int LINES = 8;   
    // 宽度  
    private static final int WIDTH = 135;  
    // 高度  
    private static final int HEIGHT = 50;  
    // 字体大小  
    private static final int FONT_SIZE = 30;  
  
    /** 
     * 生成随机验证码及图片 
     * Object[0]：验证码字符串； 
     * Object[1]：验证码图片。 
     */  
    public static Object[] createImage() {  
        StringBuffer sb = new StringBuffer();  
        // 1.创建空白图片  
        BufferedImage image = new BufferedImage(  
            WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);  
        // 2.获取图片画笔  
        Graphics graphic = image.getGraphics();  
        // 3.设置画笔颜色  、验证码图片的背景颜色
        Color c=new Color(230, 230,230);
        graphic.setColor(c);  
        // 4.绘制矩形背景  
        graphic.fillRect(0, 0, WIDTH, HEIGHT);  
        // 5.画随机字符  
        Random ran = new Random();  
        for (int i = 0; i <SIZE; i++) {  
            // 取随机字符索引  
            int n = ran.nextInt(chars.length);  
            // 设置随机颜色  
            graphic.setColor(getRandomColor());  
            // 设置字体大小  
            graphic.setFont(new Font(  
                null, Font.BOLD + Font.ITALIC, FONT_SIZE));  
            // 画字符  
            graphic.drawString(  
                chars[n] + "", i * WIDTH / SIZE, HEIGHT*2/3);  
            // 记录字符  
            sb.append(chars[n]);  
        }  
        // 6.画干扰线  
        for (int i = 0; i < LINES; i++) {  
            // 设置随机颜色  
            graphic.setColor(getRandomColor());  
            // 随机画线  
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),  
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));  
        }  
        // 7.返回验证码和图片  
        return new Object[]{sb.toString(), image};  
    }  
  
    /** 
     * 随机取色 
     */  
    public static Color getRandomColor() {  
        Random ran = new Random();  
        Color color = new Color(ran.nextInt(256),   
                ran.nextInt(256), ran.nextInt(256));  
        return color;  
    }  
      
    public static void main(String[] args) throws IOException {  
        Object[] objs = createImage();  
        System.out.println(objs[0]+"---"+objs[1]);
        BufferedImage image = (BufferedImage) objs[1];  
        OutputStream os = new FileOutputStream("d:/1.png");  
        ImageIO.write(image, "png", os);  
        os.close(); 
    }
		
		  
}

