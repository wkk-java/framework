package com.wk.learning.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.util.Collector;

/**
 * 有界数据测试.
 */
@Slf4j
public class BoundDataTestLearning {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        String input = "D:\\workspace\\research\\framework\\learning\\src\\test\\java\\com\\wk\\learning\\flink\\flink_word_count.txt";
        DataSet<String> dataSource = env.readTextFile(input);

        AggregateOperator<Tuple4<String, String, String, Integer>> result = dataSource.flatMap(new AcessCountFlatMapFunction())
                .groupBy(0)
                .sum(3);

        result.print();
    }


    public static class AcessCountFlatMapFunction implements FlatMapFunction<String, Tuple4<String, String, String, Integer>> {
        @Override
        public void flatMap(String line, Collector<Tuple4<String, String, String, Integer>> collector) {
            String[] wordsTemp = line.split("\\t");
            log.info("数据:{},{},{}", wordsTemp[0], wordsTemp[1], wordsTemp[2]);
            collector.collect(new Tuple4<>(wordsTemp[0], wordsTemp[1], wordsTemp[2], 1));
        }
    }
}
