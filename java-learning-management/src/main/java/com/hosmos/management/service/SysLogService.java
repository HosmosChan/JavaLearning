package com.hosmos.management.service;

import com.hosmos.management.model.SysLogs;

import java.util.List;
import java.util.Map;

/**
 * 日志service
 *
 * @author chenhuayang
 */
public interface SysLogService {
    /**
     * 通过日志信息保存日志
     *
     * @param sysLogs 日志信息
     */
    void save(SysLogs sysLogs);

    /**
     * 退出时保存日志
     *
     * @param userId 用户id
     * @param module 退出类型
     * @param flag   保存日志成功与否
     * @param remark 重复操作标志
     */
    void save(Long userId, String module, Boolean flag, String remark);

    /**
     * 删除{}之前日志{}条
     */
    void deleteLogs();

    /**
     * 通过搜索参数获取日志信息列表个数，用于list方法中进行页码计数
     *
     * @param params 搜索参数(包含角色昵称(nickName = "?")、开始时间(beginTime = "?")、结束时间(endTime = "?")、状态(flag = ?)以及排列顺序(orderBy = order by ?))
     * @return int 日志信息列表个数
     */
    int count(Map<String, Object> params);

    /**
     * 通过搜索参数获取日志信息列表
     *
     * @param params 搜索参数(包含角色昵称(nickName = "?")、开始时间(beginTime = "?")、结束时间(endTime = "?")、状态(flag = ?)以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<SysLogs> 日志信息列表
     */
    List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit);
}