package com.gqz.bookcity.dao;

import com.gqz.bookcity.pojo.UserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>用户与角色的关联</p>
 *
 * @author gqz20
 * @create 2021-12-06 17:32
 **/
@Repository
public interface UserRoleMapper extends Mapper<UserRole> {

}
