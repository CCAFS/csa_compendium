package org.cgiar.ccafs.csa;

import org.cgiar.ccafs.csa.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * This is a bootstrap class that can be used to run the application as a
 * Standalone embedded server. It also holds Environment and the REST configuration
 */
@Order(HIGHEST_PRECEDENCE)
@SpringBootApplication(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
public class CSAToolApplication extends SpringBootServletInitializer {

    private final static Logger log = LoggerFactory.getLogger(CSAToolApplication.class);

    @Autowired
    private Environment env;

    /**
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     */
    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}",
                    Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CSAToolApplication.class);
        // Check if the selected profile has been set as argument. If not, the development profile will be added
        app.setAdditionalProfiles(addDefaultProfile());
        ConfigurableApplicationContext context = app.run(args);
        for (String name : context.getBeanDefinitionNames()) {
            log.debug(name);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.profiles(addDefaultProfile()).showBanner(false).sources(CSAToolApplication.class);
    }

    /**
     * Set a default profile if it has not been set. Use -Dspring.profiles.active=dev
     */
    private static String addDefaultProfile() {
        String profile = System.getProperty("spring.profiles.active");
        if (profile == null) {
            profile = Constants.SPRING_PROFILE_DEVELOPMENT;
        }
        return profile;
    }

}
