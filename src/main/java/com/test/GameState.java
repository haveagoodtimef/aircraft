package com.test;

/**
 * 游戏的状态
 */
public class GameState {
    //准备
    public static final int PREPARE = 0;
    //运行中
    public static final int RUNNING = 1;
    //暂停中
    public static final int PAUSE = 2;
    //游戏结束
    public static final int GAME_OVER = 3;
    //当前的状态
    public static int currentState = PREPARE;

}
