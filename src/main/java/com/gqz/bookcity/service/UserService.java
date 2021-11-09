package com.gqz.bookcity.service;


import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.po.User;

public interface UserService {
    void add(User user);

    PageInfo getUserList(Integer pageNumber,Integer pageSize);

    void deleteUserById(Integer id);

    User getUserById(Integer id);

    void update(User user);

    Integer getListCount();

    Boolean findUserByUsername(String username);

    Boolean signIn(User user);
}
