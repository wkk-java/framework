package com.wk.flink;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.util.Collector;

@Slf4j
public class AccessCountFlatMapFunction implements FlatMapFunction<String, Tuple4<String, String, String, Integer>> {

    @Override
    public void flatMap(String line, Collector<Tuple4<String, String, String, Integer>> collector) {
        if (StringUtils.isBlank(line)) {
            return;
        }
        String[] wordsTemp = line.split(" ");
//            log.info("数据:{},{},{}", wordsTemp[0], wordsTemp[1], wordsTemp[2]);
        collector.collect(new Tuple4<>(wordsTemp[0], wordsTemp[1], wordsTemp[2], 1));
    }
}