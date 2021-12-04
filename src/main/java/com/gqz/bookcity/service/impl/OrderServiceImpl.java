package com.gqz.bookcity.service.impl;

import com.gqz.bookcity.po.Order;
import com.gqz.bookcity.service.OrderService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-23 21:41
 **/
@Service
@CacheConfig(cacheNames = "shopCart")
public class OrderServiceImpl implements OrderService {


    @Override
    @CachePut(key = "#order.bid")
    public Order addCart(Order order) {
        return order;
    }

    @Override
    @CacheEvict(key = "#order.bid")
    public void deleteCart(Order order) {

    }

    @Override
    @Cacheable(key = "#id")
    public Order getCart(Integer id) {
        Order o = new Order();
        o.setBid(id);
        return o;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void deleteAllCart() {
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "#id")},
            put = {@CachePut(key = "#id")})
    public Order modifyCart(Integer count, Integer id) {
        Order order = new Order();
        order.setCount(count);
        return order;
    }
}

