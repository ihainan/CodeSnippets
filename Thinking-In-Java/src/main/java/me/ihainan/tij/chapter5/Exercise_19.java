package me.ihainan.tij.chapter5;

import java.util.Arrays;

/**
 * 验证可变参数列表
 */
public class Exercise_19 {
    public static void test(Object... objects){
        System.out.println(objects.getClass());
        System.out.println(Arrays.toString(objects));
    }

    public static void main(String[] args){
        test("Hello", 1, 2f);
    }
}
