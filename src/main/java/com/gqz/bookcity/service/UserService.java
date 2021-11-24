package com.gqz.bookcity.service;


import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.po.User;

import java.util.Map;

public interface UserService {
    void add(User user);

    PageInfo getUserList(Integer pageNumber,Integer pageSize);

    void deleteUserById(Integer id);

    User getUserById(Integer id);

    void update(User user);

    Integer getListCount();

    Boolean findUserByUsername(String username);

    User signIn(User user);

    Map<String, Object> findUserRoles(String username);
}
