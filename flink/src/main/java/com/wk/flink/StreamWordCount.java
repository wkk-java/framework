//package com.wk.flink;
//
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//
//public class StreamWordCount {
//    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(1);
//        String input = "D:\\workspace\\research\\framework\\flink\\src\\main\\resources\\flink_word_count.txt";
//        DataStream<String> dataSource = env.readTextFile(input);
//
//        DataStream<Tuple2<String, Integer>> result = dataSource.flatMap(new AccessCountFlatMapFunction())
//                .keyBy(0)
//                .sum(1);
//
//        result.print();
//        env.execute();
//    }
//}
