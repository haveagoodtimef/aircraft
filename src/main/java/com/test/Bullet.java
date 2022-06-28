package com.test;

//子弹
public class Bullet extends FlyingObject {
    private int speed = 3;

    public Bullet(int x, int y) {
        image = ImageLoader.bullet;
        width = image.getWidth();
        height = image.getHeight();
        this.x = x;
        this.y = y;
    }
    public void step() {
        y -= speed;
    }

    public boolean outOfBounds() {
        return this.y<=-this.height;
    }

    @Override
    protected void destroy() {

    }
}
