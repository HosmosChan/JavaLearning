package com.hosmos.management.service;

import com.hosmos.management.model.JobModel;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface JobService {
    void saveJob(JobModel jobModel);

    void doJob(JobDataMap jobDataMap);

    void deleteJob(Long id) throws SchedulerException;

    JobModel getByName(String jobName);

    JobModel getById(Long id);

    int count(Map<String, Object> params);

    List<JobModel> list(Map<String, Object> params, Integer offset, Integer limit);
}