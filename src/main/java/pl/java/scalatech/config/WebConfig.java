package pl.java.scalatech.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import lombok.extern.slf4j.Slf4j;
//@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurationSupport{
  @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/accessdenied").setViewName("accessdenied");
    }

  @Bean
  // @Profile("converter")
  public DomainClassConverter<?> domainClassConverter() {
      return new DomainClassConverter<>(mvcConversionService());
  }
  @Bean
  public FilterRegistrationBean filterRegistrationBeanEncoding() {
      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
      CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
      characterEncodingFilter.setEncoding("UTF-8");
      characterEncodingFilter.setForceEncoding(true);
      registrationBean.setFilter(characterEncodingFilter);
      return registrationBean;
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBeanHidden() {
      FilterRegistrationBean registrationBean = new FilterRegistrationBean();
      registrationBean.setFilter(new HiddenHttpMethodFilter());
      return registrationBean;
  }
  @Bean
  public SpringSecurityDialect securityDialect() {
      return new SpringSecurityDialect();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {  // mapujemy statyczne zasoby
      registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(3000);
      registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/").setCachePeriod(0);
      registry.addResourceHandler("/images/**").addResourceLocations("classpath:/resources/images/").setCachePeriod(3000);
      registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico").setCachePeriod(3000);
      registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
      registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/").setCachePeriod(3000);

  }
  @Override   // spring boot juz to ma
  public Validator getValidator() {
      LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
      localValidatorFactoryBean.setValidationMessageSource(messageSource);
      return localValidatorFactoryBean;
  }
}
