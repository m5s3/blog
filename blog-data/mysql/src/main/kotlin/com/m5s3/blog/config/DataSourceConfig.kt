package com.m5s3.blog.config

import com.m5s3.blog.jpa.MasterSlaveRoutingDataSource
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "blog-api.datasource.mysql.slave")
    fun slaveDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    @ConfigurationProperties(prefix = "blog-api.datasource.mysql.master")
    fun masterDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun routingDataSource() = MasterSlaveRoutingDataSource().apply {
        setTargetDataSources(hashMapOf<Any, Any>(
            "master" to masterDataSource(),
            "slave" to slaveDataSource()))
        setDefaultTargetDataSource(masterDataSource())
    }

    @Bean
    fun lazyDataSource() = LazyConnectionDataSourceProxy(routingDataSource())
}