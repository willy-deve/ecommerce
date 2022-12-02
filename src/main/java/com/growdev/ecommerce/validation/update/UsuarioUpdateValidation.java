package com.growdev.ecommerce.validation.update;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsuarioUpdateValidationBean.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioUpdateValidation {
    String message() default "Validator error";
    Class<?>[] groups() default{};
    Class <? extends Payload>[] payload() default{};
}
