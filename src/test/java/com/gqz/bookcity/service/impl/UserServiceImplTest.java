package com.gqz.bookcity.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.gqz.bookcity.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/20/2021</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(User user)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getUserList(Integer pageIndex, Integer pageSize)
     */
    @Test
    public void testGetUserList() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: deleteUserById(Integer id)
     */
    @Test
    public void testDeleteUserById() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getUserById(Integer id)
     */
    @Test
    public void testGetUserById() throws Exception {
//TODO: Test goes here...
    }
    @Autowired
    private DruidDataSource druidDataSource;
    /**
     * Method: update(User user)
     */
    @Test
    public void testUpdate() throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(druidDataSource);
        UserDetails userDetails = manager.loadUserByUsername("gqz");
        System.out.println(userDetails.getAuthorities());

    }

    @Test
    public void findUserRoles() {

    }
}