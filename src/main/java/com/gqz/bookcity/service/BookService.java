package com.gqz.bookcity.service;

import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.po.Book;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-08 10:20
 **/
public interface BookService {
    void addBook(Book book);

    PageInfo<Book> findAll(Integer pageNum, Integer pageSize);

    void updateBook(Book book);

    void deleteBook(Book book);

    int findCount();

    Book findBookById(Integer id);
}
