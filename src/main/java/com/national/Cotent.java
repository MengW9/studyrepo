package com.national;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: mengw9
 * @create: 2019-09-06 23:10
 **/
public class Cotent {

    static String savePath;
    private static HttpURLConnection conn = null;
    //断开重新下载标识
    static int i = 2290;

    Depatment depart = new Depatment();

    public void getElement(WebDriver driver) throws SQLException, ClassNotFoundException, InterruptedException {
        List<String> urls = depart.select();
        for (String url : urls) {
            driver.get(url);
            getContent(driver);
            Thread.sleep(1500);
        }
    }

    public void getElementXinxi(WebDriver driver) throws SQLException, ClassNotFoundException, InterruptedException {
        List<String> urls = depart.selectXinxi();
        for (String url : urls) {
            driver.get(url);
            getContent(driver);
            Thread.sleep(1500);
        }
    }

    public void getElementDili(WebDriver driver) throws SQLException, ClassNotFoundException, InterruptedException {
        i = 0;
        List<String> urls = depart.selectDili();
        for (String url : urls) {
            driver.get(url);
            getContent(driver);
            Thread.sleep(1500);
        }
    }

    public void getElementyixue(WebDriver driver) throws SQLException, ClassNotFoundException, InterruptedException {
        i = 0;
        List<String> urls = depart.selectYixue();
        for (String url : urls) {
            driver.get(url);
            getContent(driver);
            Thread.sleep(1500);
        }
    }

    public void getContent(WebDriver driver) {
        try {
            LifeSciencess lifeSciences = new LifeSciencess();
            WebElement element = driver.findElement(By.id("paperDetailDiv"));
            String title = element.findElement(By.tagName("h2")).getText();
            lifeSciences.setTitle(title);
            Thread.sleep(500);
            List<WebElement> elements = element.findElements(By.className("row"));
            List<String> param = new ArrayList();
            param.add(elements.get(0).findElement(By.className("col-md-9")).getText());
            param.add(elements.get(1).findElement(By.className("col-md-9")).getText());
            param.add(elements.get(2).findElement(By.className("col-md-9")).getText());
            param.add(elements.get(6).findElement(By.className("col-md-9")).getText());
            param.add(elements.get(8).findElement(By.className("col-md-9")).getText());
            String author = param.get(0);
            String periodical = param.get(1);
            String data = param.get(2);
            String departments = param.get(3);
            String researchField = param.get(4);
            if (author == null || periodical == null || data == null || departments == null || researchField == null) {
                getContent(driver);
            } else {
                lifeSciences.setAuthor(author);
                lifeSciences.setPeriodical(periodical);
                lifeSciences.setData(data);
                lifeSciences.setDepartment(departments);
                lifeSciences.setResearchField(researchField);
                element = elements.get(7).findElement(By.className("col-md-9")).findElement(By.tagName("a"));
                Thread.sleep(500);
                String pdf = element.getAttribute("href");
                String fileName = pdf.substring(36);
                LifeSciencess life = downLoadByUrl(pdf, fileName, savePath);
                if (life != null) {
                    lifeSciences.setName(life.getName());
                    lifeSciences.setPath(life.getPath());
                }
                i++;
                System.out.println(i);
            }
            depart.insertContent(lifeSciences);
        } catch (Exception e) {
            getContent(driver);
        }
    }

    //取全文文本
    public String getTextFromPDF(InputStream inputStream) {
        String result = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(inputStream);
            PDFTextStripper pts = new PDFTextStripper();
            result = pts.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @Description： 取关键字
     * @Param： [text]
     * @return： java.lang.String
     * @Author： mengw9
     * @Date： 2019-09-05
     * @Time： 10:01
     */
    public String getKeyword(String text) {
        String keyword = "";
        String pattern = "关键词[:|：](.*?)\\n";
        text = text.replaceAll(" ", "");
        Pattern compile = Pattern.compile(pattern, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = compile.matcher(text);
        while (matcher.find()) {
            keyword = matcher.group();
            System.out.println(matcher.start() + "matcher" + matcher.end());
            System.out.println("matcher.groupCount()" + matcher.groupCount());
        }
        return keyword;
    }

    /**
     * @param urlStr   下载路径
     * @param fileName 文件名
     * @param savePath 存放路径
     * @throws IOException
     */
    public static LifeSciencess downLoadByUrl(String urlStr, String fileName, String savePath) throws IOException, InterruptedException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        try {
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            //文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            LifeSciencess lifeSciencess = new LifeSciencess();
            lifeSciencess.setPath(savePath + fileName);
            lifeSciencess.setName(fileName);
            return lifeSciencess;
        } catch (Exception e) {
            LifeSciencess lifeSciencess = new LifeSciencess();
            e.printStackTrace();
            Thread.sleep(200);
            return lifeSciencess;
        }


    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try {
            downLoadByUrl("http://dcs.yozosoft.com:8000/2018/10/09/MTTEx.pdf", "MTTEx.pdf", "E:/upload/protocol");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void guojia() throws SQLException, ClassNotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        savePath = "D:\\guojia\\life\\";
        getElement(driver);
        savePath = "D:\\guojia\\xinxi\\";
        getElementXinxi(driver);
        savePath = "D:\\guojia\\dili\\";
        getElementDili(driver);
        savePath = "D:\\guojia\\yixue\\";
        getElementyixue(driver);
        driver.quit();
    }

    //InputStream inputStream  = getFile(pdf);
    //            if(inputStream!=null) {
    //    String text = getTextFromPDF(inputStream);
    //    String keyword = getKeyword(text);
    //    lifeSciences.setKeyword(keyword);
    //    StorePath path = iFastDFSClientService.uploadFile(inputStream, "pdf");
    //    //获取组名字
    //    lifeSciences.setFileGroupName(path.getGroup());
    //    //获取路径
    //    lifeSciences.setFilePath(path.getPath());
    //    //获取全路径
    //    lifeSciences.setFileFullPath(path.getFullPath());
    //    inputStream.close();
//}

}
