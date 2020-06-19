package com.hc.mall.listservice.impl;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TestClass {

    @Autowired
    JestClient jestClient;


    @RequestMapping("test")
    public void Test() throws IOException {
        // 测试能否与es联通

        /*
            1. 定义dsl语句
            2. 定义执行的动作
            3. 执行动作
            4. 获取执行之后的结果
         */

        // 1. 定义dsl语句
        String query = "{\n" +
                "  \"query\": {\n" +
                "    \"term\": {\n" +
                "       \"actorList.name\": \"张译\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        //2. 定义执行的动作
        //   GET /movie_chn/movie_type_chn/_search
        Search search = new Search.Builder(query).addIndex("movie_chn").addType("movie_type_chn").build();

        // 3. 执行动作
        SearchResult searchResult = jestClient.execute(search);

        // 4. 获取执行之后的结果
        List<SearchResult.Hit<Map, Void>> hits = searchResult.getHits(Map.class);

        for(SearchResult.Hit<Map, Void> hit : hits) {
            Map map = hit.source;
            System.out.println(  map.get("name")); // 红海行动
        }
    }


}
