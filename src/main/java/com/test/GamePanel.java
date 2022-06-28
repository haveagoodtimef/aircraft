package com.test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏面板的创建
 * 可以改名称
 */
public class GamePanel extends JPanel {
    //窗体的大小
    public static final int WIDTH = 400;
    public static final int HEIGHT = 680;

    //创建英雄战机
    private Hero hero; //创建英雄机

    private int score = 0;

    private FlyingObject[] flyings = {};  //敌人
    private Bullet[] bullets = {};  //子弹数组

    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public GamePanel(String gameName) {
        this.gameName = gameName;
    }

    public GamePanel(Hero hero) {
        this.hero = hero;
    }

    public GamePanel(Hero hero, String gameName) {
        this.hero = hero;
        this.gameName = gameName;
    }

    public GamePanel() {
    }


    /**
     * 画一个面板
     */
    public void paintPanel(){
        JFrame frame ;
        if(gameName != null && !gameName.equals("")){
            frame = new JFrame(gameName);
        }else{
            frame = new JFrame("课工场-飞机大战");
        }
        frame.add(this);
        frame.setSize(WIDTH, HEIGHT);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //重载paint 方法,调用画笔Graphics
    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageLoader.background, 0, 0, null); //画背景
        paintHero(g);  //画英雄机
        paintFlyingObjects(g);  //画飞行物
        paintBullets(g); //画子弹
        paintScoreAndLife(g); //画生命
        paintState(g); //画状态
    }
    //开始执行
    public void action(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //如果是运行的状态,那么就开始进场了
                if(GameState.currentState == GameState.RUNNING){
                    enterAction(); //都进场..就是画出所有的图行
                    stepAction();//都走一走,画面动起来
                    shootAction();  //子弹入场
                    outOfBoundsAction(); //删除越界飞行物(没有消灭的飞机)
                    bangAction(); //子弹与敌人(敌机+小蜜蜂)的碰撞 //变化图片
                    hitAction(); //碰撞后的处理
                    checkGameOverAction();//判断游戏是否结束
                }
                //重复调用paint
                repaint();
            }
        },0,10);
    }
    /*
    * // 删除越界的飞行物
    * 1)声明活着的数组
         2)遍历flyings/bullets数组
           判断对象是否不越界:
           若true:
             将对象添加到活着的数组中
         3)将活着的数组复制到flyings/bullets数组中
    */
    public void outOfBoundsAction(){
        int index = 0; //定义一个下标
        FlyingObject[] flyinglives = new FlyingObject[flyings.length];//创建一个飞行物个数的飞行物对象.
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];  //父类引用指向子类对象
            if(!f.outOfBounds()) {   //判断是否越界
                flyinglives[index] = f;  //保存所有不越界的.
                index++;
            }
        }
        flyings = Arrays.copyOf(flyinglives, index); //保存新的飞行物数组.

        index = 0;
        Bullet[] bulletlives = new Bullet[bullets.length];
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            if(!b.outOfBounds()){
                bulletlives[index] = b;
                index++;
            }
        }
        bullets = Arrays.copyOf(bulletlives, index);  //创建新的子弹数组
    }

    //
    public void paintScoreAndLife(Graphics g) {
        g.setColor(new Color(255,0,0));
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
        g.drawString("SCORE:" + score, 20, 20);
        g.drawString("LIFE: "+hero.getLife(), 20, 40);
    }


    /**
     *  状态的改变
     */
    public void paintState(Graphics g) {
        switch(GameState.currentState){
            case GameState.PREPARE:
                g.drawImage(ImageLoader.start, 0, 0, null);
                break;
            case GameState.PAUSE:
                g.drawImage(ImageLoader.pause, 0, 0, null);
                break;
            case GameState.GAME_OVER:
                g.drawImage(ImageLoader.gameover, 0, 0, null);
                break;
            default:
        }
    }


    //画子弹
    public void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.length; i++) {
            Bullet b = bullets[i];
            g.drawImage(b.image, b.x, b.y, null);
        }
    }

    //随机生成一个飞行物
    public FlyingObject nextOne() {
        Random rand = new Random();
        int index = rand.nextInt(20);
        if(score % 11 == 0){
            return new BigAirplane();
        }
        if(index < 5) {
            return new Bee();
        }else {
            return new Airplane();
        }
    }

    //敌人入场
    int flyEnteredIndex = 0;
    public void enterAction(){
        flyEnteredIndex++;
        if(flyEnteredIndex%40 == 0){    //400毫秒运行一次
            FlyingObject obj = nextOne();    //随机传入一个敌人
            flyings = Arrays.copyOf(flyings, flyings.length+1);    //扩容飞行物数组
            flyings[flyings.length-1] = obj;   //添加到数组最后一个
        }
    }

    //画飞行物
    public void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            g.drawImage(f.image, f.x, f.y, null);
        }
    }

    //画英雄
    public void paintHero(Graphics g) {
        g.drawImage(hero.image, hero.x, hero.y, null);
    }

    public void bangAction() {
        for (int i = 0; i < bullets.length; i++) { //先遍历所有子弹
            Bullet b = bullets[i];
            bang(b); //为了程序可读性,拆分为小方法
        }
    }

    //判断一个子弹是否有与敌人撞
    public void bang(Bullet b) {
        int index = -1; //索引下标
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject f = flyings[i];
            if(f.shootBy(b)){  //调用坐标判断方法
                index = i;
                break;
            }
        }
        if(index != -1) { //如果撞上了,与数组最后一个元素交换并删除最后一个元素
            FlyingObject one = flyings[index];
            FlyingObject t = flyings[index];
            flyings[index] = flyings[flyings.length -1];
            flyings[flyings.length-1] = t;
            flyings = Arrays.copyOf(flyings, flyings.length-1);  //飞行物数组缩容.


            if(one instanceof Enemy) {  //如果是敌人就加分
                Enemy e = (Enemy)one;
                score += e.getScore();
            }
            if(one instanceof Award) { //如果是小蜜蜂就给奖励
                Award a = (Award)one;
                int type = a.getType();
                switch(type){
                    case Award.DOUBLE_FIRE:
                        hero.addDoubleFire();
                        break;
                    case Award.LIFE:
                        hero.addLife();
                        break;
                }
            }
        }
    }



    //飞行物实现移动
    public void stepAction() {
        hero.step(); //英雄机的移动跟随鼠标
        for (int i = 0; i < flyings.length; i++) {  //遍历飞行物数组
            flyings[i].step();
        }
        for (int i = 0; i < bullets.length; i++) {  //遍历子弹数组
            bullets[i].step();
        }
    }



    //碰撞后的处理(不是子弹击中)
    public void hitAction() {
        for (int i = 0; i < flyings.length; i++) {
            FlyingObject obj = flyings[i];
            if(hero.hit(obj)){
                hero.subtractLife();
                hero.clearDoubleFire();

                //敌机销毁
                FlyingObject t = flyings[i];
                flyings[i] = flyings[flyings.length -1];
                flyings[flyings.length-1] = t;
                flyings = Arrays.copyOf(flyings, flyings.length-1);

                //子弹消失
//                Bullet bs = bullets[i];
//                bullets[i] = bullets[bullets.length -1];
//                bullets[bullets.length -1] = bs;
//                bullets = Arrays.copyOf(bullets, bullets.length-1);

            }
        }
    }

    //判断游戏是否结束
    public void checkGameOverAction() {
        if (hero.getLife() <= 0) {
            GameState.currentState = GameState.GAME_OVER;
            //重置英雄机属性
            hero.init();
            //重置分数
            score = 0;

            //清空所有数据
            flyings = new FlyingObject[0];
            bullets = new Bullet[0];

        }
    }

    //子弹的入场
    int shootIndex = 0;
    public void shootAction() {
        shootIndex++;
        if(shootIndex%30 == 0) { //控制子弹发射速度.300毫秒一次
            Bullet[] bs = hero.shoot(); //生成子弹 ,可以是一个.也可以是多个.
            bullets = Arrays.copyOf(bullets, bullets.length + bs.length); //要先扩容,然后现实追加.固定格式.
            System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);// 这是数组的追加
        }
    }

}
