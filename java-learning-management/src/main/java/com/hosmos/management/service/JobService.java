package com.hosmos.management.service;

import com.hosmos.management.model.JobModel;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * 定时任务业务层
 *
 * @author chenhuayang
 */
public interface JobService {
    /**
     * 保存定时任务
     *
     * @param jobModel 定时任务信息
     */
    void saveJob(JobModel jobModel);

    /**
     * 执行定时任务
     *
     * @param jobDataMap 定时任务
     */
    void doJob(JobDataMap jobDataMap);

    /**
     * 通过定时任务id删除定时任务信息
     *
     * @param id 定时任务id
     * @throws SchedulerException 定时异常("该job是系统任务，不能删除，因为此job是在代码里初始化的，删除该类job请先确保相关代码已经去除")
     */
    void deleteJob(Long id) throws SchedulerException;

    /**
     * 通过定时任务名称获取定时任务信息
     *
     * @param jobName 定时任务名称
     * @return JobModel 定时任务信息
     */
    JobModel getByName(String jobName);

    /**
     * 通过定时任务id获取定时任务信息
     *
     * @param id 定时任务id
     * @return JobModel 定时任务信息
     */
    JobModel getById(Long id);
    /**
     * 通过搜索参数获取定时任务列表个数，用于list方法中进行页码计数
     *
     * @param params 搜索参数(包含描述(description = "?")、状态(status = ?)以及排列顺序(orderBy = order by ?))
     * @return int 定时任务列表个数
     */
    int count(Map<String, Object> params);
    /**
     * 通过搜索参数获取定时任务列表
     *
     * @param params 搜索参数(包含描述(description = "?")、状态(status = ?)以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<Dict> 定时任务列表
     */
    List<JobModel> list(Map<String, Object> params, Integer offset, Integer limit);
}