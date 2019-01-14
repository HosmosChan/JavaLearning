package com.hosmos.management.dao;

import com.hosmos.management.model.SysLogs;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 日志Dao层
 *
 * @author chenhuayang
 */
@Mapper
public interface SysLogsDao {
    @Insert("insert into sys_logs(userId, module, flag, remark, createTime) values(#{user.id}, #{module}, #{flag}, #{remark}, now())")
    void save(SysLogs sysLogs);

    Integer count(@Param("params") Map<String, Object> params);

    List<SysLogs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Delete("delete from sys_logs where createTime <= #{time}")
    int deleteLogs(String time);
}