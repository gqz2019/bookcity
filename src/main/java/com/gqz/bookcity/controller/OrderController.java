package com.gqz.bookcity.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.po.Order;
import com.gqz.bookcity.po.User;
import com.gqz.bookcity.service.BookService;
import com.gqz.bookcity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-23 21:59
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private BookService bookService;

    private List<Integer> ids = new Vector<>();

    /**
     * Instantiates a new Order controller.
     *
     * @param orderService  the order service
     * @param redisTemplate the redis template
     */
    @Autowired
    public OrderController(OrderService orderService, RedisTemplate<String, Object> redisTemplate) {
        this.orderService = orderService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 添加到购物车
     *
     * @return 添加成功/失败
     */
    @PostMapping("/addCart")
    public Result addCart(@RequestBody Book book) {
        Object loginUser = redisTemplate.opsForValue().get("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("请先登录");
        }
        User login = JSON.parseObject((String) loginUser, User.class);

        Order order = new Order(null,
                null,
                book.getId(),
                1,
                book.getPrice().multiply(BigDecimal.valueOf(1)),
                LocalDate.now(),
                0,
                login.getId());
        orderService.addCart(order);
         ids.add(book.getId());
        return new Result().success("添加购物车成功");
    }

    /**
     * Modify cart result.
     *
     * @param count the count
     * @param id    the id
     * @return the result
     */
    @PutMapping("modifyCart")
    public Result modifyCart(Integer count, Integer id) {

        Order order = orderService.modifyCart(count, id);
        return new Result().success("更改购物车内商品数量成功");
    }

    /**
     * 删除购物车
     *
     * @return 添加成功/失败
     */
    @DeleteMapping("/deleteCart")
    public Result deleteCart(Integer bid) {
        orderService.deleteCart(bid);
        ids.remove(bid);
        return new Result().success("删除购物车成功");
    }

    /**
     * 删除购物车all
     *
     * @return 添加成功/失败
     */
    @DeleteMapping("/deleteAllCart")
    public Result deleteAllCart() {
        orderService.deleteAllCart();
        ids.clear();
        return new Result().success("删除all购物车成功");
    }

    /**
     * 购物车
     *
     * @return 添加成功/失败
     */
    @GetMapping("/getCart")
    public Result<List<Map<String, Object>>> getCart() {
        LinkedList<Map<String, Object>> shopCart = new LinkedList<>();
        ids.forEach(id -> {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Order order = orderService.getCart(id);
            Book bookById = bookService.findBookById(id);
            paramMap.put("name", bookById.getName());
            paramMap.put("count", order.getCount());
            paramMap.put("price", bookById.getPrice());
            paramMap.put("totalAmount", order.getTotalprice());
            paramMap.put("bid",bookById.getId());
            shopCart.add(paramMap);
        });

        if (shopCart.isEmpty()) {
            throw new RuntimeException("查找所有购物车失败");
        }

        return new Result<List<Map<String, Object>>>().success("获取购物车成功", shopCart);
    }

    /**
     * Get all cart result.
     *
     * @return the result
     */
    @GetMapping("getAllCart")
    public Result<Object> getAllCart(){
        User loginUser = JSONObject.parseObject(String.valueOf(redisTemplate.opsForValue().get("loginUser")), User.class);
        List orders = orderService.getAllOrders(loginUser.getId());
        return new Result<>().success("获取购物车成功",orders);
    }

}
