package com.xy.user.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public class UserDto implements Serializable {

    private Long id;
    private String userName;

}
