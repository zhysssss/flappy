package com.bird.main;

import java.util.ArrayList;
import java.util.List;

public class Barrierpool {

    // container that manage all objects
    private static List<Barrier> pool = new ArrayList<>();

    public static final int initCount = 16;

    public static final int maxCount = 20;

    static {
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }

    // get an object from pool
    public static Barrier getPool() {
        int size = pool.size();
        if (size > 0) {
            System.out.println("Take one barrier from pool");
            return pool.remove(size - 1);
        }else{
            System.out.println("new barrier object");
            return new Barrier();
        }
    }

    // return an object to pool
    public static void setPool(Barrier barrier){
        if(pool.size() < maxCount){
            pool.add(barrier);
            System.out.println("return an barrier object");
        }
    }
}
