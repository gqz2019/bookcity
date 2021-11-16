package com.gqz.bookcity.service.impl;

import com.gqz.bookcity.dao.BookMapper;
import com.gqz.bookcity.po.Book;
import com.gqz.bookcity.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-13 09:56
 **/
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 保存图片的路径
     *
     * @param s
     */
    @Override
    public void save(String s, Integer id) {
        Book book = new Book();
        book.setImgPath(s);
        book.setId(id);
        Book saveBook = bookMapper.selectOne(book);
        if (saveBook != null && saveBook.getImgPath().equals(s)) {
            throw new RuntimeException("图片已上传");
        } else if (saveBook != null && !saveBook.getImgPath().equals(s)) {
            int i = bookMapper.updateByPrimaryKeySelective(book);
            if (i == 0) {
                throw new RuntimeException("图片修改失败");
            }
        } else if (saveBook != null && saveBook.getImgPath() == null) {
            int i = bookMapper.updateByPrimaryKeySelective(book);
            if (i == 0) {
                throw new RuntimeException("图片添加失败");
            }
        }else if (saveBook==null){
            int i = bookMapper.insert(book);
        }

    }
}
