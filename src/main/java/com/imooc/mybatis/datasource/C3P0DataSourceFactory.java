package com.imooc.mybatis.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @author SongShengLin
 * @date 2022/4/23 10:27
 * @description 构造器指定Mybatis使用C3P0连接池
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
    /**
     * 构造器指定Mybatis使用C3P0连接池
     */
    public C3P0DataSourceFactory() {
        // ComboPooledDataSource是C3P0自带的连接池
        this.dataSource = new ComboPooledDataSource();
    }
}
