package com.algorithm.kmeams01.module.cluster.service;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;

public class init {

    public void calKMeans(String sourceFile) {
        Instances instances;
        SimpleKMeans simpleKMeans;

        File file = new File(sourceFile);
        ArffLoader loader = new ArffLoader();
        try {
            loader.setFile(file);
            instances = loader.getDataSet();

            simpleKMeans = new SimpleKMeans();
            simpleKMeans.setNumClusters(5);
            simpleKMeans.buildClusterer(instances);
            System.out.println(simpleKMeans.preserveInstancesOrderTipText());
            System.out.println(simpleKMeans.toString());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        String sourceFile = "F:\\Data\\test.arff";
        new init().calKMeans(sourceFile);


    }
}
