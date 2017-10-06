package model.dao.interfaces;

import java.util.*;

public interface GenericDao<E> {
    Optional<E> find(long id);
    void delete(long id);
    long add(E e);
    long update(E e);
	List<E> findAll();
}
