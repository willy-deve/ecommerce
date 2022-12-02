package com.growdev.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //Aqui é o método das liberações das rotas
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/**"); //liberei todos endpoints
  }
}
