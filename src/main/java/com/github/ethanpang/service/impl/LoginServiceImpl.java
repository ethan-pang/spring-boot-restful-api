package com.github.ethanpang.service.impl;

import com.github.ethanpang.core.ServiceException;
import com.github.ethanpang.core.security.JwtTokenUtil;
import com.github.ethanpang.dao.UserDao;
import com.github.ethanpang.model.User;
import com.github.ethanpang.service.LoginService;
import com.github.ethanpang.util.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional
public class LoginServiceImpl  implements LoginService {

    private RedisUtil redisUtil;
    private UserDao userDao;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    public LoginServiceImpl(RedisUtil redisUtil, UserDao userDao, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.redisUtil = redisUtil;
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public String verifyLogin(String username,String requestPassword){
        User user= userDao.findUserByUsername(username);
        String password=requestPassword+user.getGmtCreate()+"jclive";
        if(user.getPassword().equals(DigestUtils.sha1Hex(password))){
            String date=new Date().toString();
            String token=DigestUtils.sha1Hex(username+date);
            redisUtil.psetex("cps_token"+token,3600000,user.getId().toString());
            return token;
        }else {
            return null;
        }
    }



    @Override
    public String login(String username, String password) throws ServiceException {
        try {
            UsernamePasswordAuthenticationToken upToken =new UsernamePasswordAuthenticationToken(username,password);
            final Authentication authentication =authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            redisUtil.psetex(token,3600000,username);
            return token;
        }catch (Exception e){
            throw new ServiceException("账号或密码错误");
        }

    }
}
