package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.validation.insert.UsuarioInsertValidation;
import lombok.Getter;
import lombok.Setter;

@UsuarioInsertValidation
@Getter
@Setter
public class UsuarioInsertDTO extends UsuariosDTO {

  private String senha;

}
