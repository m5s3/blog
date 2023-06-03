package com.m5s3.blog.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement

@ConditionalOnProperty(prefix = "blog-api.datasource.mysql.slave", name = ["jdbc-url"])
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.m5s3.blog.domain"])
@EnableJpaRepositories(
    basePackages = ["com.m5s3.blog.repository"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef =  "transactionManager"
)
@EnableJpaAuditing
class JpaConfig(
    private val jpaProperties: JpaProperties,
    private val dataSourceConfig: DataSourceConfig
) {
    @Bean("entityManagerFactory")
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSourceConfig.slaveDataSource()
            setPackagesToScan("com.m5s3.blog.domain")
            jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
                setShowSql(jpaProperties.isShowSql)
                setGenerateDdl(jpaProperties.isGenerateDdl)
                setJpaPropertyMap(jpaProperties.properties)
            }
            afterPropertiesSet()
        }
    }

    @Bean
    fun transactionManager(): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactory().`object`!!)
    }
}