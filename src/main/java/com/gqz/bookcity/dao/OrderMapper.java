package com.gqz.bookcity.dao;

import com.gqz.bookcity.po.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>订单mapper</p>
 *
 * @author gqz20
 * @create 2021-11-23 21:38
 **/
@Repository
public interface OrderMapper extends Mapper<Order> {

}
