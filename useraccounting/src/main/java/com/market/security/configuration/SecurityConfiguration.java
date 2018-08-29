package com.market.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("testusr1").password("pwd1").roles("USER").and()
      .withUser("testusr2").password("pwd2").roles("ADMIN");
  }
  
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
        
        .antMatchers("/signup").anonymous()
        .antMatchers("/index").anonymous()
        .antMatchers("/welcomeMember").fullyAuthenticated()
        .and().httpBasic();
    httpSecurity.csrf().disable();
  }
}