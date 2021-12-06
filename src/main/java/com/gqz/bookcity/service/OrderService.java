package com.gqz.bookcity.service;

import com.gqz.bookcity.po.Order;

import java.util.List;

/**
 * <p>订单接口</p>
 *
 * @author gqz20
 * @create 2021 -11-23 21:40
 */
public interface OrderService {

    /**
     * Add cart order.
     *
     * @param order the order
     * @return the order
     */
    Order addCart(Order order);

    /**
     * Delete cart.
     *
     * @param bid the bid
     */
    void deleteCart(Integer bid);

    /**
     * Gets cart.
     *
     * @param id the id
     * @return the cart
     */
    Order getCart(Integer id);

    /**
     * Delete all cart.
     */
    void deleteAllCart();

    /**
     * Modify cart order.
     *
     * @param count the count
     * @param id    the id
     * @return the order
     */
    Order modifyCart(Integer count, Integer id);

    /**
     * Gets all orders.
     *
     * @param uid the uid
     * @return the all orders
     */
    List getAllOrders(Integer uid);
}
