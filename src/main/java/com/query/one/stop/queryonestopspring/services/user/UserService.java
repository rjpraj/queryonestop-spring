package com.query.one.stop.queryonestopspring.services.user;

import com.query.one.stop.queryonestopspring.dto.SignUpDto;
import com.query.one.stop.queryonestopspring.dto.UserDTO;

public interface UserService {
    UserDTO createUser(SignUpDto signUpDto);

    boolean hasUserWithEmail(String email);
}
