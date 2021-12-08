package com.gqz.bookcity.security;

import com.alibaba.fastjson.JSON;
import com.gqz.bookcity.entity.Result;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@AllArgsConstructor
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

//    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().write(objectMapper.writeValueAsString(authentication));
        response.getWriter().write(JSON.toJSONString(new Result<>().success("登录成功",authentication)));
    }

}
