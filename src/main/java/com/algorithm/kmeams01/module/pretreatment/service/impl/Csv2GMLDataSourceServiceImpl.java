package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.Csv2GMLDataSourceService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 生成GML文件
 */
public class Csv2GMLDataSourceServiceImpl implements Csv2GMLDataSourceService {

    
    /**
     * 生成GML所用的数据源
     *
     * @param similarities
     * @return
     */
    @Override
    public String getGMLData(List<Similarity> similarities) {

        //edges
        Set<String> edgesSet = new HashSet<>();
        //nodes
        Set<String> nodesSet = new HashSet<>();

        for (Similarity similarity : similarities) {
            String source = similarity.getSource();
            String target = similarity.getTarget();

            if (source == null || target == null) {
                continue;
            }
            nodesSet.add(source);
            nodesSet.add(target);
            StringBuffer edgeInner = new StringBuffer("edge\n")
                    .append("[\n")
                    .append("  source ").append(source).append("\n")
                    .append("  target ").append(target).append("\n")
                    .append("  weight ").append(similarity.getWeight()).append("\n")
                    .append("]\n");

            edgesSet.add(edgeInner.toString());

        }

        StringBuffer nodesBuffer = new StringBuffer();
        for (String node : nodesSet) {
            nodesBuffer.append("node\n")
                    .append("[\n")
                    .append("  id ").append(node).append("\n")
                    .append("]\n");
        }

        StringBuffer edgesBuffer = new StringBuffer();
        for (String edge : edgesSet) {
            edgesBuffer.append(edge);
        }
        StringBuffer stringBuffer = new StringBuffer("graph\n").append("[\n");

        stringBuffer.append(nodesBuffer.toString());
        stringBuffer.append(edgesBuffer.toString());
        stringBuffer.append("\n]");

        return stringBuffer.toString();
    }


}
