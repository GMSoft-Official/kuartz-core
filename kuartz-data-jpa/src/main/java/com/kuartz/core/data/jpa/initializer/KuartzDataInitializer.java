package com.kuartz.core.data.jpa.initializer;

import com.kuartz.core.common.util.KzUtil;
import com.kuartz.core.data.jpa.configuration.property.KuartzJpaProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class KuartzDataInitializer implements InitializingBean {

    @Autowired
    private KuartzJpaProperty kuartzJpaProperty;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private final ResourceLoader resourceLoader;

    public KuartzDataInitializer(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (kuartzJpaProperty.getEnable()) {
            List<String> scripts = kuartzJpaProperty.getDataSource();
            List<Resource> resources = scripts.stream()
                                              .map(s -> resourceLoader.getResource("classpath:" + s))
                                              .collect(Collectors.toList());

            if (!KzUtil.isEmpty(resources)) {
                ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
                populator.setContinueOnError(kuartzJpaProperty.getContiveOnError());
                for (Resource resource : resources) {
                    populator.addScript(resource);
                    EncodedResource encodedScript = new EncodedResource(resource, dataSourceProperties.getSqlScriptEncoding());
                    KuartzScriptUtil.executeSqlScript(dataSource.getConnection(), encodedScript,
                                                      kuartzJpaProperty.getContiveOnError(), true,
                                                      kuartzJpaProperty.getSqlCommentPrefix(),
                                                      kuartzJpaProperty.getSqlLineSeperator(),
                                                      KuartzScriptUtil.DEFAULT_BLOCK_COMMENT_START_DELIMITER,
                                                      KuartzScriptUtil.DEFAULT_BLOCK_COMMENT_END_DELIMITER);
                    DatabasePopulatorUtils.execute(populator, dataSource);
                }
            }
        }
    }

}
