package app.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Registers the DelegatingFilterProxy to use the springSecurityFilterChain before any other registered Filter
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
