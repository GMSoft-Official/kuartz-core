package com.kuartz.core.data.jpa.configuration;

import com.kuartz.core.data.jpa.configuration.property.KuartzJpaProperty;
import com.kuartz.core.data.jpa.initializer.KuartzDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Kutay Celebi
 * @since 7.10.2019
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({KuartzJpaProperty.class})
@Import(KuartzDataInitializer.class)
public class KuartzJpaConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setDatabase(jpaProperties.getDatabase());
        jpaVendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        jpaVendorAdapter.setShowSql(jpaProperties.isShowSql());
        jpaVendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());

        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new
                LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("com.kuartz");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(convertJpaProperties());
        return factory;
    }

    private Properties convertJpaProperties() {
        return new Properties() {{
            setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
            setProperty("hibernate.dialect", jpaProperties.getDatabasePlatform());
            setProperty("hibernate.show_sql", String.valueOf(jpaProperties.isShowSql()));
            setProperty("hibernate.format_sql", "false");
        }};
    }

}

