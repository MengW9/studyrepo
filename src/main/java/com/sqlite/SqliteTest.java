package com.sqlite;

import java.sql.*;

/**
 * @program: algorithm_demo
 * @description: 测试Sqlite连接
 * @author: mengw9
 * @create: 2019-11-27 15:02
 **/
public class SqliteTest {

    private static String Drivde = "org.sqlite.JDBC";
    private static String dbUrl = "D:\\sqlite\\self.db";

    public static void main(String[] args) throws Exception {
        SqliteTest sqliteTest = new SqliteTest();
        //打印表格
        sqliteTest.selectTable();
    }

    /**
     * @Description： 获取sqlite数据的连接
     * @Param： []
     * @return： java.sql.Connection
     * @Author： mengw9
     * @Date： 2019-11-27
     * @Time： 16:13
     */
    public Connection getConn()throws Exception{
        Class.forName(Drivde);
        return DriverManager.getConnection("jdbc:sqlite:"+dbUrl);
    }

    public void selectTable()throws Exception{
        String sql = "SELECT * FROM t_law;";
        Statement statement = getConn().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            System.out.println("记录是："+id+"=="+name+"=="+title);
        }

    }

    /**
     * @Description： 测试sqlite数据库连接
     * @Param： []
     * @return： void
     * @Author： mengw9
     * @Date： 2019-11-27
     * @Time： 15:47
     */
    public void testSqliteConn() throws Exception {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
            //创建连接对象，是Java的一个操作数据库的重要接口
            Statement statement = conn.createStatement();
            //判断是否有表tables的存在。有则删除
            statement.executeUpdate("drop table if exists tables");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            conn.close();
        }
        System.out.println("Opened database successfully");
    }

    /**
     * @Description： 创建表
     * @Param： []
     * @return： void
     * @Author： mengw9
     * @Date： 2019-11-27
     * @Time： 16:05
     */
    public void creteTable() {
        try {
            // 加载驱动,连接sqlite的jdbc
            Class.forName(Drivde);
            //连接数据库zhou.db,不存在则创建
            Connection connection = DriverManager.getConnection("jdbc:sqlite:db/test.db");
            //创建连接对象，是Java的一个操作数据库的重要接口
            Statement statement = connection.createStatement();
            String sql = "create table tables(name varchar(20),pwd varchar(20))";
            //判断是否有表tables的存在。有则删除
            statement.executeUpdate("drop table if exists tables");
            //创建数据库
            statement.executeUpdate(sql);
            //向数据库中插入数据
            statement.executeUpdate("insert into tables values('test','156546')");
            //搜索数据库，将搜索的放入数据集ResultSet中
            ResultSet rSet = statement.executeQuery("select * from tables");
            //遍历这个数据集
            while (rSet.next()) {
                //依次输出 也可以这样写 rSet.getString(“name”)
                System.out.println("姓名：" + rSet.getString(1));
                System.out.println("密码：" + rSet.getString("pwd"));
            }
            rSet.close();//关闭数据集
            connection.close();//关闭数据库连接
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
