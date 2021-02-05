package com.wk.spring.annnotation;

import com.wk.spring.selector.LogPrintSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogPrintSelector.class)
public @interface EnableMethodLogPrint {
}
