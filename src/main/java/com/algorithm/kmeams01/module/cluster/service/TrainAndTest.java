package com.algorithm.kmeams01.module.cluster.service;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader;

import java.io.File;

public class TrainAndTest {

    public void calKmeans() {
        File trainFile = new File("F:\\Data\\kmeans\\race_train.arff");
        File testFile = new File("F:\\Data\\kmeans\\race_test.arff");
        ArffLoader atf = new ArffLoader();

        try {
            // load data
            atf.setFile(trainFile);
            Instances train = atf.getDataSet();
            atf.setFile(testFile);
            Instances test = atf.getDataSet();
            // build clusterer
            SimpleKMeans clusterer = new SimpleKMeans();
            clusterer.setNumClusters(2);
            clusterer.buildClusterer(train);
            System.out.println(clusterer.toString());
            // output predictions
            for (int i = 0; i < test.numInstances(); i++) {
                int cluster = clusterer.clusterInstance(test.instance(i));
                double[] dist = clusterer.distributionForInstance(test.instance(i));
                System.out.print((i + 1));
                System.out.print(" - ");
                System.out.print(cluster);
                System.out.print(" - ");
                System.out.print(Utils.arrayToString(dist));
                System.out.println();
            }
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        new TrainAndTest().calKmeans();
    }


}
