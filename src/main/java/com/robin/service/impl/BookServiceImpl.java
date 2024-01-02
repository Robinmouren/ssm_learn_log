package com.robin.service.impl;

import com.robin.controller.Code;
import com.robin.dao.BookDao;
import com.robin.domain.Book;
import com.robin.exception.BusinessException;
import com.robin.exception.SystemException;
import com.robin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public boolean save(Book book) {
        return bookDao.save(book) > 0;

    }

    public boolean update(Book book) {
        return bookDao.update(book) > 0;

    }

    public boolean delete(Integer id) {
        return bookDao.delete(id) > 0;

    }

    public Book getById(Integer id) {
        if(id <= 0){
            throw new BusinessException(Code.PROJECT_LOW_ZERO_ERR,"输入的id不能小于1，请输入正确的id");
        }else if(id > 100){
            throw new BusinessException(Code.PROJECT_TOO_HIGH_ERR,"输入的id不能大于100，请输入正确的id");
        }


        /*try {
            int i = 1/0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器访问超时，请重试",e);
        }*/


        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
