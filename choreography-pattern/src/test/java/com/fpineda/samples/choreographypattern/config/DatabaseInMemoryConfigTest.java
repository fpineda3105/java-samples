/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 13:05:22
 * @modify date 2020-10-09 13:05:22
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.config;

import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@TestConfiguration
public class DatabaseInMemoryConfigTest {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdD;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("test");
        dataSource.setPassword("test$123");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        return transactionManager;
    }
}
