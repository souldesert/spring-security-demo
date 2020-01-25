package ru.voskhod.springsecurity.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.voskhod.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

    // set up variables to hold the properties
    private Environment env;

    @Autowired
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    // set up a logger for diagnostics
    private Logger logger = Logger.getLogger(getClass().getName());

    // define a bean for ViewResolver

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // define a bean for security datasource
    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource
                = new ComboPooledDataSource();

        // set the jdbc driver class
        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // log the connection props
        logger.info(">>> jdbc.url = " + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user = " + env.getProperty("jdbc.user"));

        // set database connection props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize(
                getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(
                getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(
                getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(
                getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // a helper method to convert int properties from String
    private int getIntProperty(String propName) {
         return Integer.parseInt(Objects.requireNonNull(env.getProperty(propName)));
    }
}
