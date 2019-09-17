package com.pmcity;

import org.apache.commons.collections4.map.LinkedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: WangMengWei
 * @create: 2019-09-09 13:43
 **/
public class WuHan {


    /**
     * 当前年
     */
    private static int year = Calendar.getInstance().get(Calendar.YEAR);
    /**
     * 当前月
     */
    private static int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    /**
     * 上月
     */
    private static int oldMonth = Calendar.getInstance().get(Calendar.MONTH);

    public static WebDriver getWebDriver() throws Exception {
        //配置ChromeDiver
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
        //开启新webDriver进程
        WebDriver webDriver = new ChromeDriver();

        return webDriver;
    }

    /**
     * 获取区域链接
     *
     * @param webDriver
     * @return
     */
    public static Map<String, String> getArea(WebDriver webDriver) {
        HashMap<String, String> map = new HashMap<>();
        List<WebElement> elements = webDriver.findElement(By.className("bddcl_content_active")).findElements(By.tagName("li"));
        for (WebElement element : elements) {
            WebElement we = element.findElement(By.tagName("a"));
            String text = we.getAttribute("innerHTML").replace("<i>•</i>", "");
            String href = we.getAttribute("href");
            map.put("区域", text);
            map.put("链接", href);
            System.out.println(text + "=====" + href);
        }
        return map;
    }

    /**
     * 获取Aqi
     */
    public static Map getAqi(WebDriver webDriver, String city, String area) throws Exception {
        //拼接地址
        String url = "http://www.pm25.com/city/mon/aqi/" + city + "/" + area + ".html";
        webDriver.get(url);
        Thread.sleep(1500);
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String pageSource = webDriver.getPageSource();
        //正则处理
        String reg = "varoption_d30.*?category.*?data:(.*?)axisLine.*?中国标准.*?data:(.*?)}";
        return getContent(pageSource, reg);
    }

    //获取PM2.5
    public static Map getPM25(WebDriver webDriver, String city, String area) throws Exception {
        String url = "http://www.pm25.com/city/mon/pm2_5/" + city + "/" + area + ".html";
        webDriver.get(url);
        String pageSource = webDriver.getPageSource();
        //正则处理
        String reg = "option_d30.*?category.*?data:(.*?)axisLine.*?浓度值.*?data:(.*?)}";
        return getContent(pageSource, reg);
    }

    //获取PM10
    public static Map getPM10(WebDriver webDriver, String city, String area) throws Exception {
        String url = "http://www.pm25.com/city/mon/pm10/" + city + "/" + area + ".html";
        webDriver.get(url);
        String pageSource = webDriver.getPageSource();
        //正则处理
        String reg = "option_d30.*?category.*?data:(.*?)axisLine.*?浓度值.*?data:(.*?)}";
        return getContent(pageSource, reg);
    }


    public static Map getContent(String pageSource, String reg) {
        Map resultmap = new LinkedMap();
        List<String> dataList = new LinkedList<>();
        List<String> sourceList = new LinkedList<>();
        Pattern compile = Pattern.compile(reg, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = compile.matcher(pageSource.replaceAll(" ", ""));
        while (matcher.find()) {
            //日期数组
            String dategroup = matcher.group(1);
            //aqi数组
            String aqigroup = matcher.group(2);
            //处理日期数据
            String all = dategroup.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("]", "").replaceAll("\n", "");
            String[] split = all.split(",");
            //处理数组
            String s = aqigroup.replaceAll("\\[", "").replaceAll("]", "");
            String[] splitAqi = s.split(",");
            for (int i = 0; i < split.length; i++) {
                String[] strings = split[i].split("\\\\u");
//                String str = strings[0] + (char) Integer.valueOf(strings[1], 16).intValue();
                String str = strings[0];
                if (!dataList.contains(str)) {
                    dataList.add(str);
                    sourceList.add(splitAqi[i]);
                }
            }
            //判断日期是否跨月
            if (isMonth(dataList)) {
                //跨月添加年月
                addYearMonth(dataList);
            } else {
                //不跨添加年月
                addMonth(dataList);
            }

            System.out.println(dataList.toString());
            System.out.println(sourceList.toString());
        }

        if (!dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                resultmap.put(dataList.get(i), sourceList.get(i));
            }
        }
        return resultmap;
    }

    /**
     * 不跨月添加
     *
     * @param dataList
     * @return
     */
    private static List<String> addMonth(List<String> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.set(i, year + "-" + month + "-" + dataList.get(i));
        }
        return dataList;
    }

    /**
     * 跨月添加
     *
     * @param dataList
     * @return
     */
    private static List<String> addYearMonth(List<String> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).equals("01")) {
                //拼接当月的日期
                for (int j = i; j < dataList.size(); j++) {
                    dataList.set(j, year + "-" + month + "-" + dataList.get(j));
                }
                //拼接上月的日期
                for (int j = 0; j < i; j++) {
                    dataList.set(j, year + "-" + oldMonth + "-" + dataList.get(j));
                }
                break;
            }
        }
        return dataList;
    }

    /**
     * 判断日期是否跨月
     *
     * @param list
     * @return
     */
    private static boolean isMonth(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (Integer.parseInt(list.get(i).toString()) > Integer.parseInt(list.get(i + 1).toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较日期（年月日）
     */
    public static int dateCompare(Date date1, Date date2) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFirst = null;
        try {
            dateFirst = dateFormat.parse(dateFormat.format(date1));
            Date dateLast = dateFormat.parse(dateFormat.format(date2));

            if (dateFirst.after(dateLast)) {
                return 1;
            } else if (dateFirst.before(dateLast)) {
                return -1;
            }
            return 0;
        } catch (ParseException e) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
//        WebDriver webDriver = getWebDriver("http://www.pm25.com/city/mon/aqi/%E6%AD%A6%E6%B1%89/%E4%B8%9C%E6%B9%96%E6%A2%A8%E5%9B%AD.html");
//        WebDriver webDriveraqi = getWebDriver("http://www.pm25.com/city/mon/aqi/武汉/东湖梨园.html");
//        Map<String, String> area = getArea(webDriver);

        /*String url ="http://www.pm25.com/city/mon/aqi/武汉/东湖梨园.html";
        WebDriver webDriverpm25 = getWebDriver(url);
        String pageSource = webDriverpm25.getPageSource();*/
        WebDriver webDriver = getWebDriver();
        String city = "武汉";
        String area = "沌口新区";
        Map aqi = getAqi(webDriver, city, area);
        System.out.println("正在爬取" + city + area + "的aqi数据");
        Map pm25 = getPM25(webDriver, city, area);
        System.out.println("正在爬取" + city + area + "的pm25数据");
        Map pm10 = getPM10(webDriver, city, area);
        System.out.println("正在爬取" + city + area + "的pm10数据");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //
        Department department = new Department();

        aqi.forEach((key, value) -> {
            aqiHistoricalPO aqiHistoricalPO = new aqiHistoricalPO();
            //数据源id
            aqiHistoricalPO.setSourceId(2);
            //城市id
            aqiHistoricalPO.setCityId(1);
            // 站点  id
            aqiHistoricalPO.setInspectionSiteId(3);
            aqiHistoricalPO.setAOI(Integer.parseInt(value.toString()));
            aqiHistoricalPO.setPM2_5(Integer.parseInt(pm25.get(key.toString()).toString()));
            aqiHistoricalPO.setPM10(Integer.parseInt(pm10.get(key.toString()).toString()));
            try {
                aqiHistoricalPO.setCheckDate(new java.sql.Date(dateFormat.parse(key.toString()).getTime()));
                department.insertUrl(aqiHistoricalPO);
            } catch (Exception e) {
                e.printStackTrace();
            }


        });


        webDriver.quit();

        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parse = simpleDateFormat.parse("2019-9-10");
        Date parse1 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));

        if (parse.equals(parse1)) {
            System.out.println("20190914想等");
        }
        int i1 = Calendar.getInstance().get(Calendar.MONTH) + 1;
        System.out.println(i1);*/


//        System.out.println(pageSource);
//        System.out.println(aqi);
    }

}
