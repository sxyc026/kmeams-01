package com.algorithm.kmeams01.module.statistical.service;

import java.util.List;

public interface StatCsvTypeService {

    List<Double> compareCsvType(String sourceFile, String targetFile, int type);

    void compareCsvType(String sourceFile,String targetFile);
}
