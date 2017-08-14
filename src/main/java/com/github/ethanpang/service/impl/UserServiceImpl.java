package com.github.ethanpang.service.impl;

import com.github.ethanpang.core.AbstractService;
import com.github.ethanpang.core.ServiceException;
import com.github.ethanpang.dao.UserDao;
import com.github.ethanpang.dao.UserMapper;
import com.github.ethanpang.model.User;
import com.github.ethanpang.service.UserService;
import com.github.ethanpang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/26.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    private UserDao userDao;
    private  UserMapper userMapper;
    RedisUtil redisUtil;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper, RedisUtil redisUtil) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }


    @Override
    public User getUserByToken(String token) {
        String username= redisUtil.get(token);
        User user= userDao.findUserByUsername(username);
        return user;
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public boolean logout(String token) {
        if(redisUtil.exists(token)){
            redisUtil.del(token);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public User findUserByUsename(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Integer register(String username, String password) {
        if (userDao.findUserByUsername(username)!=null){
            throw(new ServiceException("用户已存在"));
        }
        User userToAdd=new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userToAdd.setUsername(username);
        userToAdd.setPassword(encoder.encode(password));
        userToAdd.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        userToAdd.setGmtModified(new Timestamp(System.currentTimeMillis()));
        userToAdd.setStatus(0);
        return userMapper.insert(userToAdd);
    }

    @Override
    public boolean changePassword(User user, String originalPassword, String newPassword) throws ServiceException{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(originalPassword,user.getPassword())){
            user.setPassword(encoder.encode(newPassword));
            userMapper.updateByPrimaryKey(user);
        }else {
            throw new ServiceException("原密码错误");
        }
        return true;
    }
}
