package com.github.ethanpang.core.security;

import com.github.ethanpang.model.SysRole;
import com.github.ethanpang.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ethan on 2017/8/3.
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        Integer state = user.getStatus();
        boolean isUserLocked = false;
        if (state == 1) {
            isUserLocked = true;
        }
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), mapToGrantedAuthorities(user.getRoles()), isUserLocked);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<SysRole> roles) {

        List<String> authorities = new ArrayList<>();
        for (SysRole role : roles) {
            authorities.add(role.getRole());
        }
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
