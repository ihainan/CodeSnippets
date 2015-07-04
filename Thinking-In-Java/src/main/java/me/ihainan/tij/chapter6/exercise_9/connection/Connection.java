package me.ihainan.tij.chapter6.exercise_9.connection;

/**
 * 连接类
 */
public class Connection {
    private Connection(){};
    static Connection getConnection(){return new Connection();}
}
