package org.cgiar.ccafs.csa.config;

import com.ocpsoft.pretty.PrettyFilter;
import org.h2.server.web.WebServlet;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.faces.webapp.FacesServlet;
import javax.servlet.*;
import java.util.HashMap;

/**
 * This class contains the configuration of the web components, including JSF,
 * LightAdmin and H2
 */
@Configuration
public class WebConfiguration implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    @Autowired
    private Environment env;

    @Value("${csa.tmp-dir}")
    private String tempStorageLocation;

    /**
     * Allows the use of @Scope("view") on Spring @Component, @Service and @Controller beans
     *
     * @return An Scope Configuration bean
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
     * A Custom Bean scope limited to the current view
     *
     * @return a ViewScope instance
     */
    @Bean
    public static ViewScope viewScope() {
        return new ViewScope();
    }

    ///// These methods set up the Faces Servlet for Spring Boot /////

    /**
     * This method initializes JSF options and LightAdmin.
     *
     * @throws javax.servlet.ServletException If something goes wrong
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        LightAdmin.configure(servletContext)
                .basePackage("org.cgiar.ccafs.csa.web.admin")
                .baseUrl("/admin")
                .fileStoragePath(tempStorageLocation)
                .security(true)
                .backToSiteUrl("/index.html")
                .helpUrl("/index.html");

        if (env.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {
            new LightAdminWebApplicationInitializer().onStartup(servletContext);

            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
        } else {
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        }

        /*servletContext.setInitParameter("javax.faces.FACELETS_DECORATORS",
                    "de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator");*/

        servletContext.setInitParameter("org.apache.myfaces.CACHE_EL_EXPRESSIONS", "alwaysRecompile");
        servletContext.setInitParameter("org.apache.myfaces.LOG_WEB_CONTEXT_PARAMS", "false");
        servletContext.setInitParameter("javax.faces.SERIALIZE_SERVER_STATE", "true");
        servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
        servletContext.setInitParameter("primefaces.THEME", "smoothness");
    }

    /**
     * This bean allows for file uploads
     *
     * @return A File upload configuration
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("16MB");
        factory.setMaxRequestSize("16MB");
        return factory.createMultipartConfig();
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
     *
     * @return The RegistrationBean for the ContextVariable
     */
    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(facesServlet(), "*.xhtml");
        registration.setName("FacesServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

    // Filters and servlets that only get instantiated on Embedded/Development mode

    /**
     * @return An instance of the H2 Web administration Servlet.
     */
    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public ServletRegistrationBean h2Servlet() {
        ServletRegistrationBean h2ConsoleServlet = new ServletRegistrationBean(new WebServlet(), "/console/*");
        h2ConsoleServlet.addInitParameter("-webAllowOthers", "true");
        return h2ConsoleServlet;
    }

    /**
     * @return An instance of the filter that allows Prime Faces to handle file uploads.
     */
    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public FilterRegistrationBean facesUploadFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FileUploadFilter(), facesServletRegistration());
        registrationBean.setName("PrimeFaces FileUpload Filter");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registrationBean;
    }

    /**
     * @return An instance of the filter for URL rewriting Pretty Faces library.
     */
    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new PrettyFilter());
        prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR);
        prettyFilter.addUrlPatterns("/*");
        return prettyFilter;
    }
}
