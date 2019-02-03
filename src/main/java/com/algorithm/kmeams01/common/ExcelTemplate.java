package com.algorithm.kmeams01.common;

import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelTemplate {


    /**
     * 获取CSV文件内容，加入编号
     *
     * @param sourceFile
     * @return
     */
    public static List<List<String>> getCsvDataWithCode(String sourceFile) {
        return getCsvDataUtil(sourceFile, true);
    }


    /**
     * 读取CSV 文件
     *
     * @param sourceFile
     */
    public static List<List<String>> getCsvData(String sourceFile) {

        return getCsvDataUtil(sourceFile, false);


    }


    /**
     * 获取文件内容
     *
     * @param sourceFile
     * @param isCode
     * @return
     */
    private static List<List<String>> getCsvDataUtil(String sourceFile, boolean isCode) {
        List<List<String>> result = new ArrayList<>();

        File csv = new File(sourceFile);
        BufferedReader br;
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(csv), "GBK");
            br = new BufferedReader(inputStreamReader);
            String line;
            int num = 0;
            while ((line = br.readLine()) != null) {
                List<String> innerList = new ArrayList<>();
                if (isCode) {
                    innerList.add(num++ + "");
                }
                String[] innerString = line.split(",");
                for (String inner : innerString) {
                    innerList.add(inner);
                }
                result.add(innerList);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

}
