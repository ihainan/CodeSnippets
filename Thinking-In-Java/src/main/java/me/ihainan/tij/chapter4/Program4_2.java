package me.ihainan.tij.chapter4;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * 无聊的猜数字游戏
 */
public class Program4_2 {
    public static void main(String[] args) throws IOException {
        int number = new Random().nextInt(100);
        System.out.println(number);
        int min = 0, max = 99;
        while (true) {
            System.out.println("数值在 [" + min + ", " + max + "] 之间");
            int userInput;
            while (true) {
                // userInput = System.in.read();    // 傻逼
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                System.out.println("您输入了 " + userInput);
                if (userInput < min || userInput > max) {
                    System.out.println("请输入正确的数值，数值在 [" + min + ", " + max + "] 之间");
                } else {
                    break;
                }
            }
            if (userInput == number) {
                System.out.println("猜对了！");
                break;
            } else if (userInput < number) {
                min = userInput + 1;
            } else {
                max = userInput - 1;
            }
        }
    }
}
