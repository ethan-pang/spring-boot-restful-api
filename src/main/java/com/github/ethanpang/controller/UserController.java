package com.github.ethanpang.controller;

import com.github.ethanpang.core.Result;
import com.github.ethanpang.core.ResultGenerator;
import com.github.ethanpang.model.SysRole;
import com.github.ethanpang.model.User;
import com.github.ethanpang.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeGenerator on 2017/07/26.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result detail(@PathVariable String username) {
        User user = userService.findUserByUsename(username);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody User user) {
        user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword=user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result update(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword=user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer limit,@RequestParam(required = false) String username) {
        if (username==null){
            PageHelper.startPage(page, limit);
            List<User> list = userService.listUser();
            PageInfo pageInfo = new PageInfo(list);
            return ResultGenerator.genSuccessResult(pageInfo);
        }else{
            PageHelper.startPage(page, limit);
            User user= userService.findUserByUsename(username);
            List<User> list=new ArrayList<>();
            list.add(user);
            return ResultGenerator.genSuccessResult(list);
        }

    }



    @GetMapping("/info")
    public Result userInfo(@RequestHeader("Authorization") String token) {
        List<String> roles = new ArrayList<>();
        User user = userService.getUserByToken(token);
        for (SysRole role : user.getRoles()) {
            roles.add(role.getRole());
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/logout")
    public Result logout(@RequestHeader(name = "Authorization") String token) {
        if (userService.logout(token)) {
            return ResultGenerator.genSuccessResult(null);
        } else {
            return ResultGenerator.genFailResult("登出失败");
        }
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('ADMIN')")
    public Result changePassword(@RequestHeader(name = "Authorization") String token, String originalPassword, String newPassword) {
        User user=userService.getUserByToken(token);
        if(userService.changePassword(user,originalPassword,newPassword)){
            return ResultGenerator.genSuccessResult(true);
        }else {
            return ResultGenerator.genSuccessResult(false);
        }

    }

}
