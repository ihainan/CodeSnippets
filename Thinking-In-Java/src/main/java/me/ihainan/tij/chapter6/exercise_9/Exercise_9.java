package me.ihainan.tij.chapter6.exercise_9;

import me.ihainan.tij.chapter6.exercise_9.connection.Connection;
import me.ihainan.tij.chapter6.exercise_9.connection.ConnectionManager;

/**
 * 习题 8
 */
public class Exercise_9 {
    public static void main(String[] args){
        // Connection connection = new Connection(); // 错误，无法实例化
        // Connection connection = Connection.getConnection(); // 错误，不在一个包内
        Connection connection = ConnectionManager.getConnection();
        connection = ConnectionManager.getConnection();
        connection = ConnectionManager.getConnection();
        connection = ConnectionManager.getConnection();
    }
}
