package com.pmcity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: algorithm_demo
 * @description: 操作数据库
 * @author: WangMengWei
 * @create: 2019-09-10 11:09
 **/
public class Department {

    public Connection getConnect() throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        String urls = "jdbc:mysql://192.168.8.209:3306/ipe?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(urls, "ipe", "ipe");
        return conn;
    }

    public void insertUrl(AqiHistoricalPO aqiHistoricalPO) throws Exception {
        Connection conn = getConnect();
        String sql = "insert into aqi_historicalData (sourceId,cityId,InspectionSiteId,AOI,PM2_5,PM10,checkDate) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, aqiHistoricalPO.getSourceId());
        ps.setInt(2, aqiHistoricalPO.getCityId());
        ps.setInt(3, aqiHistoricalPO.getInspectionSiteId());
        ps.setInt(4, aqiHistoricalPO.getAOI());
        ps.setInt(5, aqiHistoricalPO.getPM2_5());
        ps.setInt(6, aqiHistoricalPO.getPM10());
        ps.setDate(7, aqiHistoricalPO.getCheckDate());
        int i = ps.executeUpdate();
        System.out.println("保存成功>" + i);
        conn.close();
    }


}
