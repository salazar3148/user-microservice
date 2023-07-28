package com.pragma.powerup.usermicroservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static com.pragma.powerup.usermicroservice.configuration.aws.ParameterStore.getParameterValue;
import javax.sql.DataSource;

@Configuration
public class dbConnectionConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Bean
    public DataSource dataSourceInitializer(){

        String parameterNamePrefix = "/users/" + activeProfile + "/";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(
                "jdbc:mysql://" + getParameterValue(parameterNamePrefix + "DB_URL") + "/users"
        );
        dataSource.setUsername(
                getParameterValue(parameterNamePrefix + "DB_USERNAME")
        );
        dataSource.setPassword(
                getParameterValue(parameterNamePrefix + "DB_PASSWORD")
        );
        return dataSource;
    }
}
