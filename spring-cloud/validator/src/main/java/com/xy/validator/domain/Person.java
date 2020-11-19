package com.xy.validator.domain;

import com.xy.validator.bean.validation.constraint.PersonNamePrefix;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ToString
public class Person implements Serializable {

    @NotBlank
    @PersonNamePrefix(prefix = "xy")
    private String name;

    @Max(value = 100)
    private Integer age;


}
