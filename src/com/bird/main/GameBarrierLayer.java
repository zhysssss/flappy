package com.bird.main;

import com.bird.util.Constant;

import javax.swing.text.ParagraphView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {
    private List<Barrier> barriers;

    private GameTime gameTime;

    private int txt;

    private Random random = new Random();

    public GameBarrierLayer() {
        this.barriers = new ArrayList<>();
        gameTime = new GameTime();
    }

    public void draw(Graphics g, Bird bird) {
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if(barrier.isVisible()){
                barrier.draw(g);
            }else{
                Barrier remove = barriers.remove(i);
                Barrierpool.setPool(remove);
                i--;
            }

        }
        collideBird(bird);
        logic(g);
    }

    public void logic(Graphics g) {
        if (barriers.size() == 0) {
            gameTime.begin();
            ran();
            // use object pool to save memory
//            Barrier top = new Barrier(600, 0, numberTop, 0);
//            barriers.add(top);
//            Barrier down = new Barrier(600, Constant.FRAM_HEIGHT - numberDown, numberDown, 2);
//            barriers.add(down);
            insert(600, 0, numberTop, 0);
            insert(600, Constant.FRAM_HEIGHT - numberDown, numberDown, 2);

        } else {
            long differ = gameTime.differ();
            g.setFont(new Font("Arial", 1, 20));
            g.drawString("You flied for: "+ differ + " seconds", 30, 50);
            try {
                txt = getTxt();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(differ <= txt){
                g.drawString("Best record:"+txt,300,50);
            }else{
                setTxt(String.valueOf(differ));
                g.drawString("Best record:"+differ,300,50);
            }
            // check the final one entering the screen
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                ran();
//                Barrier top = new Barrier(600, 0, numberTop, 0);
//                barriers.add(top);
//                Barrier down = new Barrier(600, Constant.FRAM_HEIGHT - numberDown, numberDown, 2);
//                barriers.add(down);
                if(number < 50){
                    // hovered barriers
                    insert(600,142,240,4);
                }else if(number > 450){
                    insert(600,125,200,6);
                } else{
                    insert(600, 0, numberTop, 0);
                    insert(600, Constant.FRAM_HEIGHT - numberDown, numberDown, 2);
                }

            }

        }
    }

    File file = new File("D:\\graduate\\flappy_bird\\Flappy_Bird\\record.txt");

    public int getTxt() throws FileNotFoundException {
        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader(file));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int record = 0;
        try {
            record = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    public void setTxt(String str){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get an object from pool, store in barriers array
    public void insert(int x, int y, int num, int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }

    //top barrier height
    private int numberTop;
    // down barrier height
    private int numberDown;
    private int number;

    // randomly generate 2 heights in [100, 500]
    public void ran() {
        numberTop = random.nextInt(400) + 100;
        numberDown = random.nextInt(400) + 100;
        number = random.nextInt(500);
        // if conflict
        if (numberTop + numberDown > 400) {
            ran();
        }
    }

    /**
     * check is barrier and bird are conflicted
     */
    public boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if(barrier.getRect().intersects(bird.getRect())){
                System.out.println("Bird collides with barriers!!");
                bird.life = false;
                return true;
            }
        }
        return false;
    }


    // clear barrier pool
    public void restant() {
        barriers.clear();
    }


}
