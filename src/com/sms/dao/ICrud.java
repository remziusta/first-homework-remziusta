package com.sms.dao;


import java.util.List;

public interface ICrud<T> {
    List<T> getAllData();
    T findById(long id);
    void create(T item);
    T update(T item);
    void delete(Long id);
}
