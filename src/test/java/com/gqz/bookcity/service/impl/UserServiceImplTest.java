package com.gqz.bookcity.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.gqz.bookcity.pojo.User;
import com.gqz.bookcity.service.OrderService;
import com.gqz.bookcity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/20/2021</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
@Slf4j
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;


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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getDetails();
        log.info(principal.toString());

    }

    @Test
    public void aVoid(){
        System.out.println("=====================");
        orderService.getAllOrders(103).forEach(System.out::println);
    }
}