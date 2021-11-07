package com.wk.config.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义日期转换配置.
 */
@Log4j2
@Component
public class DateConverter implements Converter<String, Date> {
    /**
     * 转换器.
     */
    private static final List<String> DATE_FORMATER_TEXT = new ArrayList<>(4);

    static {
        DATE_FORMATER_TEXT.add("yyyy-MM");
        DATE_FORMATER_TEXT.add("yyyy-MM-dd");
        DATE_FORMATER_TEXT.add("yyyy-MM-dd hh:mm");
        DATE_FORMATER_TEXT.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, DATE_FORMATER_TEXT.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, DATE_FORMATER_TEXT.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, DATE_FORMATER_TEXT.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, DATE_FORMATER_TEXT.get(3));
        } else {
            throw new IllegalArgumentException("Invalid Date value '" + source + "'");
        }
    }

    /**
     * 日期转换.
     *
     * @param dateStr 日期字符串
     * @param format  格式化
     * @return 日期
     */
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            log.error("日期转换出错了:{0}", e);
        }
        return date;
    }
}