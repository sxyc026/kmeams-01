package com.algorithm.kmeams01.module;

import com.algorithm.kmeams01.module.pretreatment.service.Csv2GMLService;

import java.util.Date;

public class GMLMain {

    public static void main(String[] args) {
        Csv2GMLService csv2GMLService = new Csv2GMLService();
        String sourceFile = "F:\\Data\\data_waitDeal.csv";
        String targetFile = "F:\\Data\\data_waitDeal.gml";
        csv2GMLService.generateGML(sourceFile, targetFile, "无向", true, true, true);
        System.out.println("文件：" + targetFile + "  \n创建时间：" + new Date());
    }
}
