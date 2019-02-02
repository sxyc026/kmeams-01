package com.algorithm.kmeams01.module.pretreatment.service.impl;

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


    @Override
    public boolean GenerFile(String sourceFile, String targetFile) {

        List<List<String>> sourceData = getCsvData(sourceFile);

        if (sourceData.isEmpty() || sourceData.size() < 2) {
            return false;
        }

        BufferedWriter out;
        BufferedReader in;
        try {
            out = new BufferedWriter(new FileWriter(targetFile, false));
            out = appendBW(out, "@relation" + "  medical");
            List<String> attributeList = getColumeAttribute(sourceData);
            List<String> attribute = sourceData.get(0);
            int count = attribute.size();
            for (int i = 0; i < count; i++) {
                out = appendBW(out, "@attribute " + attribute.get(i) + " " + attributeList.get(i));
            }
            out = appendBW(out, "@data");
            String appendData;
            for (int i = 1; i < sourceData.size(); i++) {
                if (sourceData.isEmpty() || sourceData.size() == 0) {
                    continue;
                }
                appendData = StringUtils.join(sourceData.get(i), ",");
                out = appendBW(out, appendData);
            }
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 生成文件-追加内容
     *
     * @param bufferedWriter
     * @param context
     * @return
     */
    private BufferedWriter appendBW(BufferedWriter bufferedWriter, String context) {
        try {
            bufferedWriter.write(context);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedWriter;
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
        BufferedReader br;
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(csv), "GBK");
            br = new BufferedReader(inputStreamReader);
            String line;
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
