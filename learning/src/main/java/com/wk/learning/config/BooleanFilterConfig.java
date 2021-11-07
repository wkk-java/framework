//package com.wk.learning.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RBloomFilter;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author: vince
// * create at: 2021/11/6 下午10:00
// * @description: 布隆过滤器配置
// */
//@Configuration
//public class BooleanFilterConfig {
//
//    @Value("${redis.address:wk-server}")
//    private String redisServerAddress;
//
//    @Bean
//    public RBloomFilter<String> bloomFilter(){
//        Config config = new Config();
//        config.useClusterServers().addNodeAddress(redisServerAddress.split(","));
//        RedissonClient redissonClient = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom");
//        //初始化布隆过滤器,预计元素为1000w,误判率为1%(误判率越小,hash次数越多,性能降低)
//        bloomFilter.tryInit(1000 * 10000, 0.01);
//        return bloomFilter;
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
