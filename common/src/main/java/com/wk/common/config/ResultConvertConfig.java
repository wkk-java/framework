package com.wk.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 结果转换配置.
 */
@Configuration
public class ResultConvertConfig {
	/**
	 * 使用bean方式注入fastjson解析器.
	 * @return http结果转换器
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 创建fastjson对象
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig confg = new FastJsonConfig();
		// 设置是否需要格式化
		confg.setSerializerFeatures(SerializerFeature.PrettyFormat);
		confg.setDateFormat("yyyy-MM-dd HH:mm:ss");
		converter.setFastJsonConfig(confg);
		return new HttpMessageConverters(converter);
	}
}