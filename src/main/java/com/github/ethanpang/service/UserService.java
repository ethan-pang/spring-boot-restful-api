package com.github.ethanpang.service;
import com.github.ethanpang.model.User;
import com.github.ethanpang.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/26.
 */
public interface UserService extends Service<User> {
    User findUserByUsename(String username);
    Integer register(String username,String password);
    boolean changePassword(User user,String originalPassword,String newPassword);
    boolean logout(String token);
    User getUserByToken(String token);
    List<User> listUser();
}
