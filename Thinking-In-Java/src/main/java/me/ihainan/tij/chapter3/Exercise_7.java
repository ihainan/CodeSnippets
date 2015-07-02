package me.ihainan.tij.chapter3;

import java.util.Random;

/**
 * 硬币程序
 */
public class Exercise_7 {
    public static void main(String[] args){
        Random random = new Random();
        int value = random.nextInt(2);
        System.out.println("value = " + value);
        if(value == 0){
            System.out.println("正面");
        }
        else{
            System.out.println("反面");
        }
    }
}
