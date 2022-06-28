package com.test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {

    private Hero hero;
    private FlyingObject[] flyings;  //敌人
    private Bullet[] bullets;  //子弹数组

    public MyMouseAdapter(Hero hero) {
        this.hero = hero;
    }

    //鼠标移动
    public void mouseMoved(MouseEvent e){
        if(GameState.currentState == GameState.RUNNING){
            int x = e.getX();
            int y = e.getY();
            hero.moveTo(x, y);
        }
    }
    //鼠标点击
    public void mouseClicked(MouseEvent e){
        switch(GameState.currentState) {
            case GameState.PREPARE:
                GameState.currentState = GameState.RUNNING;
                break;
            case GameState.GAME_OVER:
                GameState.currentState = GameState.PREPARE;
                break;
        }
    }
    //鼠标移出
    public void mouseExited(MouseEvent e){
        if(GameState.currentState == GameState.RUNNING){
            GameState.currentState = GameState.PAUSE;
        }
    }
    //鼠标移入
    public void mouseEntered(MouseEvent e){
        if(GameState.currentState == GameState.PAUSE){
            GameState.currentState = GameState.RUNNING;
        }
    }
}
