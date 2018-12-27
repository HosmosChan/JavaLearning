package com.hosmos.management.service;

import com.hosmos.management.model.SysLogs;

import java.util.List;
import java.util.Map;

/**
 * 日志service
 */
public interface SysLogService {
    void save(SysLogs sysLogs);

    void save(Long userId, String module, Boolean flag, String remark);

    void deleteLogs();

    int count(Map<String, Object> params);

    List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit);
}