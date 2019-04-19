package com.hosmos.learning.common.utils.service;

import java.util.List;
import java.util.Map;

public interface BaseDaoMapper<T> {
    int add(T t);

    T get(Map<String, Object> map);

    List<T> selectList(Map<String, Object> map);

    int update(T t);
}
