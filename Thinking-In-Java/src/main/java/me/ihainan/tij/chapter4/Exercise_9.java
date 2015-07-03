package me.ihainan.tij.chapter4;

/**
 * 无聊的计算斐波那契数列程序
 */
public class Exercise_9 {
    private static void printFibonacci(int n1, int n2, int depth, int number) {
        if (depth == 1) {
            System.out.print("1 ");
            if (number > 1) {
                printFibonacci(1, 1, depth + 1, number);
            }
        } else if (depth == 2) {
            System.out.print("1 ");
            if (number > 2) {
                printFibonacci(1, 1, depth + 1, number);
            }
        } else {
            System.out.print(n1 + n2 + " ");
            if (number != depth) {
                printFibonacci(n2, n1 + n2, depth + 1, number);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise_9 Exercise_9 number");
        }

        int number = Integer.valueOf(args[0]);
        printFibonacci(1, 1, 1, number);
    }
}
