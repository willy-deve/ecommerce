package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {

  private Long id;
  @NotBlank(message = "O campo é obrigatório")
  @Size(min = 5, max = 10, message = "Deve ter entre 5 a 10 caracteres")
  private String nome;

  public CategoriaDTO(Categoria entity) {
    this.id = entity.getId();
    this.nome = entity.getNome();
  }

}
