package com.algorithm.kmeams01.module.pretreatment.service;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;

import java.util.List;

public interface Csv2GMLDataSourceService {


    String getGMLData(List<Similarity> similarities);

}
