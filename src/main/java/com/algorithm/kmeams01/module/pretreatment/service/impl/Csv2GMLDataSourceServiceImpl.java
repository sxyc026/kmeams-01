package com.algorithm.kmeams01.module.pretreatment.service.impl;

import com.algorithm.kmeams01.common.ExcelTemplate;
import com.algorithm.kmeams01.module.pretreatment.entity.Similarity;
import com.algorithm.kmeams01.module.pretreatment.service.CalSimilaritiesService;
import com.algorithm.kmeams01.module.pretreatment.service.Csv2GMLDataSourceService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 生成GML文件
 */
public class Csv2GMLDataSourceServiceImpl implements Csv2GMLDataSourceService {

    @Autowired
    private CalSimilaritiesService calSimilarities;

    @Override
    public List<Similarity> generateDataSource(String sourceFile, String targetFile, String type) {

        return generateDateSourceUtil(sourceFile, targetFile, type, true);

    }

    @Override
    public List<Similarity> generateDataSource(String sourceFile, String targetFile, String type, boolean isCode) {
        return generateDateSourceUtil(sourceFile, targetFile, type, isCode);

    }

    @Override
    public void generateGMLFile(String sourceFile, String targetFile, String type, boolean isCode) {

        List<Similarity> similarities = generateDataSource(sourceFile, targetFile, type, isCode);
        String gmlData = getGMLData(similarities);
        try {
            FileUtils.write(new File(targetFile),gmlData, Charset.forName("UTF-8"),false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成GML所用的数据源
     * @param similarities
     * @return
     */
    private String getGMLData(List<Similarity> similarities){

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


    /**
     * 生成可用数据单元
     *
     * @param sourceFile
     * @param targetFile
     * @param type
     * @param isCode
     */
    private List<Similarity> generateDateSourceUtil(String sourceFile, String targetFile, String type, boolean isCode) {

        List<List<String>> csvDataWithCode = ExcelTemplate.getCsvDataWithCode(sourceFile);
        if (csvDataWithCode.size() == 0) {
            return null;
        }

        //计算相似度
        List<Similarity> similarityList = calSimilarities.calSimilarities(csvDataWithCode, type, true);

        return similarityList;

    }

}
