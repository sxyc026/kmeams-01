package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;

import java.util.List;

public interface Csv2GMLService {

    /**
     * 生成Gephi 和 Kmeans 可用的数据文件
     * @param sourceFile
     * @param targetFile
     * @param type
     */
    List<Similarity> generateDataFile(String sourceFile, String targetFile, String type);

    /**
     * 生成Gephi 和 Kmeans 可用的数据文件，可定义是否增加编号
     * @param sourceFile
     * @param targetFile
     * @param type
     */
    List<Similarity> generateDataFile(String sourceFile, String targetFile, String type, boolean isCode);

}
