package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;

import java.util.List;

public interface CalSimilaritiesService {

    /**
     * 计算给定数据的相似度
     *
     * @param csvData
     * @param type
     * @return
     */
    List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isCode);

    Double calWeightPercentAndWeighted(List<String> source, List<String> target, boolean isCode);

    Double calWeightPercent(List<String> source, List<String> target, boolean isCode);

    Double calWeight(List<String> source, List<String> target, boolean isCode);

}
