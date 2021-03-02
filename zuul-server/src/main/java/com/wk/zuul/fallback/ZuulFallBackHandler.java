package com.wk.zuul.fallback;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: vince
 * create at: 2019/12/31 14:03
 * @description: 失败回退
 */
@Component
public class ZuulFallBackHandler implements FallbackProvider {
    private final Logger logger = LoggerFactory.getLogger(ZuulFallBackHandler.class);
    /**
     * 指定处理的serviceId.
     * @return serviceId
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * @param route 路由,即serviceId
     * @param cause 错误对象信息
     * @return 客户端响应
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause != null) {
            String reason = cause.getMessage();
            logger.warn("Exception {}", reason);
        }
        return new MyClientHttpResponse(route, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), cause.getMessage());
    }

    /**
     * 内部类.
     * @author: vince
     * create at: 2019/12/31 14:38
     * @description: http响应
     */
    private class MyClientHttpResponse implements ClientHttpResponse {

        /**
         * logger.
         */
        private final Logger logger = LoggerFactory.getLogger(MyClientHttpResponse.class);
        /**
         * serviceId.
         */
        private String serviceId;
        /**
         * http状态对象.
         */
        private HttpStatus httpStatus;
        /**
         * 状态文本.
         */
        private String statusText;

        /**
         * 描述.
         */
        private String description;

        /**
         * @param serviceId serviceId
         * @param httpStatus 状态
         * @param statusText 状态文本
         * @param description 描述信息
         */
        public MyClientHttpResponse(String serviceId, HttpStatus httpStatus, String statusText, String description) {
            this.serviceId = serviceId;
            this.httpStatus = httpStatus;
            this.statusText = statusText;
            this.description = description;
        }

        @Override
        public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.httpStatus;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.httpStatus.value();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.statusText;
        }

        @Override
        public void close() {
            logger.debug("close..");
        }

        @Override
        public InputStream getBody() throws IOException {
            Map<String, String> result = new HashMap<>();
//            result.put("serviceId", serviceId);
            result.put("httpStatus", String.valueOf(httpStatus.value()));
            result.put("statusText", statusText);
            result.put("description", description);
            return new ByteArrayInputStream(JSONObject.toJSONString(result).getBytes());
        }
    }
}
