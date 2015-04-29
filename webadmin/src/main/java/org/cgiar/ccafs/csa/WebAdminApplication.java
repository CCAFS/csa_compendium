package org.cgiar.ccafs.csa;

import org.cgiar.ccafs.csa.config.LightAdminConfiguration;
import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This configuration creates the datasource for administration
 */
public class WebAdminApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final Logger log = LoggerFactory.getLogger(WebAdminApplication.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LightAdmin.configure(servletContext)
                .basePackage("org.cgiar.ccafs.csa.web.admin")
                .baseUrl("/webadmin")
                .security(false)
                .backToSiteUrl("/index.html")
                .helpUrl("/index.html")
                .fileStreaming(true);

        new LightAdminWebApplicationInitializer().onStartup(servletContext);

        super.onStartup(servletContext);
    }

    /*@Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(LightAdminConfiguration.class);
        webApplicationContext.refresh();
        return webApplicationContext;
    }*/

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{LightAdminConfiguration.class};
    }
}
