package com.hosmos.management.service.impl;

import com.hosmos.management.dao.SysLogsDao;
import com.hosmos.management.model.SysLogs;
import com.hosmos.management.model.User;
import com.hosmos.management.service.SysLogService;
import com.hosmos.management.utils.UserUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl implements SysLogService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired(required = false)
    private SysLogsDao sysLogsDao;

    @Override
    public void save(SysLogs sysLogs) {
        User user = UserUtil.getCurrentUser();
        if (user == null || user.getId() == null) {
            return;
        }
        sysLogs.setUser(user);
        sysLogsDao.save(sysLogs);
    }

    @Async
    @Override
    public void save(Long userId, String module, Boolean flag, String remark) {
        SysLogs sysLogs = new SysLogs();
        sysLogs.setFlag(flag);
        sysLogs.setModule(module);
        sysLogs.setRemark(remark);
        User user = new User();
        user.setId(userId);
        sysLogs.setUser(user);
        sysLogsDao.save(sysLogs);

    }

    @Override
    public void deleteLogs() {
        Date date = DateUtils.addMonths(new Date(), -3);
        String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
        int n = sysLogsDao.deleteLogs(time);
        log.info("删除{}之前日志{}条", time, n);
    }

    @Override
    public int count(Map<String, Object> params) {
        return sysLogsDao.count(params);
    }

    @Override
    public List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit) {
        return sysLogsDao.list(params, offset, limit);
    }
}