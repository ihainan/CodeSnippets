package me.ihainan.tij.chapter11.section5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List 类测试
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<String>();
        Collections.addAll(myList, "1", "2", "3", "4", "2");
        myList.set(0, "-1");
        List<String> subLst = myList.subList(0, 2);
        System.out.println("1: " + myList.subList(0, 1));
        Collections.shuffle(subLst);
        System.out.println(subLst);
        System.out.println(myList.containsAll(subLst));
        // myList.retainAll(subLst);   // Exception
        System.out.println(myList + "  " + subLst);
        // myList.removeAll(subLst);
        System.out.println(myList + "  " + subLst);
        // System.out.println(subLst.retainAll(myList));

    }
}
