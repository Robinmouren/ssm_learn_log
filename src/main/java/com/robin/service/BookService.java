package com.robin.service;

import com.robin.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService {


    /**
     * 保存
     * @param book
     */
    public boolean save(Book book);

    /**
     * 修改
     * @param book
     */
    public boolean update(Book book);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 获取全部
     * @return
     */
    public List<Book> getAll();

}
