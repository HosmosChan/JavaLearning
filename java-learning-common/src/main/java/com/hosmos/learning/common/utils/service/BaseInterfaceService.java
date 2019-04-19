package com.hosmos.learning.common.utils.service;

import com.hosmos.learning.common.utils.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface BaseInterfaceService<T> {
    int add(T t) throws ServiceException;

    T get(Map<String, Object> map) throws ServiceException;

    List<T> selectList(Map<String, Object> map) throws ServiceException;

    int update(T t) throws ServiceException;
}
