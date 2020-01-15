package com.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author:mengw9
 * @date: 2019-07-04
 * @time: 10:12
 */
public class ReadDocx {
    public static void main(String[] args) throws Exception {

        Path path = Paths.get("D:\\Desktop\\test.docx");
        byte[] byteData = Files.readAllBytes(path);

        // read as XWPFDocument from byte[]
        XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(byteData));

        int numberToPrint = 0;

        // you can edit paragraphs
        for (XWPFParagraph para : doc.getParagraphs()) {
            List<XWPFRun> runs = para.getRuns();

//            System.out.println("para====" + para);

//            numberToPrint++;

            for (XWPFRun run : runs) {

                // read text
                String text = run.getText(0);

                System.out.println(text);

                // edit text and update it
//                run.setText(numberToPrint + " " + text, 0);
            }
        }

        // save it
//        FileOutputStream fos = new FileOutputStream(new File("D:\\Desktop\\test2.docx"));
//        doc.write(fos);
    }


}
