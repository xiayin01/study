package com.xy.validator.controller;

import com.xy.validator.domain.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidatorController {

    @PostMapping("save/person")
    public String savePerson(@Valid @RequestBody Person person) {
        return "success";
    }
}
