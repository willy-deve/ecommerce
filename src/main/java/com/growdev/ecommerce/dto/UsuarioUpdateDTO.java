package com.growdev.ecommerce.dto;


import com.growdev.ecommerce.validation.update.UsuarioUpdateValidation;
import lombok.Getter;
import lombok.Setter;

@UsuarioUpdateValidation
@Getter
@Setter
public class UsuarioUpdateDTO extends UsuariosDTO{
    private String senha;
}
