package com.growdev.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/*
* classe de configuração - dentro dessa classe nós temo alu um método que gera um code de segurança */
@Configuration
public class AppConfig {

  //bean é um component do spring, annotation somente para método.
  //injetar lá no UsuarioSevice
  @Bean //normalmente é colocado dentro de classe de configuraçao
  public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

  @Bean
  public JwtAccessTokenConverter accessTokenConverter(){
    JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();//instancia o uso de token
    tokenConverter.setSigningKey("MINHA-SENHA-SECRETA");//registro na chave do token do meu segredo
    return tokenConverter;//retorno do token
  }

  @Bean
  public JwtTokenStore tokenStore(){
    return new JwtTokenStore(accessTokenConverter());// retorna token para uso
  }


}
