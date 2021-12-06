package com.gqz.bookcity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.po.Order;
import com.gqz.bookcity.service.BookService;
import com.gqz.bookcity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021 -11-23 21:41
 */
@Service
@CacheConfig(cacheNames = "shopCart")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BookService bookService;

    @Override
    @CachePut(key = "#order.bid")
    public Order addCart(Order order) {
        return order;
    }

    @Override
    @CacheEvict(key = "#bid")
    public void deleteCart(Integer bid) {

    }

    @Override
    @Cacheable(key = "#id")
    public Order getCart(Integer id) {
        Order o = new Order();
        o.setBid(id);
        return o;
    }

    @Override
    public List getAllOrders(Integer uid) {
        Set keys = redisTemplate.keys("shopCart*");
        List<String> list = redisTemplate.opsForValue().multiGet(keys);
        List<Map<String, Object>> maps = new ArrayList<>();
        if (list != null) {

            List<Order> collect = list
                    .stream()
                    .map(s -> JSONObject.parseObject(s, Order.class))
                    .filter(a -> {
                        if (Objects.equals(a.getUid(), uid)) {
                            return true;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
            for (Order order : collect) {
                Book bookById = bookService.findBookById(order.getBid());
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", bookById.getName());
                map.put("count", order.getCount());
                map.put("price", bookById.getPrice());
                map.put("totalAmount", order.getTotalprice());
                map.put("bid",bookById.getId());
                maps.add(map);
            }
            return maps;
        }else {
            throw new RuntimeException("查询用户id为"+uid+"的订单失败");
        }
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

