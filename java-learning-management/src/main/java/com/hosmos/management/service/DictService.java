package com.hosmos.management.service;

import com.hosmos.management.model.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典管理业务层
 *
 * @author chenhuayang
 */
public interface DictService {
    /**
     * 通过字典type和key获取字典包含value值的整条字典信息
     *
     * @param type 字典类型
     * @param k    字典对应类型的key值，同一个类型的每个key值各不相同
     * @return Dict 一条字典信息
     */
    Dict getByTypeAndK(String type, String k);

    /**
     * 保存字典信息
     *
     * @param dict 字典信息
     */
    void save(Dict dict);

    /**
     * 通过字典id获取字典信息
     *
     * @param id 字典id
     * @return Dict 一条字典信息
     */
    Dict getById(Long id);

    /**
     * 更新字典信息
     *
     * @param dict 字典信息
     */
    void update(Dict dict);

    /**
     * 通过搜索参数获取字典信息列表
     *
     * @param params 搜索参数(包含字典类型(type = "?")以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<Dict> 字典信息列表
     */
    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过搜索参数获取字典信息列表个数，用于list方法中进行页码计数
     *
     * @param params 搜索参数(包含字典类型(type = "?")以及排列顺序(orderBy = order by ?))
     * @return int 字典信息列表个数
     */
    int count(Map<String, Object> params);

    /**
     * 通过字典id删除字典信息
     *
     * @param id 字典id
     */
    void delete(Long id);

    /**
     * 通过字典类型获取字典信息列表
     *
     * @param type 字典类型
     * @return List<Dict> 字典信息列表
     */
    List<Dict> listByType(String type);
}