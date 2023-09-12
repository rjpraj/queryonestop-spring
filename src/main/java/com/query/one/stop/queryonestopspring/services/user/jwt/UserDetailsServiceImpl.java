package com.query.one.stop.queryonestopspring.services.user.jwt;

import com.query.one.stop.queryonestopspring.entity.User;
import com.query.one.stop.queryonestopspring.repository.UserRepository;
import com.query.one.stop.queryonestopspring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // interface from spring boot itself
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // write the logic to get user from db
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("user Not Found");
        return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),userOptional.get().getPassword(),new ArrayList<>());
    }
}
