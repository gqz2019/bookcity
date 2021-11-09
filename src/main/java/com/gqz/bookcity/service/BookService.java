package com.gqz.bookcity.service;

import com.gqz.bookcity.po.Book;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-08 10:20
 **/
public interface BookService {
    void addBook(Book book);

    List<Book> findAll();

    void updateBook(Book book);

    void deleteBook(Book book);
}
