package me.ihainan.tij.chapter4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 无聊的二进制输出程序
 */
public class Exercise_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int number = scanner.nextInt();
            if (number >= 0) {
                do {
                    int remainder = number % 2;
                    number = number / 2;
                    result.add(remainder);
                } while (number != 0);
            }

            for (int i = result.size() - 1; i >= 0; --i) {
                System.out.print(result.get(i));
            }
            System.out.println();
        }
    }
}
