package com.algorithm.kmeams01.module.pretreatment.service;


public interface Csv2ArffService {
    boolean GenerFile(String sourceFile, String targetFile);

    void csv2ArrfWithoutCN(String sourceFile, String targetFile);
}
