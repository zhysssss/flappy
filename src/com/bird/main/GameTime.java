package com.bird.main;

import com.bird.util.GameUtil;

public class GameTime {

    // start
    private long beginTime;
    // end
    private long endTime;
    // difference
    private long differ;

    public GameTime(){}

    public  void begin(){
        beginTime = System.currentTimeMillis();
    }
    public long differ(){
        endTime = System.currentTimeMillis();
        differ = (endTime - beginTime) / 1000;
        return differ;
    }

}
