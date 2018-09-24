package com.market.config;

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

import com.market.service.UserDetailsServiceImpl;

/** 
 * @author Johannes Weiss
 *
 *Anstelle von auth.inMemoryAuthentication() wird auth.userDetailsService() in der Methode
 *globalSecurityConfiguration() verwendet. Das übergebene Interface UserDetailsService wird
 *per @Autowired eingebunden. Damit Spring die Implementation findet, wird an die Klasse
 *die Annotation @ComponentScan(basePackageClasses = AuthenticatedUserService.class)
 *eingefügt. AuthenticatedUserService ist dabei unsere Implementierung des Interfaces.
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
   * Own implementation of the UserDetailsService.
   * Is responsible for returning the Principal or UserDetails. NOT Authenticated yet.
   */
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  
  /**
   * Needed to have access to the thrown SpringSecurity authorisation exceptions to handle them.
   */
  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;
  
  /**
   * set up something like an authentication-object. NOT SURE
   * @param auth the authentification Token/Object
   * @throws Exception
   */
  @Autowired
  protected void GlobalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
  
  /**
   * Needed to encrypt the password before it gets authenticated against the repository.
   * @return the BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
 
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    
    httpSecurity.authorizeRequests()        
        .antMatchers("/signup").permitAll()
        .antMatchers("/index").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/verification").permitAll()
        .antMatchers("/welcome").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
        .and()
          .formLogin().loginPage("/login")
          .defaultSuccessUrl("/welcome")
          .failureUrl("/login?error=true")
          .failureHandler(authenticationFailureHandler)
          .usernameParameter("username").passwordParameter("password");
       
           
    httpSecurity.csrf().disable(); //Disable cross site scripting
  }
}