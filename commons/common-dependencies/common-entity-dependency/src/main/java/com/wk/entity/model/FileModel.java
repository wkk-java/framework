package com.wk.entity.model;

import lombok.Data;

@Data
public class FileModel {
	/**
	 * 文件地址
	 */
	private String link;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 原始文件名
	 */
	private String originalName;
}