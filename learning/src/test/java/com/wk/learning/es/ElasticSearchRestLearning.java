package com.wk.learning.es;

import com.alibaba.fastjson.JSONObject;
import com.wk.learning.JunitApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

/**
 * rest方式操作es.
 * 官方doc: https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-supported-apis.html
 */
@Slf4j
public class ElasticSearchRestLearning extends JunitApplicationRunner {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void addIndexTest() throws IOException {
        ProductInfoIndex productInfoIndex = new ProductInfoIndex();
        productInfoIndex.setId("1111");
        productInfoIndex.setCreateTime(new Date().getTime());
        productInfoIndex.setName("rest方式创建的最便宜的爆款拖鞋了");
        productInfoIndex.setPrice(Math.random() * 100);
        productInfoIndex.setTitle("rest方式创建的最便宜的爆款拖鞋了");
        productInfoIndex.setRemark("rest方式过来的数据");

        IndexRequest indexRequest = new IndexRequest("index_product_info2");
        indexRequest.source(JSONObject.toJSONString(productInfoIndex), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", indexResponse);
    }


}
