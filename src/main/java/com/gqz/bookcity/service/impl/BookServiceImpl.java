package com.gqz.bookcity.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.dao.BookMapper;
import com.gqz.bookcity.pojo.Book;
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
        if (book.getImgPath() == null) {

            int i = bookMapper.insert(book);
            if (i == 0) {
                throw new RuntimeException("添加图书失败");
            }
        } else {
            Book book1 = new Book();
            book1.setImgPath(book.getImgPath());
            Book temp = bookMapper.selectOne(book1);
            temp.setName(book.getName());
            temp.setAuthor(book.getAuthor());
            temp.setPrice(book.getPrice());
            temp.setStock(book.getStock());
            int i = bookMapper.updateByPrimaryKeySelective(temp);
            if (i == 0) {
                throw new RuntimeException("添加图示失败");
            }
        }
    }

    @Override
    public PageInfo<Book> findAll(Integer pageNum, Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Book> books = bookMapper.selectAll();
        if (books.isEmpty()) {
            throw new RuntimeException("查询所有图书失败");
        }
        PageInfo<Book> info = new PageInfo<>();
        info.setList(books);
//        info.setPages(page.getPages());
        info.setTotal(page.getTotal());

        return info;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(Book book) {
        int i = bookMapper.updateByPrimaryKeySelective(book);
        if (i == 0) {
            throw new RuntimeException("修改图书失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(Book book) {
        if (book.getId() == null) {
            throw new RuntimeException("empty id error");
        }
        int i = bookMapper.delete(book);
        if (i == 0) {
            throw new RuntimeException("delete book failure");
        }
    }

    @Override
    public int findCount() {
        int count = bookMapper.selectCount(null);
        return count;
    }

    @Override
    public Book findBookById(Integer id) {
        Book temp = new Book();
        temp.setId(id);
        Book book = bookMapper.selectOne(temp);
        return book;
    }
}
