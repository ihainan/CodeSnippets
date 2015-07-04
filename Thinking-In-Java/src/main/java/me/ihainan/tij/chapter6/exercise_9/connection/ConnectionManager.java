package me.ihainan.tij.chapter6.exercise_9.connection;

/**
 * 连接管理器
 */
public class ConnectionManager {
    private static Connection[] connections;
    public static int currentCursor = 0;
    static{
        connections = new Connection[]{
                Connection.getConnection(),
                Connection.getConnection(),
                Connection.getConnection()
        };
    }

    public static Connection getConnection(){
        if(currentCursor == connections.length){
            System.out.println("发完啦！");
            return null;
        }
        System.out.println("返回第 " + currentCursor + " 个连接");
        return connections[currentCursor++];
    }
}
