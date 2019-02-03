package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.impl.Csv2ArffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class Csv2GMLService {

    @Autowired
    private CalSimilaritiesService calSimilaritiesService;

    @Autowired
    private Csv2GMLDataSourceService csv2GMLDataSourceService;


    public void generateGML(String sourceFile, String targetFile, String type, boolean isCode, boolean isRate, boolean isWeighted) {

        //生成原始数据
        List<List<String>> csvDataWithCode = ExcelTemplate.getCsvDataWithCode(sourceFile);
        //获取数据源
        List<Similarity> similarities = calSimilaritiesService.calSimilarities(csvDataWithCode, type, isRate, isCode, isWeighted);

        String gmlData = csv2GMLDataSourceService.getGMLData(similarities);
        System.out.println(gmlData);
    }


    public static void main(String[] args) {
        String sourceFile = "F:\\Data\\data_waitDeal.csv";
        String targetFile = "F:\\Data\\data_waitDeal.gml";
        new Csv2GMLService().generateGML(sourceFile,targetFile,"无向",true,true,true);
        System.out.println(new Date());
    }
}
