package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.module.pretreatment.service.Csv2ArffService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Csv2ArffServiceImpl implements Csv2ArffService {


    @Override
    public boolean GenerFile(String sourceFile, String targetFile) {

        List<List<String>> sourceData = getCsvData(sourceFile);

        if (sourceData.isEmpty() || sourceData.size() < 2) {
            return false;
        }

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(targetFile, false));

            out.write("@relation" + "  medical");
            out.newLine();
            List<String> attributeList = getColumeAttribute(sourceData);
            List<String> attribute = sourceData.get(0);
            int count = attribute.size();
            for (int i = 0; i < count; i++) {
                out.write("@attribute " + attribute.get(i) + " " + attributeList.get(i));
            }
            for (String innerAttribute : attribute) {

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in;

        try {

            String temp;
            out = new BufferedWriter(new FileWriter(sourceFile, false));
            //关系声明
            out.write("@relation" + "  medical");
            out.newLine();
            //属性声明
            int i1;
            int column = 5;
            for (i1 = 0; i1 < column; i1++) {
                out.write("@attribute attr" + i1 + " String");
                out.newLine();
            }
            //数据声明
            out.write("@data");
            out.newLine();
            //读CSV文件
            in = new BufferedReader(new FileReader(sourceFile));
            temp = in.readLine();
            while (temp != null) {
                out.write(temp);
                out.newLine();
                temp = in.readLine();
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取CSV 文件
     *
     * @param sourceFile
     */
    @Override
    public List<List<String>> getCsvData(String sourceFile) {

        List<List<String>> result = new ArrayList<>();

        File csv = new File(sourceFile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> innerList = new ArrayList<>();
                String[] innerString = line.split(",");
                for (String inner : innerString) {
                    innerList.add(inner);
                }
                result.add(innerList);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 生成属性值
     *
     * @param sourceData
     * @return
     */
    public List<String> getColumeAttribute(List<List<String>> sourceData) {

        List<String> result = new ArrayList<>();
        if (sourceData.isEmpty() || sourceData.size() < 2) {
            return result;
        }
        List<String> stringList = sourceData.get(1);
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        List<String> bool = new ArrayList<>(Arrays.asList("N", "Y"));

        for (String inner : stringList) {
            if (bool.contains(inner)) {
                result.add("{N,Y}");
            } else if (pattern.matcher(inner).matches()) {
                result.add("numeric");
            } else {
                result.add("string");
            }
        }


        return result;
    }


    private Instances


}
