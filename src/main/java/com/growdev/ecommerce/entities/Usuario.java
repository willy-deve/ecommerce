package com.growdev.ecommerce.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String email;
  private String senha;

  @ManyToMany
  @JoinTable(
    name = "tb_usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Roles> roles = new HashSet<>();





  //TODOS OS METODOS PARA TRABALHAR COM SEGURANÇA NO SPRING


  //Esse metodo é responsavel pela coleção de perfis para o usuario
  //Esse método que diz ao spring security quem são os niveis de acesso
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    //Percorrendo a List de ROLES e mandando ela CONVERTIDA EM UMA LISTA DE GRANTEDAUTHORITY
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());

  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
