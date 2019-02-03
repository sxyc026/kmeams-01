package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.Csv2GMLService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成GML文件
 */
public class Csv2GMLServiceImpl implements Csv2GMLService {

    @Override
    public List<Similarity> generateDataFile(String sourceFile, String targetFile, String type) {

       return generateDateFileUtil(sourceFile, targetFile, type, true);

    }

    @Override
    public List<Similarity> generateDataFile(String sourceFile, String targetFile, String type, boolean isCode) {
        return generateDateFileUtil(sourceFile, targetFile, type, isCode);

    }

    /**
     * 生成可用数据单元
     *  @param sourceFile
     * @param targetFile
     * @param type
     * @param isCode
     */
    private List<Similarity> generateDateFileUtil(String sourceFile, String targetFile, String type, boolean isCode) {

        List<List<String>> csvDataWithCode = ExcelTemplate.getCsvDataWithCode(sourceFile);
        if (csvDataWithCode.size() == 0) {
            return null;
        }

        //计算相似度
        List<Similarity> similarityList = calSimilarities(csvDataWithCode, type, true);

        return similarityList;

    }

    /**
     *生成GML数据
     * @param similarityList
     */
    private void generateGMLFile(List<Similarity> similarityList ){




    }


    /**
     * 计算给定数据的相似度
     *
     * @param csvData
     * @param type
     * @return
     */
    private List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isCode) {

        if (type == null || type.isEmpty()) {
            type = "undirected";
        }
        //计算相似度
        List<Similarity> similarityList = new ArrayList<>();
        int count = csvData.size();
        for (int i = 0; i < count; i++) {

            for (int j = 0; j < count; j++) {
                if (i == j) {
                    continue;
                }
                Similarity similarity = new Similarity();
                similarity.setSource(i + "");
                similarity.setTarget(j + "");
                similarity.setWeight(calWeightPercentAndWeighted(csvData.get(i), csvData.get(j), isCode));
                similarity.setType(type);
                similarityList.add(similarity);
            }

        }
        return similarityList;

    }


    /**
     * 计算两个节点的相似度
     *
     * @param source
     * @param target
     * @return
     */
    private Double calWeight(List<String> source, List<String> target, boolean isCode) {

        return calWeightUtil(source, target, false, false, isCode);

    }

    /**
     * 计算两个节点的相似度,百分比
     *
     * @param source
     * @param target
     * @return
     */
    private Double calWeightPercent(List<String> source, List<String> target, boolean isCode) {

        return calWeightUtil(source, target, true, false, isCode);

    }

    /**
     * 计算两个节点的相似度,百分比
     *
     * @param source
     * @param target
     * @return
     */
    private Double calWeightPercentAndWeighted(List<String> source, List<String> target, boolean isCode) {

        return calWeightUtil(source, target, true, true, isCode);

    }

    /**
     * 计算相似度
     *
     * @param source     源数据
     * @param target     目标数据
     * @param isPercent  是否返回百分比
     * @param isWeighted 是否计算百分比后乘以100
     * @return
     */
    private Double calWeightUtil(List<String> source, List<String> target, boolean isPercent, boolean isWeighted, boolean isCode) {

        int weight = 0;

        int count = source.size();
        if (count != target.size() || count == 0) {
            return 0.0;
        }

        for (int i = (isCode ? 1 : 0); i < source.size(); i++) {
            if (source.get(i).equalsIgnoreCase(target.get(i))) {
                weight++;
            }

        }

        if (isPercent) {

            BigDecimal denominator = new BigDecimal(count);
            BigDecimal numerator = new BigDecimal(weight);
            BigDecimal result = numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
            if (isWeighted) {
                result = result.multiply(new BigDecimal(100));
            }
            return result.doubleValue();
        }

        return Double.valueOf(weight);
    }


    public static void main(String[] args) {
        String sourceFile = "F:\\Data\\data_waitDeal.csv";
        String targetFile = "F:\\Data\\data_waitDeal.arff";
        new Csv2GMLServiceImpl().generateDataFile(sourceFile, targetFile, "无向", true);
        System.out.println(new Date());
    }


}
