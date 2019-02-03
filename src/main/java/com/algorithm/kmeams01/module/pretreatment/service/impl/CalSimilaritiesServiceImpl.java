package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.CalSimilaritiesService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class CalSimilaritiesServiceImpl implements CalSimilaritiesService {


    @Override
    public List<Similarity> calSimilarities(List<List<String>> csvData, String type, boolean isRate, boolean isCode, boolean isWeighted) {

        if (type == null || type.isEmpty()) {
            type = "undirected";
        }
        //计算相似度
        List<Similarity> similarityList = new ArrayList<>();
        int count = csvData.size();
        for (int i = 0; i < count; i++) {

            for (int j = 0; j < count; j++) {
                if (i == j) {
                    continue;
                }
                Similarity similarity = new Similarity();
                similarity.setSource(i + "");
                similarity.setTarget(j + "");
                similarity.setWeight(calWeightUtil(csvData.get(i), csvData.get(j), isRate, isCode, isWeighted));
                similarity.setType(type);
                similarityList.add(similarity);
            }

        }
        return similarityList;
    }

    /**
     * 计算相似度
     *
     * @param source     源数据
     * @param target     目标数据
     * @param isRate  是否返回百分比
     * @param isWeighted 是否计算百分比后乘以100
     * @return
     */
    private Double calWeightUtil(List<String> source, List<String> target, boolean isRate, boolean isWeighted, boolean isCode) {

        int weight = 0;

        int count = source.size();
        if (count != target.size() || count == 0) {
            return 0.0;
        }

        for (int i = (isCode ? 1 : 0); i < source.size(); i++) {
            if (source.get(i).equalsIgnoreCase(target.get(i))) {
                weight++;
            }

        }

        if (isRate) {

            BigDecimal denominator = new BigDecimal(count);
            BigDecimal numerator = new BigDecimal(weight);
            BigDecimal result = numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
            if (isWeighted) {
                result = result.multiply(new BigDecimal(100));
            }
            return result.doubleValue();
        }

        return Double.valueOf(weight);
    }
}
