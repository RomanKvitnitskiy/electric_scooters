package com.kvitnytskyi.electric_scooters.dao;

import com.kvitnytskyi.electric_scooters.model.user.User;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    void update(T t, String[] params);

    T get(long id);

    void delete(T t);

    List<T> getAll();
}
