package com.pugwoo.random;

public class Random {
	
    private int num = (int)(100*Math.random());
    
    public void printRandom(){
        System.out.println("随机数是"+num);
    }
}
