package com.hosmos.management.dao;

import com.hosmos.management.model.JobModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobDao {
    @Select("select * from t_job where jobName = #{jobName}")
    JobModel getByName(String jobName);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_job(jobName, description, cron, springBeanName, methodName, isSysJob, status, createTime, gmtTime) values(#{jobName}, #{description}, #{cron}, #{springBeanName}, #{methodName}, #{isSysJob}, 1, now(), now())")
    void save(JobModel jobModel);

    void update(JobModel jobModel);

    @Select("select * from t_job where id = #{id}")
    JobModel getById(Long id);

    int count(Map<String, Object> params);

    List<JobModel> list(Map<String, Object> params, Integer offset, Integer limit);
}
