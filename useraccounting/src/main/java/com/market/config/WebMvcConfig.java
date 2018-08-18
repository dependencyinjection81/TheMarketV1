package com.market.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * INTERNATIONALIZATION In order for our application to be able to determine which locale is
   * currently being used, we need to add a LocaleResolver bean. The LocaleResolver interface has
   * implementations that determine the current locale based on the session, cookies, the
   * Accept-Language header, or a fixed value. In our example, we have used the session based
   * resolver SessionLocaleResolver and set a default locale with value US.
   * 
   * @return SessionLocaleResolver
   */
  @Bean(name = "localeResolver")
  public LocaleResolver getLocaleResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setCookieDomain("myAppLocaleCookie");
    // 60 minutes
    resolver.setCookieMaxAge(60 * 60);
    return resolver;
  }

  /**
   * Tells the validator where to find the message resource.
   * @return
   */
  @Bean
  public javax.validation.Validator validator() {
    final LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
    factory.setValidationMessageSource(getMessageResource());
    return factory;
  }

  /**
   * Message source.
   * 
   * @return message resource
   */
  @Bean(name = "messageSource")
  public MessageSource getMessageResource() {
    ReloadableResourceBundleMessageSource messageResource = 
        new ReloadableResourceBundleMessageSource();

    // Read i18n/messages_xxx.properties file.
    // For example: i18n/messages_en.properties
    messageResource.setBasename("classpath:i18n/messages");
    messageResource.setDefaultEncoding("UTF-8");
    return messageResource;
  }

  /**
   * In order to take effect, the bean above needs to be added to the application’s interceptor
   * registry.
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
    localeInterceptor.setParamName("lang");
    registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
  }

}
