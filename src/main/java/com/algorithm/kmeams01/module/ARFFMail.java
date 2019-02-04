package com.algorithm.kmeams01.module;

import com.algorithm.kmeams01.module.pretreatment.service.impl.Csv2ArffServiceImpl;

import java.util.Date;

public class ARFFMail {

    public static void main(String[] args) {
        String sourceFile = "F:\\Data\\data_waitDeal.csv";
        String targetFile = "F:\\Data\\data_waitDeal.arff";
        new Csv2ArffServiceImpl().GenerFile(sourceFile, targetFile);
        System.out.println(new Date());
    }
}
