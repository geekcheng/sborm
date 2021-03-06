package com.sborm.core;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * 实体基类，处理表相关的东西，比如分表，数据源选择  ...
 * 1、选择查询表
 * 2、选择查询数据源（jdbctemplate）
 * 3、读写分离处理
 * 
 * @author fengli
 * @date 2014-7-7 下午3:47:25
 *
 */
public abstract class BaseEntity {

	/**
	 * 分表标识比如 _00 _01 _201401 ...  要带上完整的分表后缀
	 */
	private String subTableFlag = "";
	private String databaseRouterKey;
	
	// 通用属性
	private Long groupCount;	// 在分组统计的时候通用属性，sql中写死
	
	public String getSubTableFlag() {
		return subTableFlag;
	}
	public void setSubTableFlag(String subTableFlag) {
		this.subTableFlag = subTableFlag;
	}
	public Long getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(Long groupCount) {
		this.groupCount = groupCount;
	}
	
	/**
	 * 设置数据库路由key
	 * @param key
	 */
	public void setDatabaseRouterKey(String key) {
		databaseRouterKey = key;
	}
	
	/**
	 * 获取数据库路由key，为空则采用默认规则
	 * @return
	 */
	public String getDatabaseRouterKey() {
		return databaseRouterKey;
	}
	
	/**
	 * 获取数据库名
	 * @return
	 */
	public String getDatabase() {
		return EntityContainer.getDatabase(this);
	}
	
	/**
	 * 获取实体影射实例
	 * @return
	 */
	public ParameterizedRowMapper<?> getRowMapper() {
		return EntityContainer.getRowMapper(this.getClass());
	}
	
	/**
	 * 获取表名
	 * @return
	 */
	public String getFullTableName() {
		return EntityContainer.getTable(this) + this.subTableFlag;
	}
}
