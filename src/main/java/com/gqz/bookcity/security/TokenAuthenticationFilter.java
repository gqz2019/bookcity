package com.gqz.bookcity.security;

import com.gqz.bookcity.dao.RoleMapper;
import com.gqz.bookcity.dao.TokenMapper;
import com.gqz.bookcity.dao.UserMapper;
import com.gqz.bookcity.dao.UserRoleMapper;
import com.gqz.bookcity.pojo.Role;
import com.gqz.bookcity.pojo.Token;
import com.gqz.bookcity.pojo.User;
import com.gqz.bookcity.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenMapper tokenRepository;
    @Autowired
    private UserMapper userRepository;
    @Autowired
    private RoleMapper roleRepository;
    @Autowired
    private UserRoleMapper userRoleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenStr = request.getHeader("token");
        if (tokenStr != null && !tokenStr.isEmpty()) {
            Token tokenDb = tokenRepository.selectOneByExample(Example
                    .builder(Token.class)
                    .where(Sqls.custom().andEqualTo("token", tokenStr))
                    .build());

            if (tokenDb != null && tokenDb.getUserId() != null) {
                User user = userRepository.selectByPrimaryKey(tokenDb.getUserId());
                if (user == null) {
                    throw new UsernameNotFoundException("token已失效");
                }
                List<UserRole> userRoles = userRoleRepository.selectByExample(Example.builder(UserRole.class)
                        .where(Sqls.custom().andEqualTo("userId", user.getId())).build());

                if (userRoles != null && !userRoles.isEmpty()) {
                    List<Integer> roleIds = userRoles.stream()
                            .map(UserRole::getRoleId)
                            .collect(Collectors.toList());
                    List<Role> roles = roleRepository.selectByExample(Example
                            .builder(Role.class)
                            .where(Sqls.custom().andIn("id", roleIds))
                            .build());
                    user.setRoleList(roles);
                }
                user.setToken(tokenStr);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated user %s, setting security context", user.getUsername()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
