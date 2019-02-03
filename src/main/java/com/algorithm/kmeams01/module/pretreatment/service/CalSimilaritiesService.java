package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;

import java.util.List;

public interface CalSimilaritiesService {

    /**
     * 计算给定数据的相似度
     *
     * @param csvData
     * @param type
     * @param isRate
     * @param isCode
     * @param isWeighted
     * @return
     */
    List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isRate, boolean isCode, boolean isWeighted);
}
