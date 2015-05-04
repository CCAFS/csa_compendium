package org.cgiar.ccafs.csa.config;

import com.ocpsoft.pretty.PrettyFilter;
import org.h2.server.web.WebServlet;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.richfaces.webapp.ResourceServlet;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * To be deployed only when standalone mode
 */
@Configuration
@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
public class StandaloneConfiguration implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new WebServlet());
        h2ConsoleServlet.addMapping("/console");
    }

    /**
     * @return An instance of the filter for URL rewriting pretty faces library
     */
    @Bean
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new PrettyFilter());
        prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR);
        prettyFilter.addUrlPatterns("/*");
        return prettyFilter;
    }

    /**
     * @return An instance of a Servlet that handles Rich Faces Resources
     */
    @Bean
    public ResourceServlet richFacesResourceServlet() {
        return new ResourceServlet();
    }


    /**
     * Registers Rich Faces resource handler
     *
     * @return The RegistrationBean for the Rich Faces Servlet
     */
    @Bean
    public ServletRegistrationBean richFacesRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(richFacesResourceServlet(),
                "/org.richfaces.resources/*");
        registration.setName("Rich Resource Servlet");
        return registration;
    }

    /**
     * This Bean can be used to register Event Listeners for the container
     *
     * @return A ConfigureListener for JSF events registration
     */
    /*@Bean
    public ServletListenerRegistrationBean<StartupServletContextListener> jsfConfigureListener() {
        StartupServletContextListener listener = new StartupServletContextListener();
        return new ServletListenerRegistrationBean<>(listener);
    }*/
}
