package com.github.ethanpang.config.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceType {
	 String value() default DataSourceContextHolder.DAFAULT_DATA_SOURCE;
}
