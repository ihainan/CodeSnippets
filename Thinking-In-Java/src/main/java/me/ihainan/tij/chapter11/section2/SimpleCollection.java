package me.ihainan.tij.chapter11.section2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Collection 接口
 */
public class SimpleCollection {
    public static void main(String[] args){
        Collection<Integer> collection = new ArrayList<Integer>();
        for(int i = 0; i < 10; ++i){
            collection.add(i);
        }

        for(Integer c: collection){
            System.out.println(c);
        }
    }
}
