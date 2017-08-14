package com.github.ethanpang.service;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface LoginService {
    String verifyLogin(String username,String password);
    String login(String username,String password);
}
