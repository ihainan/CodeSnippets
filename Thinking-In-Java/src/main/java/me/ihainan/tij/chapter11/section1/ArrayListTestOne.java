package me.ihainan.tij.chapter11.section1;

import java.util.ArrayList;

/**
 * ArrayList 容器测试
 */
public class ArrayListTestOne {
    static class Apple{
        int id = 1;
    }
    static class Orange{}

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Apple apple = new Apple();
        Orange orange = new Orange();
        arrayList.add(apple);
        arrayList.add(orange);
        System.out.println(((Apple)arrayList.get(1)).id);
    }
}
