/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author Admin
 */
@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {
    @Autowired
    private Environment env;
    
    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan("com.lhn.pojo");
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(hibernateProperties());
        
        return factory;
    }
    
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName(env.getProperty("hibernate.connection.driverClass"));
        d.setUrl(env.getProperty("hibernate.connection.url"));
        d.setUsername(env.getProperty("hibernate.connection.username"));
        d.setPassword(env.getProperty("hibernate.connection.password"));
        
        return d;
    }
    
    @Bean
    public Properties hibernateProperties(){
        Properties props = new Properties();
        props.setProperty(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        props.setProperty(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.showSql"));
        
        return props;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager m = new HibernateTransactionManager();
        m.setSessionFactory(getSessionFactory().getObject());
        
        return m;
    }
}
