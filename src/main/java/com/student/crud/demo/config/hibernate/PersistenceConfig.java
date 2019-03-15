package com.student.crud.demo.config.hibernate;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource("persistence-sqlite.properties")
public class PersistenceConfig {

  private final Environment env;

  @Autowired
  public PersistenceConfig(final Environment env) {
    this.env = env;
    assert this.env != null;
  }

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
    dataSource.setUrl(env.getProperty("url"));
    dataSource.setUsername(env.getProperty("username"));
    dataSource.setPassword(env.getProperty("password"));
    return dataSource;
  }

  final Properties additionalProperties() {
    final Properties hibernateProperties = new Properties();
    if (env.getProperty("hibernate.show_sql") != null) {
      hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    }
    return hibernateProperties;
  }

  @Configuration
  @Profile("sqlite")
  @PropertySource("classpath:persistence-sqlite.properties")
  class SqliteConfig {
  }
}
