package com.growdev.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
* classe de configuração - dentro dessa classe nós temo alu um método que gera um code de segurança */
@Configuration
public class AppConfig {

  //bean é um component do spring, annotation somente para método.
  //injetar lá no UsuarioSevice
  @Bean //normalmente é colocado dentro de classe de configuraçao
  public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

}
