package com.example.springbootormbeetlsqldemo.dao;

import com.example.springbootormbeetlsqldemo.pojo.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
}
