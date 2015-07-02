package me.ihainan.tij.chapter3;

/**
 * 验证 Java 的别名机制
 */
public class Exercise_2 {
    private static class People {
        float height;
    }

    private static void f(People p) {
        p.height = 300;
    }

    public static void main(String[] args) {
        People p1 = new People();
        People p2 = new People();
        p1.height = 175;
        p2.height = 180;
        System.out.println("p1.height = " + p1.height + ", p2.height = " + p2.height);

        p1 = p2;
        p1.height = 150;
        System.out.println("p1.height = " + p1.height + ", p2.height = " + p2.height);

        f(p1);
        System.out.println("p1.height = " + p1.height + ", p2.height = " + p2.height);
    }
}
