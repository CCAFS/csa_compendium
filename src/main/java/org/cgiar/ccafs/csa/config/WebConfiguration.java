package org.cgiar.ccafs.csa.config;

import java.util.HashMap;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.cgiar.ccafs.csa.ViewScope;
import org.h2.server.web.WebServlet;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.sun.faces.config.ConfigureListener;

/**
 * This class contains the configuration of the web components, including JSF,
 * LightAdmind and H2
 */
@Configuration
public class WebConfiguration implements ServletContextInitializer {
	
	private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);
	
	@Autowired
    private Environment env;	
	
	/**
     * This method initializes LightAdmin and the H2 Console if in DEV mode
     */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {		
		LightAdmin.configure(servletContext)
	        .basePackage("org.cgiar.ccafs.csa.web.admin")
	        .baseUrl("/admin")
	        .security(false)
	        .backToSiteUrl("/")
	        .helpUrl("/");

		new LightAdminWebApplicationInitializer().onStartup(servletContext);
		
		if (env.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {
			log.debug("Initialize H2 console");
	        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new WebServlet());
	        h2ConsoleServlet.addMapping("/console/*");
	        h2ConsoleServlet.setLoadOnStartup(1);
	    }
	}
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024KB");
        factory.setMaxRequestSize("1024KB");
        return factory.createMultipartConfig();
    }

    
    ///// These methods set up the Faces Servlet for Spring Boot /////
    
    /**
     * Allows the use of @Scope("view") on Spring @Component, @Service and @Controller beans
     */
    @Bean
    public static CustomScopeConfigurer scopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("view", viewScope());
        configurer.setScopes(hashMap);
        return configurer;
    }

    /**
     * @return An instance of a Servlet that handles JSF requests
     */
    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

    /**
     * Registers the xhtml files to be handled by the Faces Servlet
     * @return The RegistrationBean for the ContextVariable
     */
    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(facesServlet(), "*.xhtml");
        registration.setName("FacesServlet");
        return registration;
    }

    /**
     * This Bean can be used to register Event Listeners for the container
     * @return A ConfigureListener for JSF events registration
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }
    
    /**
     * A Custom Bean scope limited to the current view
     * @return a ViewScope instance
     */
    @Bean
    public static ViewScope viewScope() {
        return new ViewScope();
    }

}
