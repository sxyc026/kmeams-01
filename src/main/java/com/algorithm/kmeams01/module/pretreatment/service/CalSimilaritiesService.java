package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;

import java.util.List;

public interface CalSimilaritiesService {

    /**
     * 计算给定数据的相似度
     *
     * @param csvData
     * @param type
     * @param isPercent
     * @param isCode
     * @param isWeighted
     * @return
     */
    List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isPercent, boolean isCode, boolean isWeighted);

    /**
     * 计算给定数据的相似度,默认带编号、占比*100
     *
     * @param csvData
     * @param type
     * @return
     */
    List<Similarity> calSimilarities(List<List<String>> csvData, String type);

    /**
     * 计算给定数据的相似度
     *
     * @param csvData
     * @param type
     * @param isCode
     * @return
     */
    List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isCode);


}
