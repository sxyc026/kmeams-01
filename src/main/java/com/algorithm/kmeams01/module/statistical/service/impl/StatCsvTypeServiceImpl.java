package com.algorithm.kmeams01.module.statistical.service.impl;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.statistical.entity.Element;
import com.algorithm.kmeams01.module.statistical.entity.ExistType;
import com.algorithm.kmeams01.module.statistical.service.StatCsvTypeService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StatCsvTypeServiceImpl implements StatCsvTypeService {
    @Override
    public List<Double> compareCsvType(String sourceFile, String targetFile, int type) {

        List<List<String>> sourceCsvDataWithCode = ExcelTemplate.getCsvData(sourceFile);
        List<List<String>> targetCsvDataWithCode = ExcelTemplate.getCsvData(targetFile);
        List<Double> result = new ArrayList<>();
        if (sourceCsvDataWithCode.isEmpty() || targetCsvDataWithCode.isEmpty()) {
            return result;
        }
        int count = getShortListCount(sourceCsvDataWithCode, targetCsvDataWithCode);
        ExistType sourceExistType = checkType("id", sourceCsvDataWithCode.get(0));
        ExistType targetExistType = checkType("证型", targetCsvDataWithCode.get(0));
        if (sourceExistType.isExist() && targetExistType.isExist()) {
            List<Element> source = new LinkedList<>();
            List<Element> target = new LinkedList<>();


        }



        return result;
    }

    /**
     * 判断某种类型是否存在
     *
     * @param type
     * @param sourceList
     * @return
     */
    private ExistType checkType(String type, List<String> sourceList) {
        ExistType existType = new ExistType();
        if (type.isEmpty()) {
            type = "id";
        }
        for (int i = 0; i < sourceList.size(); i++) {
            if (type.equalsIgnoreCase(sourceList.get(i))) {
                existType.setExist(true);
                existType.setCount(i);
                existType.setType(type);
                break;
            }
        }
        return existType;
    }


    private List<Element> getElementList(String type, int colume, List<List<String>> dataList) {
        return null;
    }

    private List<List<String>>updateTarget(List<List<String>>sourceList){
        List<List<String>> targetList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++) {

        }
        return targetList;

    }

    /**
     * 获取子列中最短的
     *
     * @param sourceCsvDataWithCode
     * @param targetCsvDataWithCode
     * @return
     */
    private int getShortListCount(List<List<String>> sourceCsvDataWithCode, List<List<String>> targetCsvDataWithCode) {
        if (sourceCsvDataWithCode.isEmpty() || targetCsvDataWithCode.isEmpty()) {
            return 0;
        }
        int sourceSize = sourceCsvDataWithCode.get(0).size();
        int targetSize = targetCsvDataWithCode.get(0).size();
        return sourceSize > targetSize ? targetSize : sourceSize;
    }

    @Override
    public void compareCsvType(String sourceFile, String targetFile) {

    }
}
