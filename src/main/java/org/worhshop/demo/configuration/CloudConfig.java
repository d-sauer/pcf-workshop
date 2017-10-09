package org.worhshop.demo.configuration;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class CloudConfig extends AbstractCloudConfig {

    @Bean
    public DataSource demoDataSource() {
        return connectionFactory().dataSource();
    }
}
