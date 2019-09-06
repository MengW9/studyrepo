package com.reptile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

public class yixue {
    static int number = 6331;

    public static int getYixue() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://ir.nsfc.gov.cn/fieldPaper/H");
        Thread.sleep(1000);
        driver.get("http://ir.nsfc.gov.cn/fieldPaper/H");
        Thread.sleep(1000);
        //再次重新爬取
        List<WebElement> elements = driver.findElement(By.id("pageNoUl")).findElements(By.tagName("li"));
        WebElement e = elements.get(7);
        e.findElement(By.tagName("input")).sendKeys(String.valueOf(number));
        e.findElement(By.tagName("button")).click();
        getUrl(driver);
        return number;
    }

    public static int getUrl(WebDriver driver) throws InterruptedException {
        try {
            List<WebElement> elements = driver.findElement(By.id("listDiv")).findElements(By.className("row"));


            for (int i = 0; i < elements.size(); i++) {
                WebElement el = elements.get(i);
                //获取标题
                String text = el.getText();
                //判断标题是否有英文  过滤
                if (isENChar(text)) {
                    Thread.sleep(100);
                    WebElement w = el.findElement(By.className("col-md-12")).findElement(By.tagName("a"));
                    //获取页面url
                    String url = w.getAttribute("href");
                    //存数据库
                    insertYixueUrl(url);
                }
                i += 2;
            }
            if (number <= 8428) {
                getfanye(driver);
                Thread.sleep(800);
            } else {
                return number;
            }
            getUrl(driver);
        } catch (Exception e) {
            getUrl(driver);
        }


        return number;
    }

    public static void getfanye(WebDriver driver) {
        List<WebElement> elements = null;
        elements = driver.findElement(By.id("pageNoUl")).findElements(By.tagName("li"));

        WebElement ele = elements.get(elements.size() - 2).findElement(By.tagName("a"));

        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", ele);
        number++;
    }


    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        String urls = "jdbc:mysql://localhost:3306/legal?serverTimezone=UTC";
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(urls, "root", "root");
        return conn;
    }

    public static void insertYixueUrl(String url) throws ClassNotFoundException, SQLException {
        Connection conn = getConnect();
        String sql = "insert into yixue_url (url,pageNum) values (?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, url);
        ps.setInt(2, number);
        ps.executeUpdate();
        System.out.println("总共有8428页，当前第"+number+"页，链接为："+url);
        conn.close();
    }

    public static void main(String[] args) throws InterruptedException {
//        int a = getYixue();
//        System.out.println(a);
        try {
            getElement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void getElement() throws SQLException, ClassNotFoundException {
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

    public static List<String> select() throws SQLException, ClassNotFoundException {
        Connection conn = getConnect();
        List<String> urls = new ArrayList();
        String sql = "select url from yixue_url";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            urls.add(rs.getString("url"));
        }
        conn.close();
        return urls;
    }

    public static void insertUrl(String url) throws ClassNotFoundException, SQLException {
        Connection conn = getConnect();
        String sql = "insert into newyixue_url (url) values (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, url);
        ps.executeUpdate();
        System.out.println(url);
        conn.close();
    }


    /**
     * 判断字符串中含有英文的个数是否过半，包含返回true
     * @param string
     * @return
     */
    public static boolean isENChar(String string) {
        boolean flag;
        int i = 0;
        char[] chars = string.toCharArray();
        for (char b : chars) {
            Pattern p = Pattern.compile("[a-zA-z]");
            if (p.matcher(String.valueOf(b)).find()) {
                i++;
            }
        }
        int a = chars.length / 2;
        if (i > a) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }
}
