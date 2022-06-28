package com.test;

/**
 * 启动类
 * 不带参数的用默认名字
 * 带参数的用自定义名称
 */
public class ShootGame {

    public static void start(){
        //创建一个英雄
        Hero hero = new Hero();

        //绘制面板
        GamePanel gamePanel = new GamePanel(hero);
        gamePanel.paintPanel();

        //绘制开始
        gamePanel.action();

        //设置鼠标监听
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(hero);
        gamePanel.addMouseListener(myMouseAdapter);
        gamePanel.addMouseMotionListener(myMouseAdapter);
    }

    public static void start(String name){
        //创建一个英雄
        Hero hero = new Hero();

        //绘制面板
        GamePanel gamePanel = new GamePanel(hero,name);
        gamePanel.paintPanel();

        //绘制开始
        gamePanel.action();

        //设置鼠标监听
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(hero);
        gamePanel.addMouseListener(myMouseAdapter);
        gamePanel.addMouseMotionListener(myMouseAdapter);
    }
}
