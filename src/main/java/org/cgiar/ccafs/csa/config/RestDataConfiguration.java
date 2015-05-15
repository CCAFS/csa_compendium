package org.cgiar.ccafs.csa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;

/**
 * This Spring Configuration class extends the spring-boot default Rest Mvc configuration
 * to place a new base path.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class RestDataConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        //config.setBasePath("/rest");
        config.setBaseUri(URI.create("/rest"));
    }
}