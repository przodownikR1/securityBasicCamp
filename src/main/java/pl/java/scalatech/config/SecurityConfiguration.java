package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.annotation.SecurityComponent;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(EncryptConfig.class)
@Slf4j
@ComponentScan(basePackages = { "pl.java.scalatech.security" }, useDefaultFilters = false, includeFilters = { @Filter(SecurityComponent.class) })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring().
        antMatchers("/assets/**")
        .antMatchers("/css/**")
        .antMatchers("/js/**")
        .antMatchers("/images/**")
        .antMatchers("/favicon.ico")
        .antMatchers("/webjars/**");
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .formLogin().defaultSuccessUrl("/welcome").and()
        .logout()
        .and()

        .csrf().disable().headers().disable()
          .authorizeRequests().antMatchers("/login","/logout","secContext","principal","/health").permitAll()
          .antMatchers("/simple/**").hasAnyRole("USER")
          .antMatchers("/api/admin/**").hasRole("ADMIN")
          .antMatchers("/actuator/**").hasRole("ADMIN")
          .antMatchers("/metrics/**").hasRole("ADMIN")
          .antMatchers("/info/**").hasRole("ADMIN")
          .antMatchers("/health/**").hasRole("ADMIN")
          .antMatchers("/trace/**").hasRole("ADMIN")
          .antMatchers("/dump/**").hasRole("ADMIN")
          .antMatchers("/shutdown/**").hasRole("ADMIN")
          .antMatchers("/beans/**").hasRole("ADMIN")
          .antMatchers("/env/**").hasRole("ADMIN")
          .antMatchers("/autoconfig/**").hasRole("ADMIN")


          .anyRequest().permitAll();
          // @formatter:on
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
     // @formatter:off

        auth.ldapAuthentication()
            .userDnPatterns("uid={0}, ou=people")
            .groupSearchBase("ou=groups")
            .contextSource()
                .ldif("classpath:users.ldif");
     // @formatter:on
    }

}