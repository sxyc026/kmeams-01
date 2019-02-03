package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.pretreatment.service.Csv2ArffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class Csv2ArffServiceImpl implements Csv2ArffService {


    /**
     * 数据处理：将csv文件处理成 arff文件
     *
     * @param sourceFile
     * @param targetFile
     * @return
     */
    @Override
    public boolean GenerFile(String sourceFile, String targetFile) {

        List<List<String>> sourceData = ExcelTemplate.getCsvData(sourceFile);
        int sourceDataSize = sourceData.size();

        if (sourceData.isEmpty() || sourceDataSize < 2) {
            return false;
        }
        String arffString = getArffString(sourceData);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(targetFile)), "GB2312"));
            bufferedWriter.write(arffString);
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 生成Arff内容字符串
     *
     * @param sourceData
     * @return
     */
    private String getArffString(List<List<String>> sourceData) {

        int sourceDataSize = sourceData.size();
        StringBuffer stringBuffer = new StringBuffer("");

        stringBuffer = appendStringBuffer(stringBuffer, "@relation" + "  medical", 2);
        List<String> attributeList = getColumeAttribute(sourceData);
        List<String> attribute = sourceData.get(0);
        int count = attribute.size();
        for (int i = 0; i < count; i++) {
            stringBuffer = appendStringBuffer(stringBuffer, "@attribute " + attribute.get(i) + " " + attributeList.get(i), 1);
        }
        stringBuffer = appendStringBuffer(stringBuffer, "", 1);
        stringBuffer = appendStringBuffer(stringBuffer, "@data", 1);
        String appendData;
        for (int i = 1; i < sourceDataSize; i++) {
            if (sourceData.isEmpty() || sourceData.size() == 0) {
                continue;
            }
            appendData = StringUtils.join(sourceData.get(i), ",");
            stringBuffer = appendStringBuffer(stringBuffer, appendData, 1);
        }
        return stringBuffer.toString();

    }


    /**
     * 追加文件字符串内容
     *
     * @param stringBuffer
     * @param string
     * @param count
     * @return
     */
    private StringBuffer appendStringBuffer(StringBuffer stringBuffer, String string, int count) {
        stringBuffer.append(string);
        while (count > 0) {
            stringBuffer.append("\r\n");
            count--;
        }
        return stringBuffer;
    }


    /**
     * 生成属性值
     *
     * @param sourceData
     * @return
     */
    private List<String> getColumeAttribute(List<List<String>> sourceData) {

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

    /**
     * csv 文件转 arrf
     *
     * @param sourceFile
     * @param targetFile
     */
    public void csv2ArrfWithoutCN(String sourceFile, String targetFile) {

        CSVLoader csvLoader = new CSVLoader();
        try {
            csvLoader.setSource(new File(sourceFile));
            Instances instance = csvLoader.getDataSet();

            ArffSaver arffSaver = new ArffSaver();
            arffSaver.setInstances(instance);
            arffSaver.setFile(new File(targetFile));
            arffSaver.writeBatch();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        String sourceFile = "F:\\Data\\data_waitDeal.csv";
        String targetFile = "F:\\Data\\data_waitDeal.arff";
        new Csv2ArffServiceImpl().GenerFile(sourceFile, targetFile);
        System.out.println(new Date());
    }


}
