package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFrontGround {
    // cloud numbers
    private static final int CLOUD_COUNT = 2;
    // clouds' container
    private List<Cloud> clouds;
    // cloud speed
    public static final int CLOUD_SPEED = 1;
    // image resources
    private BufferedImage[] img;

    // randomly generate clouds
    private Random random;

    public GameFrontGround(){
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUD_COUNT];

        for (int i = 0; i < CLOUD_COUNT; i++) {
            img[i] = GameUtil.loadBufferedImage("img/cloud"+i+".png");
        }
        random = new Random();
    }

    // draw clouds
    public void draw(Graphics g){
        logic();

        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).draw(g);
        }
    }

    // control clouds counts
    private void logic(){
        if((int)(500 * Math.random()) < 8){
            Cloud cloud = new Cloud(img[random.nextInt(CLOUD_COUNT)], CLOUD_SPEED, Constant.FRAM_WIDTH, random.nextInt(150));
            clouds.add(cloud);
        }
        for (int i = 0; i < clouds.size(); i++) {
            Cloud cloud = clouds.get(i);
            if(cloud.isOutFrame()){
                clouds.remove(i);
                i--;
                System.out.println("Cloud is removed!!");
            }

        }
    }

}
