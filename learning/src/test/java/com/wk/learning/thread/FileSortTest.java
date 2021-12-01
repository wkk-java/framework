package com.wk.learning.thread;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

import static org.apache.flink.shaded.zookeeper.org.apache.zookeeper.ZooDefs.OpCode.getData;

public class FileSortTest {

    private static final String FILE_NAME = "d:/sort.txt";

    private List<String> dataList = new ArrayList<>();

    public static void main(String[] args) {
        FileSortTest fileSortTest = new FileSortTest();
//        fileSortTest.createFile();
        fileSortTest.getData();
    }


    public void sortData() {
        List<Double> dataList = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            dataList.add(Math.random());
        }
        double[] objects = dataList.toArray(new double[]);
        DoubleStream.of(objects).parallel().sorted();
    }

    public void getData() {
        long startTime = System.currentTimeMillis();
        System.out.println("开始获取数据");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                dataList.add(tmp);
            }
            System.out.println("获取数据结束.,耗时:" + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println(dataList.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10_000_000; i++) {
            buffer.append(Math.random() * 1000).append("\n");
        }
        try {
            File file = new File(FILE_NAME);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
