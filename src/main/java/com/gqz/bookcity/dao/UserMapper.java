package com.gqz.bookcity.dao;

import com.gqz.bookcity.po.User;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-01 11:01
 **/
@Repository
public interface UserMapper extends Mapper<User> {
    @MapKey("u.id")
    Map<String, Object> findUserRoles(String username);
}
