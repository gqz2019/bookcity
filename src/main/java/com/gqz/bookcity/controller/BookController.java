package com.gqz.bookcity.controller;

import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param book
     * @return
     */
    @PostMapping("add")
    public Result addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new Result().success("添加图书成功");
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("findAll")
    public Result<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return new Result<List<Book>>().success("添加图书成功",books);
    }

    /**
     * 修改图示
     * @param book
     * @return
     */
    @PutMapping("update")
    public Result updateBook(@RequestBody Book book){
        bookService.updateBook(book);
        return new Result().success("修改图书成功");
    }

    @DeleteMapping("delete")
    public Result deleteBook(@RequestBody Book book){
        bookService.deleteBook(book);
        return new Result().success("delete book success");
    }

}
