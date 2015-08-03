package me.ihainan.tij.chapter11.section3;

import java.util.*;

/**
 * 添加一组元素测试
 */
public class AddElements {
    public static void printCollection(Collection<Integer> collection) {
        for (Integer i : collection) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}));
        printCollection(collection);
        collection.addAll(Arrays.asList(7, 8));
        printCollection(collection);
        Collections.addAll(collection, 9, 10, 11);
        List list = Arrays.asList(1, 2, 3);
        list.add(1);
    }

    /* Ouput:
    1
    2
    3
    4
    5
    6
    1
    2
    3
    4
    5
    6
    7
    8
    Exception in thread "main" java.lang.UnsupportedOperationException
        at java.util.AbstractList.add(AbstractList.java:131)
        at java.util.AbstractList.add(AbstractList.java:91)
     */
}
