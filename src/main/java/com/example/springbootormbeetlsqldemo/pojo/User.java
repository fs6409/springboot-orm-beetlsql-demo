package com.example.springbootormbeetlsqldemo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id ;
    private Integer age ;
    private String name ;
    private Date createDate ;

}
