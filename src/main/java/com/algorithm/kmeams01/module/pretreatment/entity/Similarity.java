package com.algorithm.kmeams01.module.pretreatment.entity;

import lombok.Data;

/**
 * 存储相似度
 */
@Data
public class Similarity {

    private String source;

    private String target;

    private Double weight;

    private String type;
}
