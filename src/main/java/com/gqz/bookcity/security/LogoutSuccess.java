package com.gqz.bookcity.security;

import com.alibaba.fastjson.JSON;
import com.gqz.bookcity.dao.TokenMapper;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.pojo.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>退出登录成功处理器</p>
 *
 * @author gqz20
 * @create 2021-12-08 09:16
 **/
@Component
@Slf4j
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("token");
        if (token == null) {
            throw new RuntimeException("未登录，不可注销");
        }
        int i = tokenMapper.deleteByExample(Example
                .builder(Token.class)
                .where(Sqls.custom().andEqualTo("token", token))
                .build());
        if (i==0) {
            throw new RuntimeException("删除token失败");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(new Result<>().success("退出登录成功")));
    }
}
