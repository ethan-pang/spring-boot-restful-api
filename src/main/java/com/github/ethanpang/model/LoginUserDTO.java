package com.github.ethanpang.model;

import java.util.List;

/**
 * Created by peng on 2017/7/26.
 */
public class LoginUserDTO {
    private String name;
    private List<String> role;
    private String avatar;

    public LoginUserDTO(String name, List<String> role, String avatar) {
        this.name = name;
        this.role = role;
        this.avatar = avatar;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
