package com.xy.validator.bean.validation.constraint;

import com.xy.validator.bean.validation.PersonNamePrefixConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PersonNamePrefixConstraintValidator.class})
public @interface PersonNamePrefix {

    String message() default "{person.name.prefix.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String prefix() default "test-";
}
