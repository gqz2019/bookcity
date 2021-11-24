package com.gqz.bookcity.controller;

import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>图书的控制类</p>
 *
 * @author gqz20
 * @create 2021-11-08 10:22
 **/
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    @PostMapping("add")
    public Result addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new Result().success("添加图书成功");
    }

    /**
     * 分页查询所有
     *
     * @return
     */
    @GetMapping("findAll/{pageNum}/{pageSize}")
    public Result<PageInfo<Book>> findAll(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Book> books = bookService.findAll(pageNum, pageSize);
        return new Result<PageInfo<Book>>().success("查找图书成功", books);

    }

    /**
     * 修改图示
     *
     * @param book
     * @return
     */
    @PutMapping("update")
    public Result updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new Result().success("修改图书成功");
    }

    @DeleteMapping("delete")
    public Result deleteBook(@RequestBody Book book) {
        bookService.deleteBook(book);
        return new Result().success("delete book success");
    }

    @GetMapping("getCount")
    public Result findCount(){
        int count= bookService.findCount();
        return new Result().success("查询数量成功",count);
    }
}