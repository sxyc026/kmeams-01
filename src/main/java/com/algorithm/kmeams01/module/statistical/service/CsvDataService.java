package com.algorithm.kmeams01.module.statistical.service;

import java.util.List;

public interface CsvDataService {

    List<Double> compare2Csv(String sourceFile, String targetFile);
}
