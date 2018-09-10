package com.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.market.service.AuthenticatedUserService;

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
@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = AuthenticatedUserService.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  protected void GlobalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }
  
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
       
    httpSecurity.authorizeRequests()        
        .antMatchers("/signup").anonymous()
        .antMatchers("/index").anonymous()
        .antMatchers("/login").anonymous()
        .antMatchers("/welcomeMember").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
        .and()
          .formLogin().loginPage("/login")
          .defaultSuccessUrl("/welcomeMember")
          .failureUrl("/login?error")
          .usernameParameter("username").passwordParameter("password");
        //.antMatchers("/welcomeMember").fullyAuthenticated().and().formLogin().loginPage("/login");
           
    httpSecurity.csrf().disable(); //Disable cross site scripting
  }
}