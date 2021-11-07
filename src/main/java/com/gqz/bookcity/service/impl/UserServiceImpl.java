package com.gqz.bookcity.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.dao.UserMapper;
import com.gqz.bookcity.po.User;
import com.gqz.bookcity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
//        userMapper.add(user);
        int i = userMapper.insert(user);
    }

    @Override
    public PageInfo<User> getUserList(Integer pageIndex, Integer pageSize) {
//        return userMapper.getUserList(map);
        PageInfo<User> info = new PageInfo<>();
        Page<Object> page = PageHelper.startPage(pageIndex, pageSize);
        List<User> users = userMapper.selectAll();
        info.setList(users);
        info.setTotal(page.getTotal());
        info.setPages(page.getPages());
        return info;
    }

    @Override
    public void deleteUserById(Integer id) {
//        userMapper.deleteUserById(id);
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserById(Integer id) {
//        return userMapper.getUserById(id);
        return userMapper.selectByPrimaryKey(id);

    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Integer getListCount() {
        return userMapper.selectCount(new User());
    }
}
