package com.bird.main;

import static com.bird.util.Constant.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame {

    // create gamebackground object
    private GameBackGround gameBackGround;

    //create bird object
    private Bird bird;

    //create gamefrontground object
    private GameFrontGround gameFrontGround;

    //create gamebarrier object
    private GameBarrierLayer gameBarrierLayer;

    // buffer image, prevent window flashing
    private BufferedImage buffimg = new BufferedImage(FRAM_WIDTH, FRAM_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    // constructor
    public GameFrame(){

        // window is visible
        setVisible(true);

        // window size
        setSize(FRAM_WIDTH, FRAM_HEIGHT);

        // title
        setTitle(FRAM_TITLE);

        // window location
        setLocation(FRAM_X, FRAM_Y);

        //window size unchangeable
        setResizable(false);

        // window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // exit window
            }
        });

        initGame();

        new run().start();

        // add keyboard listener, to change the state of bird
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }
        });
    }

    // init game
    public void initGame(){
        gameBackGround = new GameBackGround();
        bird = new Bird();
        gameFrontGround = new GameFrontGround();
        gameBarrierLayer = new GameBarrierLayer();
    }

    class run extends Thread{
        @Override
        public void run() {
            while(true){
                repaint(); // call update() function
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    // update window
    @Override
    public void update(Graphics g){
        if(bird.life){
            // get pen
            Graphics graphics = buffimg.getGraphics();

            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gameFrontGround.draw(graphics);
            gameBarrierLayer.draw(graphics, bird);

            // put everything on the screen at one time
            g.drawImage(buffimg,0,0,null);
        }else{
            String over = "Game Over!!";
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",1,60));
            g.drawString(over,100, 250);
            String reset = "Space Restart Game!!";
            g.setFont(new Font("Arial",1,30));
            g.drawString(reset, 125,350);
        }

    }

    // press key
    public void add(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if(bird.life == false){
                    restart();
                }
                break;
        }
    }

    // release key
    public void minu(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(5);
                break;
        }
    }

    public void restart(){
        gameBarrierLayer.restant();
        bird.restartDraw();
    }

}
