package app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "app.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private MySavedRequestAwareAuthenticationSuccessHandler successHandler;
    private CustomUserDetailsService userDetailsService;

    @Inject
    public WebSecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                             MySavedRequestAwareAuthenticationSuccessHandler successHandler,
                             CustomUserDetailsService userDetailsService) {
        this.successHandler = successHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public GlobalMethodSecurityConfiguration methodSecurityService() {
        return new GlobalMethodSecurityConfiguration();
    }

    /**
     * Used to configure AuthenticationManager
     * @param auth the{@link AuthenticationManagerBuilder}
     * @throws Exception throws when enable to apply userDetailsService
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).configure(auth);
    }

    /**
     * Configure Spring security
     * @param http allows configuring web based security for specific http requests
     * @throws Exception can be thrown when cors configured
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("app/goods", "app/users")
                .permitAll()
                .antMatchers("/app/orders/**", "/app/orders")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .formLogin()
                .successHandler(successHandler)
                //.failureHandler()//failureHandler
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/j_spring_security_logout")
                .permitAll()
                .and()
                .sessionManagement()
                .sessionFixation()
                .migrateSession();
    }

}

