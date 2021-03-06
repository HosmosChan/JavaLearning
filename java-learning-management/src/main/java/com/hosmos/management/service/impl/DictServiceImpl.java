package com.hosmos.management.service.impl;

import com.hosmos.management.dao.DictDao;
import com.hosmos.management.model.Dict;
import com.hosmos.management.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 字典管理实现层
 *
 * @author chenhuayang
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Override
    public Dict getByTypeAndK(String type, String k) {
        return dictDao.getByTypeAndK(type, k);
    }

    @Override
    public void save(Dict dict) {
        dictDao.save(dict);
    }

    @Override
    public Dict getById(Long id) {
        return dictDao.getById(id);
    }

    @Override
    public void update(Dict dict) {
        dictDao.update(dict);
    }

    @Override
    public List<Dict> list(Map<String, Object> params, Integer offset, Integer limit) {
        return dictDao.list(params, offset, limit);
    }

    @Override
    public int count(Map<String, Object> params) {
        return dictDao.count(params);
    }

    @Override
    public void delete(Long id) {
        dictDao.delete(id);
    }

    @Override
    public List<Dict> listByType(String type) {
        return dictDao.listByType(type);
    }
}