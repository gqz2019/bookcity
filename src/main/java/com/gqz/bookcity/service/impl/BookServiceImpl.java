package com.gqz.bookcity.service.impl;

import com.gqz.bookcity.dao.BookMapper;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-08 10:21
 **/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBook(Book book) {
        int i = bookMapper.insert(book);
        if (i==0){
            throw new RuntimeException("添加图书失败");
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookMapper.selectAll();
        if (books.isEmpty()){
            throw new RuntimeException("查询所有图书失败");
        }
        return books;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(Book book) {
        int i = bookMapper.updateByPrimaryKeySelective(book);
        if (i==0) {
            throw new RuntimeException("修改图书失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(Book book) {
        if (book.getId()==null) {
            throw new RuntimeException("empty id error");
        }
        int i = bookMapper.delete(book);
        if (i==0) {
            throw new RuntimeException("delete book failure");
        }
    }
}
