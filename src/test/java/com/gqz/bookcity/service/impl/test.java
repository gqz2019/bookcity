package com.gqz.bookcity.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-12-06 20:01
 **/
public class test {
    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("2580000000000055545454545");
        System.out.println(encode);
    }
}
