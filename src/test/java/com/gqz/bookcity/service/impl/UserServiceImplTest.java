package com.gqz.bookcity.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.gqz.bookcity.po.User;
import com.gqz.bookcity.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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


    /**
     * Before.
     *
     * @throws Exception the exception
     */
    @Before
    public void before() throws Exception {
    }

    /**
     * After.
     *
     * @throws Exception the exception
     */
    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(User user)
     *
     * @throws Exception the exception
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getUserList(Integer pageIndex, Integer pageSize)
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetUserList() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: deleteUserById(Integer id)
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteUserById() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getUserById(Integer id)
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetUserById() throws Exception {
//TODO: Test goes here...
    }

    @Autowired
    private DruidDataSource druidDataSource;

    /**
     * Method: update(User user)
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdate() throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(druidDataSource);
        UserDetails userDetails = manager.loadUserByUsername("gqz");
        System.out.println(userDetails.getAuthorities());

    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Find user roles.
     */
    @Test
    public void findUserRoles() {

//        String username = (String) redisTemplate.opsForValue().get("username");
//        redisTemplate.opsForValue().set("username","jffffffffffffffffjfjj");
        User user = new User();
        user.setId(1);
        user.setUsername("gqjjfjfjf");
        user.setPassword("oY7hgo17g3vxQ7w");
        user.setEmail("r.rumugt@uazxtw.int");
//        redisTemplate.boundValueOps("loginUser").set(JSON.toJSONString(user));
//        redisTemplate.boundHashOps("loginUser1").put(2,"贡青志是大大大");


        String s = redisTemplate.opsForValue().get("loginUser").toString();
        System.out.println(s);

    }

    /**
     * Url.
     *
     * @throws IOException the io exception
     */
    @Test
    public void url() throws IOException {
        String url= "https://baidu.com";

        URL net = new URL(url);
        URLConnection connection = net.openConnection();
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(10000);
        connection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

        User user = new User();

    }
}