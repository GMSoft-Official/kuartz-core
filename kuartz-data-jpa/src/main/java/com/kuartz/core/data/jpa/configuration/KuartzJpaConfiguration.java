package com.kuartz.core.data.jpa.configuration;

import com.kuartz.core.data.jpa.bean.KuartzAuditorAware;
import com.kuartz.core.data.jpa.configuration.property.KuartzAuditProperty;
import com.kuartz.core.data.jpa.configuration.property.KuartzJpaProperty;
import com.kuartz.core.data.jpa.initializer.KuartzDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

/**
 * @author Kutay Celebi
 * @since 7.10.2019
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({KuartzJpaProperty.class, KuartzAuditProperty.class})
@EntityScan(basePackages = "com.kuartz.core.data.jpa.entity")
@Import(KuartzDataInitializer.class)
public class KuartzJpaConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    @ConditionalOnProperty(prefix = "kuartz.jpa.audit", name = "enable")
    public KuartzAuditorAware kuartzAuditorAware() {
        return new KuartzAuditorAware();
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


    private Properties convertJpaProperties() {
        return new Properties() {
            private static final long serialVersionUID = 8911623546662622101L;

            {
                setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
                setProperty("hibernate.dialect", jpaProperties.getDatabasePlatform());
                setProperty("hibernate.show_sql", String.valueOf(jpaProperties.isShowSql()));
                setProperty("hibernate.format_sql", "false");
            }
        };
    }

}

