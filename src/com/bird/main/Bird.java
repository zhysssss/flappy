package com.bird.main;

import static com.bird.util.Constant.*;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird {

    // matrix
    private Rectangle rect;

    private int acceleration;

    // if still alive
    public boolean life = true;

    // bird images
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;

    // bird flying states
    private int state;
    public static final int STATE_NORMAL = 0; // fly normally
    public static final int STATE_UP = 1; // go up
    public static final int STATE_DOWN = 2; // go down

    // bird moving direction - up and down
    private boolean up = false, down = false;

    //bird moving speed
    private int speed = 4;

    // bird location
    private int x = 200, y = 200;

    public Bird() {
        images = new BufferedImage[BIRD_IMG_COUNT];
        for (int i = 0; i < BIRD_IMG_COUNT; i++) {
            images[i] = GameUtil.loadBufferedImage(BIRD_IMG[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w, h);
    }

    // draw bird
    public void draw(Graphics g) {
        flyLogic();
        g.drawImage(images[state], x, y, null);

        // draw rect
//        g.drawRect(x,y,(int)rect.getWidth(), rect.height);
        rect.x = this.x;
        rect.y = this.y;
    }

    // control bird flying direction
    public void flyLogic() {
        if (up) {
            acceleration--;
            y += acceleration;
            if(acceleration < -5){
                acceleration = -5;
            }

            if (y < 20) {
                y = 20;
                acceleration = 0;
            }
        }
        if (!up) {
            acceleration++;
            y += acceleration;
            if(acceleration > 5){
                acceleration = 5;
            }
            if (y > 475) {
                y = 475;
                acceleration = 0;
            }
        }
    }

    public void fly(int fly) {
        switch (fly) {
            case 1:
                state = 1;
                up = true;
                break;
            case 5:
                state = 2;
                up = false;
                break;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void restartDraw(){
        life = true;
    }
}
