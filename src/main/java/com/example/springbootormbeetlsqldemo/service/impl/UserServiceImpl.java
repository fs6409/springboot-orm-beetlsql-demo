package com.example.springbootormbeetlsqldemo.service.impl;

import com.example.springbootormbeetlsqldemo.dao.UserDao;
import com.example.springbootormbeetlsqldemo.pojo.User;
import com.example.springbootormbeetlsqldemo.service.UserService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        userDao.insert(user, true);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户id不能为null");
        }
        userDao.updateTemplateById(user);
        return userDao.single(user.getId());
    }

    @Override
    public User getUser(Long id) {
        return userDao.single(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.all();
    }

    @Override
    public PageQuery<User> getUserByPage(Integer currentPage, Integer pageSize) {
        return userDao.createLambdaQuery().page(currentPage, pageSize);
    }
}
