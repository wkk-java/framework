package com.wk.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
	/**
	 * 对象存储服务的URL
	 */
	private String endpoint;

	/**
	 * Access key就像用户ID，可以唯一标识你的账户
	 */
	private String accessKey;

	/**
	 * Secret key是你账户的密码
	 */
	private String secretKey;

//	/**
//	 * 默认的存储桶名称
//	 */
//	private String bucketName = OssConstants.BUCKET_NAME;

	/**
	 * 文件过期时间
	 */
	private Long expireTime = 3600L;
	/**
	 * 文件大小限制 10M
	 */
	private Integer contentLengthRange = 10485760;
	/**
	 * 域名
	 */
	private String domain;
}
