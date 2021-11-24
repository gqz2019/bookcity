package com.gqz.bookcity.controller;

import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.po.Order;
import com.gqz.bookcity.service.BookService;
import com.gqz.bookcity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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
    private BookService bookService;

    private List<Integer> ids = new Vector<>();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 添加到购物车
     *
     * @return 添加成功/失败
     */
    @PostMapping("/addCart")
    public Result addCart(@RequestBody Book book, HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("signInUser");

        if (uid == null) {
            throw new RuntimeException("请登录");
        }
        Order order = new Order(null,
                null,
                book.getId(),
                1, book.getPrice().multiply(BigDecimal.valueOf(1)),
                LocalDate.now(),
                0,
                103);

        orderService.addCart(order);
        ids.add(book.getId());
        return new Result().success("添加购物车成功");
    }

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
    public Result deleteCart(@RequestBody Order order) {
        orderService.deleteCart(order);
        ids.remove(order.getBid());
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
    public Result<List<Book>> getCart() {
        LinkedList<Book> shopCart = new LinkedList<>();
        ids.forEach(id -> {
            Book book = orderService.getCart(id);
            shopCart.add(book);
        });
        return new Result<List<Book>>().success("获取购物车成功", shopCart);
    }

}
