package pcShop.service;

import pcShop.model.Product;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> findAll();
    void save(T t);
    Product delete(E id);
    T findById(E id);
}
