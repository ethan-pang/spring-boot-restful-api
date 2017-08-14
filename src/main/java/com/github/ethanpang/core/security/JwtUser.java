package com.github.ethanpang.core.security;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by ethan on 2017/8/3.
 */
public class JwtUser implements UserDetails {
    private final Integer id;
    private final String username;
    private final String password;
    private final boolean isAccountLocked;

    public JwtUser(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities,Boolean isAccountLocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountLocked=isAccountLocked;
    }

    private final Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JSONField(serialize = false)
    public Integer getId() {
        return id;
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
