package com.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @program: yofc-ipe-root
 * @description: 汉语拼音工具类
 * @author: mengw9
 * @create: 2019-09-18 16:04
 **/
public class HanyuPinyinHelp {

    /**
     * 将文字转为汉语拼音
     * @param chineseLanguage 要转成拼音的中文
     */
    public static String toHanyuPinyin(String chineseLanguage,HanyuPinyinCaseType type) {
        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(type);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                // 如果字符是中文,则将中文转为汉语拼音
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {
                    // 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * @Description： 获取文本拼音  全部小写
     * @Param： [chineseLanguage]
     * @return： java.lang.String
     * @Author： mengw9
     * @Date： 2019-09-18
     * @Time： 16:20
     */
    public static String toXiaoxiePinyin(String chineseLanguage){
        return toHanyuPinyin(chineseLanguage,HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * @Description： 获取文本拼音  全部大写
     * @Param： [chineseLanguage]
     * @return： java.lang.String
     * @Author： mengw9
     * @Date： 2019-09-18
     * @Time： 16:21
     */
    public static String toDaxiePinyin(String chineseLanguage){
        return toHanyuPinyin(chineseLanguage,HanyuPinyinCaseType.UPPERCASE);
    }

}
