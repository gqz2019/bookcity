package com.gqz.bookcity.service.impl;

import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.po.Order;
import com.gqz.bookcity.service.OrderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-23 21:41
 **/
@Service
public class OrderServiceImpl implements OrderService {


    @Override
    @CachePut(cacheNames = "books", key = "#order.bid")
    public void addCart(Order order) {

    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#order.bid")
    public void deleteCart(Order order) {

    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id")
    public Book getCart(Integer id) {
        return new Book();
    }

    @Override
    @CacheEvict(cacheNames = "books", allEntries = true)
    public void deleteAllCart() {
    }

    @Override
    @Caching(cacheable = {@Cacheable(cacheNames = "books", key = "#id")},
            put = {@CachePut(value = "books", key = "#id")})
    public Order modifyCart(Integer count, Integer id) {
        Order order = new Order();
        order.setCount(count);
        return order;
    }
}

