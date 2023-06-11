package com.bird.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud {
    // images
    private BufferedImage img;

    // cloud speed
    private int speed;

    // cloud location
    private int x,y;

    public Cloud(){}

    public Cloud(BufferedImage img, int speed, int x, int y){
        this.img = img;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        x -= speed;
        g.drawImage(img, x, y,null);
    }

    // check if cloud is out of screen
    public boolean isOutFrame(){
        return x < -100;
    }


}
