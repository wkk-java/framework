package com.wk.oss.rule;

public interface OssRule {
	/**
	 * 获取存储桶规则
	 *
	 * @param bucketName 存储桶名称
	 * @return String
	 */
	String bucketName(String bucketName);

	/**
	 * 获取文件名规则
	 *
	 * @param prefix 前缀 可以作为区分
	 * @param originalFilename 文件名
	 * @return String
	 */
	String fileName(String prefix,String originalFilename);
}
