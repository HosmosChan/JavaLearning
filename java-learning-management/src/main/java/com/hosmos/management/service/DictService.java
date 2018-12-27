package com.hosmos.management.service;

import com.hosmos.management.model.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {
    Dict getByTypeAndK(String type, String k);

    void save(Dict dict);

    Dict getById(Long id);

    void update(Dict dict);

    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);

    void delete(Long id);

    List<Dict> listByType(String type);
}