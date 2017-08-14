package com.github.ethanpang.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("数据源为{}",DataSourceContextHolder.getDataSourceType());
		return DataSourceContextHolder.getDataSourceType();
	}

//	@Override
//	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
//		super.setDefaultTargetDataSource(defaultTargetDataSource);
//	}
}
