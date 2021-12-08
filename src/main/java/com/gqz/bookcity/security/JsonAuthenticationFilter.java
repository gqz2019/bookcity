package com.gqz.bookcity.security;

import com.alibaba.fastjson.JSONObject;
import com.gqz.bookcity.dao.TokenMapper;
import com.gqz.bookcity.pojo.Token;
import com.gqz.bookcity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenMapper tokenRepository;

    public JsonAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        try {
            User user = JSONObject.parseObject(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword());
            //调用父类方法
            Authentication authenticate = getAuthenticationManager().authenticate(token);
            if (authenticate.isAuthenticated()) {
                User loginUser = (User) authenticate.getPrincipal();
                Token loginToken = new Token();
                loginToken.setToken(UUID.randomUUID().toString());
                loginToken.setUserId(Long.valueOf(loginUser.getId()));
                tokenRepository.insert(loginToken);
                user.setToken(loginToken.getToken());


            }
            return authenticate;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
