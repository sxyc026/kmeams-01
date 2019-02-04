package com.algorithm.kmeams01.module.statistical.service.impl;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.statistical.service.CsvDataService;

import java.util.ArrayList;
import java.util.List;

public class CsvDataServiceImpl implements CsvDataService {

    @Override
    public List<Double> compare2Csv(String sourceFile, String targetFile) {

        List<List<String>> sourceCsvData = ExcelTemplate.getCsvData(sourceFile);
        List<List<String>> targetCsvData = ExcelTemplate.getCsvData(targetFile);
        List<Double> result = new ArrayList<>();
        if (sourceCsvData.isEmpty() || targetCsvData.isEmpty()) {
            return result;
        }
        int dataCount = sourceCsvData.size();
        int sourceCount = sourceCsvData.get(0).size();
        int targetCount = targetCsvData.get(0).size();
        int count = sourceCount < targetCount ? sourceCount : targetCount;


        for (int i = 0; i < dataCount; i++) {
            List<String> sourceList = sourceCsvData.get(i);
            List<String> targetList = targetCsvData.get(i);
            int countInner = 0;
            for (int j = 0; j < count; j++) {
                if (sourceList.get(j).equalsIgnoreCase(targetList.get(j))) {
                    countInner++;
                }

            }
            result.add(countInner / count * 100.0);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(i+1 + "  " + result.get(i));
        }
        return result;
    }


}
