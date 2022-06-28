package com.test;

import java.awt.image.BufferedImage;

//所有的飞行物都有的属性
public abstract class FlyingObject {
    protected BufferedImage image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected boolean dest;
    protected abstract void destroy();

    public void setDest(boolean dest) {
        this.dest = dest;
    }

    public abstract void step();

    public abstract boolean outOfBounds();//是否越界

    public boolean shootBy(Bullet bullet){
//		int x1 = this.x;
//		int x2 = this.x + this.width;
//		int y1 = this.y;
//		int y2 = this.y + this.height;

        int x = bullet.x;  //子弹横坐标
        int y = bullet.y;  //子弹纵坐标
        return  this.x<x && x<this.x+width && this.y<y && y<this.y+height;
//		return  obj.x>=x1 && obj.x<=x2  && obj.y>=y1 && obj.y<=y2;

    }


}

