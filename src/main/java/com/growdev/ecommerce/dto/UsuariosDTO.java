package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuariosDTO {

  private Long id;
  @NotBlank(message = "Campo Obrigatório")
  private String nome;
  private String email;

  private Set<RolesDTO> roles = new HashSet<>();

  public UsuariosDTO(Usuario entity) {
    this.id = entity.getId();
    this.nome = entity.getNome();
    this.email = entity.getEmail();
    entity.getRoles().forEach(r -> this.roles.add(new RolesDTO(r)));
  }
}
