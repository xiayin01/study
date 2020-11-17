package com.xy.validator;

import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

public class ApacheCommonValidatorMain {

    public static void main(String[] args) {
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        Integer value = integerValidator.validate("111");
        System.out.println(value);
        value = integerValidator.validate("aaa");
        System.out.println(value);
        System.out.println(integerValidator.format(100000, Locale.ENGLISH));
    }
}
