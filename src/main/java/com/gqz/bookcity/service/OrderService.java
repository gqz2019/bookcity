package com.gqz.bookcity.service;

import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.po.Order;

/**
 * <p>订单接口</p>
 *
 * @author gqz20
 * @create 2021-11-23 21:40
 **/
public interface OrderService {

    void addCart(Order order);

    void deleteCart(Order order);

    Book getCart(Integer id);

    void deleteAllCart();

    Order modifyCart(Integer count, Integer id);
}
