package com.algorithm.kmeams01.module;

import com.algorithm.kmeams01.module.statistical.service.CsvDataService;
import com.algorithm.kmeams01.module.statistical.service.impl.CsvDataServiceImpl;

import java.util.List;

public class StatMail {

    public static void main(String[] args) {

        String sourceFile="F:\\Data\\data_waitDeal.csv";
        String targetFile = "E:\\toBeSovled\\ChaoyiLiu\\attributeWithType.csv";
        CsvDataService csvDataService = new CsvDataServiceImpl();
        List<Double> compare2CsvList = csvDataService.compare2Csv(sourceFile, targetFile);
    }
}
