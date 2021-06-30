package com.wk.learning.es;

import com.alibaba.fastjson.JSONObject;
import com.wk.learning.JunitApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.seqno.RetentionLeaseActions;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.sort.SortOrder;
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

    private static final String TEST_INDEX_NAME = "index_product_info2";

    private ProductInfoIndex build(String name) {
        ProductInfoIndex productInfoIndex = new ProductInfoIndex();
        productInfoIndex.setId("1111");
        productInfoIndex.setCreateTime(new Date().getTime());
        productInfoIndex.setName(name == null ? "rest方式创建的最便宜的爆款拖鞋了" : name);
        productInfoIndex.setPrice(Math.random() * 100);
        productInfoIndex.setTitle("rest方式创建的最便宜的爆款拖鞋了");
        productInfoIndex.setRemark("rest方式过来的数据");
        return productInfoIndex;
    }

    /**
     * 新增文档.
     * @throws IOException
     */
    @Test
    public void addIndexTest() throws IOException {
        ProductInfoIndex productInfoIndex = build(null);
        IndexRequest indexRequest = new IndexRequest(TEST_INDEX_NAME);
        indexRequest.id(productInfoIndex.getId());
        indexRequest.source(JSONObject.toJSONString(productInfoIndex), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", indexResponse);
    }

    /**
     * 批量新增文档.
     * @throws IOException
     */
    @Test
    public void addBatchIndexTest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest(TEST_INDEX_NAME);

        for (int i = 0; i < 10; i++) {
            ProductInfoIndex productInfoIndex = build("rest addBatch方式创建的最便宜的爆款拖鞋了" + i );
            productInfoIndex.setSource(String.valueOf(i % 2));
            bulkRequest.add(
                    new IndexRequest(TEST_INDEX_NAME)
                            .id("batch_" + i)
                            .source(JSONObject.toJSONString(productInfoIndex), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", ! bulkResponse.hasFailures());
    }

    /**
     * 获取单个文档.
     * @throws IOException
     */
    @Test
    public void getIndexTest() throws IOException {
        GetRequest getRequest = new GetRequest(TEST_INDEX_NAME);
        getRequest.id("1111");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", response.getSource());
    }

    /**
     * 更新文档.
     * @throws IOException
     */
    @Test
    public void updateIndexTest() throws IOException {
        ProductInfoIndex productInfoIndex = build("rest update方式创建的最便宜的爆款拖鞋了");

        UpdateRequest updateRequest = new UpdateRequest(TEST_INDEX_NAME, productInfoIndex.getId());
        updateRequest.doc(JSONObject.toJSONString(productInfoIndex), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", response.getResult());
    }

    /**
     * 删除文档.
     * @throws IOException
     */
    @Test
    public void deleteIndexTest() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(TEST_INDEX_NAME, "1111");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        log.info("响应结果:{}", deleteResponse.getResult());
    }

    /**
     * 查找文档.
     * @throws IOException
     */
    @Test
    public void searchIndexTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest(TEST_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //term精确匹配
        QueryBuilder queryBuilder =
        QueryBuilders
                .termQuery("name", "rest")
//                .boolQuery()
//                .should(QueryBuilders.matchQuery("name", "最便宜"))
//                .should(QueryBuilders.matchQuery("name", "rest方式"))
                ;

        //根据taskid进行分组统计，统计出的列别名叫sum
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("source").field("source")
                .subAggregation(AggregationBuilders.avg("price").field("price"));
//        AggregationBuilder aggregationBuilder = AggregationBuilders.avg("price").field("price");

        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("price").order(SortOrder.DESC).sortMode(SortMode.MAX);
        searchSourceBuilder.sort(fieldSortBuilder);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0).size(30);

        //聚合查询
        searchSourceBuilder.explain(true).aggregation(aggregationBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        NumericMetricsAggregation.SingleValue parsedSum = searchResponse.getAggregations().get("price");
        log.info("聚合计算结果:{}", parsedSum.getValueAsString());

        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits) {
            log.info("响应结果:{}", hit.getSourceAsMap());
        }

        log.info("响应结果:{}", JSONObject.toJSONString(searchResponse.getHits()));
    }

}
