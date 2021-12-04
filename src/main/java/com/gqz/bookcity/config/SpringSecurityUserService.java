package com.gqz.bookcity.config;

import com.gqz.bookcity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>spring security 登录配置</p>
 *
 * @author gqz20
 * @create 2021-11-20 11:45
 **/
public class SpringSecurityUserService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> dbUser = userService.findUserRoles(s);
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (dbUser != null) {

            List roles = (List) dbUser.get("keyword");
            for (Object role : roles) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority((String) role);
                grantedAuthorities.add(simpleGrantedAuthority);

            }
            return new User(String.valueOf(dbUser.get("username")), (String) dbUser.get("password"), grantedAuthorities);
        }
        return null;
    }
}
