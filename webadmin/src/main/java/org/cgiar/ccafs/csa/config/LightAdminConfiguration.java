package org.cgiar.ccafs.csa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.POSTGRESQL;

/**
 *
 */
@Configuration
@EnableTransactionManagement
public class LightAdminConfiguration {

    private final Logger log = LoggerFactory.getLogger(LightAdminConfiguration.class);

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        vendorAdapter.setDatabase(POSTGRESQL);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.cgiar.ccafs.csa.domain");
        factory.setDataSource(dataSource);

        factory.setJpaProperties(jpaProperties());

        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public DataSource dataSource() {
        JndiTemplate jndi = new JndiTemplate();
        DataSource dataSource = null;
        String jndiName = "jdbc/PostgreSQLDS";
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/" + jndiName);
        } catch (NamingException e) {
            log.error("NamingException for " + jndiName);
        }
        return dataSource;
    }

    private Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.ejb.naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy");
        return jpaProperties;
    }
}
