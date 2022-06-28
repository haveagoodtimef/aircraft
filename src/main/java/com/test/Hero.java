package com.test;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {

    private int doublefire;
    private int life;
    private BufferedImage[] images;
    private int index;

    public void setLife(int life) {
        this.life = life;
    }

    //重新初始化信息
    public void init(){
        doublefire = 0;
        life = 3;
        index = 0;
    }

    public Hero() {
        image = ImageLoader.hero0;
        width = image.getWidth();
        height = image.getHeight();
        x = 150;
        y = 400;
        doublefire = 0;
        life = 3;
        images = new BufferedImage[]{ImageLoader.hero0,ImageLoader.hero1};
        index = 0;
    }

    @Override
    protected void destroy() {

    }

    //实现英雄图片切换
    public void step() {
//		image = images[0];或者image = images[1];
        image = images[index++/10%images.length];//100个毫秒切换一次
    }

    //生成子弹数组 ,子弹的坐标是相对于飞机的坐标来定义的.
    public Bullet[] shoot() {
        int xStep = this.width/4; //把飞机分成4分,定义子弹出现的位置
        int yStep = 20;
        //火力的大小
        Bullet[] bs;
        if(doublefire > 0){    //判断是否是双火力
            bs = new Bullet[2];
            bs[0] = new Bullet(this.x + 1*xStep, this.y - yStep); //定义子弹出现的位置.
            bs[1] = new Bullet(this.x + 3*xStep, this.y - yStep);
//            bs[2] = new Bullet(this.x + 2*xStep, this.y - yStep);
//            bs[3] = new Bullet(this.x + 4*xStep, this.y - yStep);
//            bs[4] = new Bullet(this.x + 5*xStep, this.y - yStep);

            doublefire -= 2;
        }else {
            bs = new Bullet[1];
            bs[0] = new Bullet(this.x + 2*xStep, this.y - yStep);//定义子弹出现的位置.
        }
        return bs;
    }

    //把英雄的位置定义到图片中心,不至于鼠标吊着飞机
    public void moveTo(int x, int y) {
        this.x = x - this.width/2;
        this.y = y - this.height/2;
    }

    /*
     * 定义是否越界
     * 不可以不删除.否则就没有了,应该越界就定义为暂停.
     * @see FlyingObject#outOfBounds()
     */
    public boolean outOfBounds() {
//		return x < 0 || x > ShootGame.WIDTH - width || y < 0
//				|| y > ShootGame.HEIGHT - height;
        return false;
    }

    public void addLife(){
        life++;
    }

    public void subtractLife() {
        life--;
    }
    public int getLife() {
        return life;
    }

    public void addDoubleFire(){
        doublefire += 40;
    }

    public void clearDoubleFire() {
        doublefire = 0;
    }

    public boolean hit(FlyingObject other) {
        int x1 = other.x - this.width/2;
        int x2 = other.x +other.width + this.width/2;
        int y1 = other.y - this.height/2;
        int y2 = other.y +other.height + this.height/2;
        int x = this.x + this.width/2;
        int y = this.y + this.height/2;
        return x >= x1 && x <= x2 && y>= y1 && y <= y2;
    }
}

