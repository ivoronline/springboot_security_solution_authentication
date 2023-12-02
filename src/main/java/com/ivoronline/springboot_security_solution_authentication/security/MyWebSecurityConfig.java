package com.ivoronline.springboot_security_solution_authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  //Enables @Secured & @PreAuthorize
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // CONFIGURE
  //=================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ANONYMOUS ACCESS TO AUTHENTICATION ENDPOINT
    httpSecurity.authorizeRequests().antMatchers("/Authenticate").permitAll();

    //ALLOW ACCESS TO H2 CONSOLE
    httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();  //ENABLE POST TO AUTHENTICATE

  }

  //=======================================================================
  // PASSWORD ENCODER
  //=======================================================================
  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

}

