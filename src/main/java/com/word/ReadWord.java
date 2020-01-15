package com.word;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * 读取doc和docx文档的类
 *
 * @author:mengw9
 * @date: 2019-07-03
 * @time: 22:35
 */
public class ReadWord {

    /**
     * 读取doc格式
     *
     * @param path
     * @return String
     * @throws Exception
     */
    public String ReadDoc(String path) throws Exception {
        String wordText = "";
        //取小数点后缀
        String pathString = path.substring(path.indexOf(".") + 1);
        //转换为小写
        String s = pathString.toLowerCase();
        if (s.equals("doc")) {
            //读取流
            InputStream inputStream = new FileInputStream(path);
            //把inputStream流套入到WordExtractor
            WordExtractor word = new WordExtractor(inputStream);
            wordText = word.getText();
            //关闭流
            inputStream.close();
            return wordText;
        } else if (s.equals("docx")) {
            //可以读到每个字节  可以处理所有文件
            byte[] byteData = Files.readAllBytes(Paths.get(path));
            //把ByteArrayInputStream套到XWPFDocument流当中   XWPFDocument处理docx文档的流
            XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(byteData));
            //返回包含页眉或页脚文本的段落  进行遍历
            for (XWPFParagraph para : doc.getParagraphs()) {
                List<XWPFRun> runs = para.getRuns();
                for (XWPFRun run : runs) {
                    //获取所有内容
                    String text = run.getText(0);
                    //拼接
                    wordText = wordText + "\n" + text;
                }
            }
            return wordText;
        } else {
            System.out.println("这不是word文档");
        }
        return "error";
    }
}
