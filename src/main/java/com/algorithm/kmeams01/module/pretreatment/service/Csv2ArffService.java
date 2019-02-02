package com.algorithm.kmeams01.module.pretreatment.service;


import java.util.List;

public interface Csv2ArffService {
    boolean GenerFile(String sourceFile, String targetFile);

    List<List<String>> getCsvData(String sourceFile);

    void csv2ArrfWithoutCN(String sourceFile, String targetFile);
}
