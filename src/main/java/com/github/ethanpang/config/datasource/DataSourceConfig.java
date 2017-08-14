package com.github.ethanpang.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ethan on 2017/7/28.
 */
@Configuration
public  class DataSourceConfig {
    @Bean(name="default")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public  DataSource defaultDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name="readonly")
    @ConfigurationProperties(prefix = "spring.datasource.readonly")
    public  DataSource readOnlyDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource());
        Map<Object,Object> dsMap=new HashMap<>();
        dsMap.put("defaultDataSource",defaultDataSource());
        dsMap.put("readOnlyDataSource",readOnlyDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
}
