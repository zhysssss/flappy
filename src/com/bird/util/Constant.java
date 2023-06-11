package com.bird.util;

import java.awt.*;

public class Constant {

    // window size
    public static final int FRAM_WIDTH=600;
    public static final int FRAM_HEIGHT=500;

    //window title
    public static final String FRAM_TITLE="Flappy Bird";

    //window location
    public static final int FRAM_X=200;
    public static final int FRAM_Y=200;

    //image path
    public static final String BK_IMG_PATH = "./img/bird_bk.png";

    // background color
    public static final Color BK_COLOR = new Color(0x4B4CF);

    // bird image resource
    public static final String[] BIRD_IMG = {"img/bird_normal.png", "img/bird_up.png", "img/bird_down.png"};

    // barrier image resources
    public static final String[] BARRIER_IMG_PATH = {"img/barrier.png", "img/barrier_up.png", "img/Barrier_down.png"};
}
