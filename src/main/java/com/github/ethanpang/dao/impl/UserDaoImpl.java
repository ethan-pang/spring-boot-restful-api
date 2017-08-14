package com.github.ethanpang.dao.impl;

import com.github.ethanpang.dao.UserDao;
import com.github.ethanpang.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ethan on 2017/8/2.
 */
@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public User findUserByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        User user = getSqlSession().selectOne("UserDao.findUserByUsername", params);
        return user;
    }

    @Override
    public Integer register(User user) {
        return getSqlSession().insert("UserDao.insert",user);
    }

    @Override
    public List<User> listUser() {
        return getSqlSession().selectList("UserDao.listUser",null);
    }
}
