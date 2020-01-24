package app.config.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static app.constants.Constants.*;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackages = "app")
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext context;

    public WebConfig(final ApplicationContext context) {
        this.context = context;
    }

    /**
     * Set parametres for InternalResourceViewResolver
     * @param registry {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERN).addResourceLocations(RESOURCE_LOCATION);
    }

}

