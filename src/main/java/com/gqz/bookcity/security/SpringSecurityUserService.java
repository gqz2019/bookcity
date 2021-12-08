package com.gqz.bookcity.security;

import com.gqz.bookcity.dao.RoleMapper;
import com.gqz.bookcity.dao.UserMapper;
import com.gqz.bookcity.dao.UserRoleMapper;
import com.gqz.bookcity.pojo.Role;
import com.gqz.bookcity.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>spring security 登录配置</p>
 *
 * @author gqz20
 * @create 2021-11-20 11:45
 **/
@Service
public class SpringSecurityUserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || s.isEmpty()) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        com.gqz.bookcity.pojo.User param = new com.gqz.bookcity.pojo.User();
        param.setUsername(s);
        com.gqz.bookcity.pojo.User user = userMapper.selectOne(param);

        if (user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoles = userRoleMapper.select(userRole);

        if (userRoles != null && !userRoles.isEmpty()) {
            List<Integer> roleIds = userRoles.stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toList());

            Example example = new Example(Role.class);
            example.createCriteria().andIn("id", roleIds);
            List<Role> roles = roleMapper.selectByExample(example);
            user.setRoleList(roles);
        }
        return user;
    }
}
