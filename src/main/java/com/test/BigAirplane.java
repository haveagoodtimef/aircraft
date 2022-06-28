package com.test;

import java.awt.image.BufferedImage;
import java.util.Random;

//敌机是飞行物也是敌人
public class BigAirplane extends FlyingObject implements Enemy{
    private int speed = 1; //移动速度
    private BufferedImage[] images;
    private int index = 0;

    public BigAirplane(){
        image = ImageLoader.bigplane;
        width = image.getWidth();
        height = image.getHeight();
        Random rand = new Random();
        x = rand.nextInt(GamePanel.WIDTH - this.width);
        y = -this.height;
        //爆炸的图片
        images = new BufferedImage[]{
                ImageLoader.airplane_ember0,
                ImageLoader.airplane_ember1,
                ImageLoader.airplane_ember2,
                ImageLoader.airplane_ember3};
    }
    public int getScore() {
        return 5;
    }

    //实现爆炸图片切换
    @Override
    public void destroy() {
//		image = images[0];或者image = images[1];
        image = images[index++/10%images.length];//100个毫秒切换一次
    }

    public void step() {
        y += speed;
        if(dest){
            image = images[index++/10%images.length];//100个毫秒切换一次
        }
    }



    public boolean outOfBounds() {
        return this.y >= GamePanel.HEIGHT;
    }
}