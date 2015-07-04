package me.ihainan.tij.chapter5;

/**
 * 枚举的一段无聊代码
 *
 * */
public class Program5_9 {
    public enum Color{
        YELLOW, BLUE, GREEN
    }

    public static void main(String[] args){
        for(Color c: Color.values()) {
            System.out.println(c + ", ordinal " + c.ordinal());
        }
    }
}
