package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.impl.CalSimilaritiesServiceImpl;
import com.algorithm.kmeams01.module.pretreatment.service.impl.Csv2GMLDataSourceServiceImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Csv2GMLService {


    public void generateGML(String sourceFile, String targetFile, String type, boolean isCode, boolean isRate, boolean isWeighted) {


        //生成原始数据
        List<List<String>> csvDataWithCode = ExcelTemplate.getCsvDataWithCode(sourceFile);
        //获取数据源
        CalSimilaritiesService calSimilaritiesService = new CalSimilaritiesServiceImpl();
        List<Similarity> similarities = calSimilaritiesService.calSimilarities(csvDataWithCode, type, isRate, isCode, isWeighted);

        Csv2GMLDataSourceService csv2GMLDataSourceService = new Csv2GMLDataSourceServiceImpl();
        String gmlData = csv2GMLDataSourceService.getGMLData(similarities);
        createGML(targetFile, gmlData);

    }


    /**
     * 生成GML文件
     *
     * @param targetFile
     * @param context
     */
    private void createGML(String targetFile, String context) {

        try {
            FileUtils.write(new File(targetFile), context, Charset.forName("UTF-8"), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
