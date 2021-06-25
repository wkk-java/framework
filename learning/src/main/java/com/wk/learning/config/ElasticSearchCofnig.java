//package com.wk.learning.es;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
// 可通过springboot yaml配置
//@Configuration
//public class ElasticSearchCofnig {
//
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("wk-server1", 9200, "http"));
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", System.getenv("SECURITY_USER_PASSWORD")));
//        restClientBuilder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
//        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
//        return client;
//    }
//}
