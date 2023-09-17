package com.query.one.stop.queryonestopspring.services.user;

import com.query.one.stop.queryonestopspring.dto.SignUpDto;
import com.query.one.stop.queryonestopspring.dto.UserDTO;
import com.query.one.stop.queryonestopspring.entity.User;
import com.query.one.stop.queryonestopspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignUpDto signUpDto) {
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpDto.getPassword()));
        // a lot of methods from jpa by using repository annotation
        userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        return userDTO;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
