package com.xy.validator.bean.validation;

import com.xy.validator.bean.validation.constraint.PersonNamePrefix;
import com.xy.validator.domain.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link ConstraintValidator} {@link Person}
 */
public class PersonNamePrefixConstraintValidator implements ConstraintValidator<PersonNamePrefix, String> {

    private String prefix;

    @Override
    public void initialize(PersonNamePrefix constraintAnnotation) {
        this.prefix = constraintAnnotation.prefix();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (!name.startsWith(prefix)) {
            //自定义返回信息
            context.disableDefaultConstraintViolation();
            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("人的姓名必须以\"" + prefix + "\"起始");
            builder.addConstraintViolation();
            return false;
        }
        return true;
    }
}
