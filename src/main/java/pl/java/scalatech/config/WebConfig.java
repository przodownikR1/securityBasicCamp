package pl.java.scalatech.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

}
