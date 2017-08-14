package com.github.ethanpang.core.security;

import com.github.ethanpang.model.User;
import com.github.ethanpang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ethan on 2017/8/3.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userService.findUserByUsename(s);
        if (user==null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.",s));
        }else {
            return JwtUserFactory.create(user);
        }
    }
}
