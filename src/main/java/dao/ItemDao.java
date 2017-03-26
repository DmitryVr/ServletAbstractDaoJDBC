package dao;

import java.util.List;

/**
 * standart CRUD
 */
interface ItemDao<T> {

    void create(T model);

    void delete(Integer id);

    void update(T model);

    T getById(Integer id);

    List<T> getAll();
}
