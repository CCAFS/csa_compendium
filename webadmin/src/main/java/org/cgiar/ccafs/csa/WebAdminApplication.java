package org.cgiar.ccafs.csa;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.POSTGRESQL;

/**
 * This configuration creates the datasource for administration
 */
@Configuration
@EnableTransactionManagement
public class WebAdminApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final Logger log = LoggerFactory.getLogger(WebAdminApplication.class);

    @Autowired
    private Environment env;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LightAdmin.configure(servletContext)
                .basePackage("org.cgiar.ccafs.csa.admin")
                .baseUrl("/webadmin")
                .security(false)
                .backToSiteUrl("/index.html")
                .helpUrl("/index.html")
                .fileStoragePath(env.getProperty("csa.tmp-dir"))
                .fileStreaming(true);

        new LightAdminWebApplicationInitializer().onStartup(servletContext);

        super.onStartup(servletContext);
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebAdminApplication.class);
        webApplicationContext.refresh();
        return webApplicationContext;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebAdminApplication.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

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
