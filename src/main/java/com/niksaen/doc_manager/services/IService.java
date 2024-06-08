package com.niksaen.doc_manager.services;

public interface IService<T> {
    T get(int id);
    void update(T value);
    void delete(T value);
}
