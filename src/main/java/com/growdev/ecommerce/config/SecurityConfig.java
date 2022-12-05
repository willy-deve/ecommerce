package com.growdev.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  //As injeções foram feitas para que o spring securitu entenda que ao logar ele recebera
  //o userDetailsService e uma senha criptografada.
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  //OAUT
  //Aqui é o método das liberações das rotas
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/actuator/**"); //liberei todos endpoints
  }

  //Esse metodo recebe a autenticação
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //userDetailsService + senha criptografada
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }
}
