package com.gqz.bookcity.dao;

import com.gqz.bookcity.po.Book;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 图书的dao类
 * @author gqz20
 */
@Repository
public interface BookMapper extends Mapper<Book> {
}