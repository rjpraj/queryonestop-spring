package com.query.one.stop.queryonestopspring.dto;

import lombok.Data;

@Data
public class SignUpDto { // this dto is used to capture details from request param of api call

    private Integer id;

    private String name;

    private String email;
    private String password;

}
