package com.market;

import java.util.Locale;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@SpringBootApplication(scanBasePackages = { "com.market" })
public class UseraccountingApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(UseraccountingApplication.class, args);
  }

  /**
   * INTERNATIONALIZATION In order for our application to be able to determine which locale is
   * currently being used, we need to add a LocaleResolver bean. The LocaleResolver interface has
   * implementations that determine the current locale based on the session, cookies, the
   * Accept-Language header, or a fixed value. In our example, we have used the session based
   * resolver SessionLocaleResolver and set a default locale with value US.
   * 
   * @return SessionLocaleResolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.GERMAN);
    return slr;
  }

  /**
   * INTERNATIONALIZATION Next, we need to add an interceptor bean that will switch to a new locale
   * based on the value of the lang parameter appended to a request.
   * 
   * @return LocaleChangeInterceptor
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  /**
   * In order to take effect, the bean above needs to be added to the application’s interceptor
   * registry.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
