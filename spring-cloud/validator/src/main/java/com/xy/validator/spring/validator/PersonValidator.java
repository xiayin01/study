package com.xy.validator.spring.validator;

import com.xy.validator.domain.Person;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * {@link Person} {@link Validator}
 */
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = Person.class.cast(target);
        String name = person.getName();
        if (!StringUtils.isNotBlank(name)) {
            errors.reject("person.name.not.null", "人的名字不能为空");
        }
    }
}
