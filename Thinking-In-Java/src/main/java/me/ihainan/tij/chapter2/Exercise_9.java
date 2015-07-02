package me.ihainan.tij.chapter2;

/**
 * 验证 Java 的自动包装功能
 */
public class Exercise_9 {
    public static void main(String agrs[]){
        int i = 2;
        long l = 2l;
        char c = 'c';

        Integer i2 = i;
        System.out.println("Integer i2 = " + i2);
        System.out.println("int i = " + i);
        System.out.println();

        Long l2 = l;
        System.out.println("Long l2 = " + i);
        System.out.println("long l = " + l2);
        System.out.println();

        Character c2 = c;
        System.out.println("Character c2 = " + c2);
        System.out.println("char c = " + c);
        System.out.println();
    }
}
