package com.github.ethanpang.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ethan on 2017/8/1.
 */
@Table(name="sys_role")
public class SysRole {
    @Id
    @GeneratedValue
    @JSONField(serialize = false)
    private Integer id;
    private String role;
    @JSONField(serialize = false)
    private String descrition;
    @JSONField(serialize = false)
    private Boolean available=Boolean.FALSE;
    @JSONField(serialize = false)
    private List<SysPermission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

}
