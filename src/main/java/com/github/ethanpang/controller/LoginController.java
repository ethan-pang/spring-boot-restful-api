package com.github.ethanpang.controller;

import com.github.ethanpang.config.datasource.DataSourceType;
import com.github.ethanpang.core.Result;
import com.github.ethanpang.core.ResultGenerator;
import com.github.ethanpang.model.UserToken;
import com.github.ethanpang.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/25.
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping
    @ApiOperation(value = "登陆")
    @DataSourceType("readonly")
    @ResponseBody
    public Result login( String username,  String password){
        String token=loginService.login(username,password);
        if(token!=null){
            UserToken userToken=new UserToken(token);
            return ResultGenerator.genSuccessResult(userToken);
        }else {
            return ResultGenerator.genFailResult("用户名或密码错误");
        }
    }
}
