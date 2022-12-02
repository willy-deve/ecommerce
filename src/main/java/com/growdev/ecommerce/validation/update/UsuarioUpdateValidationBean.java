package com.growdev.ecommerce.validation.update;

import com.growdev.ecommerce.dto.UsuariosDTO;
import com.growdev.ecommerce.entities.Usuario;
import com.growdev.ecommerce.exceptions.FieldMessage;
import com.growdev.ecommerce.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioUpdateValidationBean implements ConstraintValidator<UsuarioUpdateValidation, UsuariosDTO> {

    @Autowired
    private UsuariosRepository repository;

    @Autowired // injetamos aqui uma classe que trabalha diretamente com o servidor, ela traz as requisições do cliente
    private HttpServletRequest request;


    @Override
    public void initialize(UsuarioUpdateValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UsuariosDTO usuariosDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked") //tira as chamadas de atenção / warning
                //pegamos todos os atributos da requisição
                var uriAtributos = (Map <String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                Long userId = Long.parseLong(uriAtributos.get("id"));
        List<FieldMessage> list = new ArrayList<>();

        Usuario usuario = repository.findByEmail(usuariosDTO.getEmail());

        if(usuario != null && usuario.getId() != userId ){
            list.add(new FieldMessage("email", "Esse email ja existe"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
