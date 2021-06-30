package com.wk.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple4;

import java.util.List;

/**
 * 有界数据测试.
 */
@Slf4j
public class BoundDataTestLearning {

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        ExecutionEnvironment env = ExecutionEnvironment.createCollectionsEnvironment();//.createRemoteEnvironment("jr-server", 8081);
        String input = "D:\\workspace\\research\\framework\\flink\\src\\main\\resources\\flink_word_count.txt";
        DataSet<String> dataSource = env.readTextFile(input);
        log.info("开始收集");
        DataSet<Tuple4<String, String, String, Integer>> result = dataSource.flatMap(new AccessCountFlatMapFunction());
        List<Tuple4<String, String, String, Integer>> collect = result.groupBy(0).sum(3).sortPartition(1, Order.DESCENDING).collect();
        log.info("截止收集");
        log.info("计算后数据:{}", collect);
        log.info("用时:{}s", (System.currentTimeMillis() - startTime) / 1000);

        List<Tuple4<String, String, String, Integer>> collect1 = result
                //多字段分组
                .groupBy(0, 1, 2)
                .sum(3)
//                .partitionByRange(0, 3)
                //多重排序
                .sortPartition(0, Order.DESCENDING)
                .sortPartition(1, Order.DESCENDING)
                .sortPartition(3, Order.DESCENDING)
                .collect();
        log.info("计算后数据1:{}", collect1);
    }


}
