package com.wk.oss.rule;

import com.wk.oss.constants.StringPool;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class MyOssRule implements OssRule {
	@Override
	public String bucketName(String bucketName) {
		return bucketName;
	}

	@Override
	public String fileName(String prefix,String originalFilename) {
		String pre = StringUtils.isBlank(prefix) ? "upload" : prefix;
		originalFilename = StringUtils.isBlank(originalFilename) ? UUID.randomUUID().toString() : originalFilename;
		return pre + StringPool.SLASH + DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:sss") + StringPool.SLASH + originalFilename;
	}
}
