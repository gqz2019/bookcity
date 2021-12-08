package com.gqz.bookcity.dao;

import com.gqz.bookcity.pojo.Token;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>鉴权</p>
 *
 * @author gqz20
 * @create 2021-12-06 17:31
 **/
@Repository
public interface TokenMapper extends Mapper<Token> {
}
