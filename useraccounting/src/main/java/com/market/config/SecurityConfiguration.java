package com.market.config;

import com.market.controller.LogoutSuccessHandlerImpl;
import com.market.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * Security Configuration.
 * @author Johannes Weiss
 *
 *         Anstelle von auth.inMemoryAuthentication() wird auth.userDetailsService() in der Methode
 *         globalSecurityConfiguration() verwendet. Das übergebene Interface UserDetailsService wird
 *         per @Autowired eingebunden. Damit Spring die Implementierung findet, wird an die Klasse
 *         die Annotation @ComponentScan(basePackageClasses = AuthenticatedUserService.class)
 *         eingefügt. AuthenticatedUserService ist dabei die Implementierung des Interfaces.
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserDetailsServiceImpl.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  /**
   * This is needed to have the AuthenticationManager managed by spring as a bean.
   */
  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Own implementation of the UserDetailsService. Is responsible for returning the Principal or
   * UserDetails. NOT Authenticated yet.
   */
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  /**
   * Needed to have access to the thrown SpringSecurity authorisation exceptions to handle them.
   */
  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;

  /**
   * Needed to have the oportunity to redirect users depending on thier roles after login.
   */
  @Autowired
  public AuthenticationSuccessHandler authenticationSuccessHandler;

  /**
   * set up something like an authentication-object. NOT SURE
   * 
   * @param auth the authentification Token/Object
   * @throws Exception any Exception.
   */
  @Autowired
  protected void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  /**
   * Needed to encrypt the password before it gets authenticated against the repository.
   * 
   * @return the BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new LogoutSuccessHandlerImpl();
  }

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {

    httpSecurity
    .headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);
    httpSecurity
    .authorizeRequests()
    .antMatchers("/signup").permitAll()
    .antMatchers("/index").permitAll()
    .antMatchers("/login").permitAll()
    .antMatchers("/drag-and-drop01").permitAll()
    .antMatchers("/signup-verification").access("hasRole('ROLE_USERNOTVERIFIED')")
    .antMatchers("/online-users").access("hasRole('ROLE_USER')")
    .antMatchers("/my-requests").access("hasRole('ROLE_USER')")
    .antMatchers("/request").access("hasRole('ROLE_USER')")
    .antMatchers("/new-request").access("hasRole('ROLE_USER')")
    .antMatchers("/chatbox").access("hasRole('ROLE_USER')")
    .antMatchers("/contribution").access("hasRole('ROLE_USER')")
    .antMatchers("/requisition").access("hasRole('ROLE_USER')")
    .antMatchers("/welcome").access("hasRole('ROLE_USER')").and().formLogin().loginPage("/login")
        .failureUrl("/login?error=true").failureHandler(authenticationFailureHandler)
        .successHandler(authenticationSuccessHandler)
        .usernameParameter("username")
        .passwordParameter("password").and()
        .logout()
        .logoutUrl("/execute-logout")
        .invalidateHttpSession(true) // should be true by default anyways
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(logoutSuccessHandler());
        
        //httpSecurity.csrf().disable(); // Disable cross site request forgery protection!!!
  }
  
}