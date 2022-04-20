package dao;

import exception.BaseException;
import exception.NoUserException;

import java.util.List;

@SuppressWarnings("all")
public interface BaseDao<T> {

    T insert(T object) throws BaseException;

    T update(T object) throws BaseException;

    T delete(T object) throws BaseException;

    T findByName(T object) throws BaseException;

    List<T> findAllOnes() throws BaseException;


}
