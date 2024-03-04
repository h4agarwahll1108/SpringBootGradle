package com.harshit1930815.ticketSystem.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//6. Create a Custom DataSource and JDBCTemplate Bean - 5 points
@Configuration
public class CustomDataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = { "datasource" })
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    // Creating a JdbcTemplate bean named "jdbcTemplate"
    @Bean(name = { "jdbcTemplate" })
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource") DataSource datasource) {
        // Creating and returning a JdbcTemplate with the specified DataSource
        return new JdbcTemplate(datasource);
    }

//    // Creating a DirectChannel bean named "from0b"
//    @Bean("from0b")
//    public DirectMessageChannel getChannel() {
//        // Creating and returning a DirectMessageChannel
//        return MessageChannels.direct().get();
//    }

}