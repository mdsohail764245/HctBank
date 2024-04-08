package com.example.controller;

import java.util.Random;

public class GenerateRandom {


    public long getRandom(){
        int minId = 1000000;
        int maxId = 9999999;
        Random random = new Random();
        return random.nextInt(maxId - minId + 1) + minId;
    }

}
