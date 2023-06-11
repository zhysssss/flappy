package com.bird.main;

/*
    This is a java class of game background
 */


import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBackGround {
    // background image
    private BufferedImage bkimg;

    public GameBackGround(){
        bkimg = GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
    }

    // draw image
    public void draw(Graphics g){

        //add background color
        g.setColor(Constant.BK_COLOR);
        g.fillRect(0,0,Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        g.setColor(Color.BLACK);

        // get image size
        int height = bkimg.getHeight();
        int width = bkimg.getWidth();

        // needed amount of images
        int count = Constant.FRAM_WIDTH / width + 1;
        for (int i = 0; i < count; i++) {
            // (x,y) -> top-left of the image
            g.drawImage(bkimg, width*i, Constant.FRAM_HEIGHT - height, null);
        }
    }

}
