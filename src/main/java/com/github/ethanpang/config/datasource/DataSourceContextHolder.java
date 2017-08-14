package com.github.ethanpang.config.datasource;

public class DataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	// 主数据库
	public static final String DAFAULT_DATA_SOURCE = "defaultDataSource";
	// 只读数据库
	/**
	 * 设置数据源类型
	 * 
	 * @param dataSource
	 *            数据库类型
	 */
	public static void setDataSourceType(String dataSource) {
		contextHolder.set(dataSource);
	}

	/**
	 * 获取数据源类型
	 * 
	 * @return
	 */
	public static String getDataSourceType() {
		return contextHolder.get();
	}

	/**
	 * 清除数据源类型
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}

}
