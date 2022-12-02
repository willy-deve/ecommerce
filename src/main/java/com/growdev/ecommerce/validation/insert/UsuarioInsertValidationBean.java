package com.growdev.ecommerce.validation.insert;

import com.growdev.ecommerce.dto.UsuariosDTO;
import com.growdev.ecommerce.entities.Usuario;
import com.growdev.ecommerce.exceptions.FieldMessage;
import com.growdev.ecommerce.repositories.UsuariosRepository;
import com.growdev.ecommerce.validation.insert.UsuarioInsertValidation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

//A CLASSE IMPLEMENTA O CONSTRAINTVALIDATOR POIS HAVERÁ UMA ANNOTATION LIGADA A CLASSE E UMA CLASSE QUE RECEBERÁ A ANNOTATION
public class UsuarioInsertValidationBean implements ConstraintValidator<UsuarioInsertValidation, UsuariosDTO> {


  @Autowired
  private UsuariosRepository repository;

  @Override
  public void initialize(UsuarioInsertValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  //ele que é o validador real
  @Override
  public boolean isValid(UsuariosDTO value, ConstraintValidatorContext context) {

    //pra futuramente, receber a minha lista de erros
    List<FieldMessage> list = new ArrayList<>();

    //verificar se o email já existe
    Usuario usuario =  repository.findByEmail(value.getEmail());

    if(usuario != null){
      //uma excecao padrao
      list.add(new FieldMessage("email", "Esse email já existe"));
    }

    for(FieldMessage e : list){
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
        .addConstraintViolation();
    }

    return list.isEmpty();
  }
}
