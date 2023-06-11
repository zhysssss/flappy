package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {

    // rectangle
    private Rectangle rect;

    // for moving barrier, control moving up or down
    private boolean mob;

    // barrier images
    private static BufferedImage[] imgs;

    // speed
    private int speed = 3;

    // existence of a barrier
    private boolean visible;

    static {
        final int COUNT = 3;
        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    // location
    private int x, y;

    // height and width
    private int width, height;

    // barrier types
    private int type;
    public static final int TYPE_TOP_NORMAL = 0; // first type of barrier
    public static final int TYPE_BOTTOM_NORMAL = 2; // second type of barrier
    public static final int TYPE_HOVER_NORMAL = 4; // third type of barrier (hover type)
    public static final int TYPE_HOVER_MOVING = 6; // fourth type of barrier (moving type)

    // get width and height of barrier
    public static final int BARRIER_WIDTH = imgs[0].getWidth(); // hover one
    public static final int BARRIER_HEIGHT = imgs[0].getHeight(); // hover one
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();

    public Barrier() {
        rect = new Rectangle();
    }

    public Barrier(int x, int y, int height, int type) {
        this.x = x;
        this.y = y;
        this.width = BARRIER_WIDTH;
        this.height = height;
        this.type = type;

    }

    // draw barrier according to its type
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopDown(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawDownTop(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHovered(g);
                break;
            case TYPE_HOVER_MOVING:
                drawMoving(g);
                break;
        }
    }

    // top-down barrier type
    private void drawTopDown(Graphics g) {
        // needed number of barrier
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT + 1;

        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIER_HEIGHT, null);
        }

        // draw head
        int y = height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    private void drawDownTop(Graphics g) {
        int count = height / BARRIER_HEIGHT + 1;

        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAM_HEIGHT - i * BARRIER_HEIGHT, null);
        }

        // draw head
        int y = Constant.FRAM_HEIGHT - height;
        g.drawImage(imgs[1], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    private void drawHovered(Graphics g) {
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;

        //draw upper head
        g.drawImage(imgs[1], x, y, null);

        //draw body
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }

        // draw lower head
        int y11 = y + height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y11, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    private void drawMoving(Graphics g) {
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;

        //draw upper head
        g.drawImage(imgs[1], x, y, null);

        //draw body
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }

        // draw lower head
        int y11 = y + height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y11, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);

        if(mob){
            y += 4;
            if(y >= 280){
                mob = false;
            }
        }else if (!mob){
            y -= 4;
            if(y <= 50){
                mob = true;
            }
        }
    }


    // draw rect
    public void rect(Graphics g) {
        int x1 = this.x;
        int y1 = this.y;
        int w1 = imgs[0].getWidth();
//        g.setColor(Color.blue);
//        g.drawRect(x1, y1, w1, height);
        setRectangle(x1, y1, w1, height);
    }

    // rect parameters
    public void setRectangle(int x, int y, int width, int height) {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    // when to draw next series of barriers
    public boolean isInFrame() {
        return 600 - x > 150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }
}
