package com.algorithm.kmeams01.module.pretreatment.service;


import java.util.List;

public interface Csv2ArffService {
    abstract public boolean GenerFile(String sourceFile,String targetFile);

    abstract public List<List<String>> getCsvData(String sourceFile);
}
