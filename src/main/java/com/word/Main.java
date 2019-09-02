package com.word;

import com.word.ReadWord;

/**
 * @author:Meng
 * @date: 2019-07-03
 * @time: 22:09
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String path = "D:\\Desktop\\test.docx";
        com.word.ReadWord readWord = new ReadWord();
        String readDoc = readWord.ReadDoc(path);
        System.out.println(readDoc);
    }
}
