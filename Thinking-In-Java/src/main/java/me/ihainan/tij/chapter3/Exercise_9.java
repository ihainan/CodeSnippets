package me.ihainan.tij.chapter3;

/**
 * 单精和双精能表示的最大、最小数
 */
public class Exercise_9 {
    public static void main(String[] args){
        float fMax = Float.MAX_VALUE;
        float fMin = Float.MIN_VALUE;
        double dMax = Double.MAX_VALUE;
        double dMin = Double.MIN_VALUE;

        System.out.println("Max float value : " + fMax);
        System.out.println("Min float value : " + fMin);
        System.out.println("Max double value : " + dMax);
        System.out.println("Min double value : " + dMin);
    }
}
