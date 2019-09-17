package com.national;

import java.sql.*;
import java.util.*;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: WangMengWei
 * @create: 2019-09-06 23:06
 **/
public class Depatment {

    public Connection getConnect() throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        String urls = "jdbc:mysql://192.168.8.136:3306/legal?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(urls, "root", "root");
        return conn;
    }


    //生命
    public List<String> select() throws SQLException, ClassNotFoundException {
        Connection conn = getConnect();
        List<String> urls = new ArrayList();
        String sql = "select url from newlife_url where id > 2290";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            urls.add(rs.getString("url"));
        }
        conn.close();
        return urls;
    }
    //信息
    public  List<String> selectXinxi() throws SQLException, ClassNotFoundException {
        Connection conn = getConnect();
        List<String> urls = new ArrayList();
        String sql = "select url from newxinxi_url";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            urls.add(rs.getString("url"));
        }
        conn.close();
        return urls;
    }
    //地理
    public  List<String> selectDili() throws SQLException, ClassNotFoundException {
        Connection conn = getConnect();
        List<String> urls = new ArrayList();
        String sql = "select url from newdili_url";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            urls.add(rs.getString("url"));
        }
        conn.close();
        return urls;
    }

    //TODO:医学
    public  List<String> selectYixue() throws SQLException, ClassNotFoundException {
        Connection conn = getConnect();
        List<String> urls = new ArrayList();
        String sql = "select url from newyixue_url";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            urls.add(rs.getString("url"));
        }
        conn.close();
        return urls;
    }

    public  void insertUrl(String url) throws ClassNotFoundException, SQLException {
        Connection conn = getConnect();
        String sql = "insert into newdili_url (url) values (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, url);
        ps.executeUpdate();
        System.out.println(url);
        conn.close();
    }

    public  void insertYixueUrl(String url) throws ClassNotFoundException, SQLException {
        Connection conn = getConnect();
        String sql = "insert into yixue_url (url) values (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, url);
        ps.executeUpdate();
        System.out.println(url);
        conn.close();
    }

    public  void getElement() throws SQLException, ClassNotFoundException {
        List<String> urls = select();
        Set<String> urlSet = new HashSet<String>();
        urlSet.addAll(urls);
        List<String> newUrls = new ArrayList<String>();
        newUrls.addAll(urlSet);
        Iterator<String> iter = newUrls.iterator();
        while (iter.hasNext()) {
            String url = iter.next();
            insertUrl(url);
        }


    }

    public  void insertContent(LifeSciencess lifeSciences) throws ClassNotFoundException, SQLException {
        Connection conn = getConnect();
        String sql = "insert into life_sciences (title,data,department,research_field,periodical,author,name,path) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, lifeSciences.getTitle());
        ps.setString(2, lifeSciences.getData());
        ps.setString(3, lifeSciences.getDepartment());
        ps.setString(4, lifeSciences.getResearchField());
        ps.setString(5, lifeSciences.getPeriodical());
        ps.setString(6, lifeSciences.getAuthor());
        ps.setString(7, lifeSciences.getPath());
        ps.setString(8, lifeSciences.getName());
        ps.executeUpdate();
        conn.close();
    }

    public  void main(String[] args) throws SQLException, ClassNotFoundException {
        getElement();
    }
}
