package com.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: algorithm_demo
 * @description: 读pdf内容
 * @author: WangMengWei
 * @create: 2019-09-03 15:16
 **/
public class SimplePDFReader {

    /**
     * @Description： pdfbox 双层文本才能取出
     * @Param： [pdfFilePath]
     * @return： java.lang.String
     * @Author： WangMengWei
     * @Date： 2019-09-03
     * @Time： 16:17
     */
    public static String getTextFromPDF(File file) {
        String result = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
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
     * @Author： WangMengWei
     * @Date： 2019-09-05
     * @Time： 10:01
     */
    public static String getKeyword(String text) {
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

    public static String get() {
        String group = "";
        String reg = "tt(.*?)tt";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher("textexxt\n\texttexttexttext");
        while (matcher.find()) {
            group = matcher.group();
        }
        return group;
    }

    public static void main(String[] args) {
//        String textFromPDF = getTextFromPDF("D:\\Desktop\\Java开发手册(华山版).pdf");
//        String textFromPDF = getTextFromPDF("D:\\Desktop\\1000000962393.pdf");
//        String textFromPDF = getTextFromPDF("D:\\Desktop\\1000009643976.pdf");
//        String keyword = getKeyword(textFromPDF);
//        System.out.println(keyword);

        String s = get();
        System.out.println(s);
    }

}
