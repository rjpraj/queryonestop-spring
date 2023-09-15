package com.query.one.stop.queryonestopspring.dto;

import lombok.Data;

@Data
public class UserDTO { // this dto is the class we return to the person hitting save api
    private Integer id;
    private String name;
    private String email;
}
