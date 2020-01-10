package com.singh.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.singh.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// replaces spring config xml

	// set up variable to hold the properties
	@Autowired
	private Environment env;

	// set up logger for diagnostics
	private Logger LOGGER = Logger.getLogger(getClass().getName());

	// Define bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// define a bean for our security datasource
	@Bean
	public DataSource securityDataSource() {

		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// set jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// log connection properties
		LOGGER.info("jdbc.url = " + env.getProperty("jdbc.url"));
		LOGGER.info("jdbc.user = " + env.getProperty("jdbc.user"));

		// set database connection properties
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.user"));
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.password"));

		// set connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	// need a helper mehod
	// read enviornment property and convert to int

	private int getIntProperty(String propertyName) {
		String propertyValue = env.getProperty(propertyName);
		int intPropertyValue = Integer.parseInt(propertyValue);
		return intPropertyValue;
	}

}
