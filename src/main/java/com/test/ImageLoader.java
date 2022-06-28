package com.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage bullet;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage bigplane;
    public static BufferedImage airplane;
    public static BufferedImage airplane_ember0;
    public static BufferedImage airplane_ember1;
    public static BufferedImage airplane_ember2;
    public static BufferedImage airplane_ember3;
    public static BufferedImage bee;
    public static BufferedImage hero1;
    public static BufferedImage hero0;

    static {
        try{
            background = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"background.png"));
            start = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"start.png"));
            bullet = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"bullet.png"));
            pause = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"pause.png"));
            gameover = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"gameover.png"));
            bigplane = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"bigplane.png"));
            airplane = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"airplane.png"));
            airplane_ember0 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"airplane_ember0.png"));
            airplane_ember1 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"airplane_ember1.png"));
            airplane_ember2 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"airplane_ember2.png"));
            airplane_ember3 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"airplane_ember3.png"));
            bee = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"bee.png"));
            hero1 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"hero1.png"));
            hero0 = ImageIO.read(ShootGame.class.getResourceAsStream("/"+"hero0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
